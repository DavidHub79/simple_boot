pipeline {
    agent any 
    
    tools { 
        maven 'M3'
    }
    
    stages{
        stage ('Checkout'){
            steps{
                sh 'echo "--=-- Checkout --=--"'
                git url: 'https://github.com/DavidHub79/simple_boot.git'
         	}
       	}
        stage ('compile'){
            steps{
                sh 'echo "--=-- Compile --=--"'
                sh 'mvn clean compile'
            }
        }
        stage ('Test'){
            steps{
                sh 'echo "--=-- Test --=--"'
                sh 'mvn test'
            }
            post {
                success {
                     junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage ('Code coverage'){
            steps{
                sh 'echo "--=-- Code coverage --=--"'
                jacoco (
                    execPattern: 'target/*.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java',
                    exclusionPattern: 'src/test/*'
                )
            }
        } 
        stage ('Sanity Check'){
            steps{
                sh 'echo "--=-- Sanity Check --=--"'
                sh 'mvn checkstyle:checkstyle pmd:pmd'
            }
            post {
                always {
                    recordIssues enabledForFailure: true, tools:[checkStyle()]
                    recordIssues enabledForFailure: true, tool:pmdParser(pattern:'**/target/pmd.xml')
                }
            }
        }
        stage ('Package'){
            steps{
                sh 'echo "--=-- Package --=--"'
                sh 'mvn package'
            }
        }
    }
}
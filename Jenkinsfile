pipeline {
    agent any 
    
    tools { 
        maven 'M3'
    }
    
    stages{
         stage ('Cleaning workspace'){
            steps{
                sh 'echo "--=-- cleaning  --=--"'
                sh 'mvn clean'
                script {
                    try {
                        sh 'docker stop simple-boot && docker rm simple-boot'
                        }
                    catch (Exception e)  {
                        sh 'echo "--=-- No Container to remove  --=--"'
                        }
                        try {
                        sh 'docker rmi simple-boot'
                        }
                    catch (Exception e)  {
                        sh 'echo "--=-- No image to remove  --=--"'
                        }
                }
         	}
       	}
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
        
        stage ('Docker Build'){
            steps{
                sh 'echo "--=-- Docker image Build --=--"'
                sh 'docker build -t simple-boot .'
            }
        }
          stage ('Deploy Application'){
            steps{
                sh 'echo "--=-- Deploying Docker Image --=--"'
                sh 'docker run -d --name=simple-boot -p 8081:8080 simple-boot '
            }
        }   

    }
}
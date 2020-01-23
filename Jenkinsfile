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
          stage ('Package'){
         steps{
             sh 'echo "--=-- Package --=--"'
             sh 'mvn package'
            }
        }
    }
}
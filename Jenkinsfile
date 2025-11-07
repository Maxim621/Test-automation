pipeline {
    agent any

    tools {
        maven 'M3'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Maxim621/Test-automation.git'
            }
        }

        stage('Build Infrastructure') {
            steps {
                sh '''
                    docker-compose up -d selenium-hub chrome firefox appium
                    echo "Waiting for services to start..."
                    sleep 30
                '''
            }
        }

        stage('Build Test Image') {
            steps {
                sh 'docker-compose build test-runner'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'docker-compose up --exit-code-from test-runner test-runner'
            }
            post {
                always {
                    junit 'test-reports/*.xml'
                    archiveArtifacts 'test-reports/*.xml'
                    archiveArtifacts 'screenshots/*.png'
                }
            }
        }
    }

    post {
        always {
            sh 'docker-compose down --remove-orphans'
            cleanWs()
        }
        success {
            emailext (
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: "Build successful!\n\nCheck console output at: ${env.BUILD_URL}"
            )
        }
        failure {
            emailext (
                subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: "Build failed!\n\nCheck console output at: ${env.BUILD_URL}"
            )
        }
    }
}
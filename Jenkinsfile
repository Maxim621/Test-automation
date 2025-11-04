pipeline {
    agent any

    tools {
        maven 'M3'
        jdk 'JDK21'
    }

    environment {
        SELENIUM_HUB_URL = 'http://selenium-hub:4444'
        APPIUM_URL = 'http://appium:4723'
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
                    sleep 30
                '''
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test -Dselenium.hub.url=http://selenium-hub:4444 -Dappium.url=http://appium:4723'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("test-automation:${env.BUILD_ID}")
                }
            }
        }
    }

    post {
        always {
            sh 'docker-compose down'
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
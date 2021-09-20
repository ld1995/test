pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
        }
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Build jar') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t ld1995/test:latest .'
            }
        }
        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh 'docker push ld1995/test:latest'
                }
            }
        }
    }
}
pipeline {

    agent any

    tools {
        maven 'my-maven'
    }
    stages {

        stage('Build with Maven') {
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Packaging/Pushing image') {
              steps {
                    withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                        sh 'docker build -t vietbac/springboot .'
                        sh 'docker push vietbac/springboot'
                    }
                }
        }
        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull vietbac/springboot'
                sh 'docker container stop vietbac-springboot || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '
                sh 'docker container run -d --rm --name vietbac-springboot -p 8082:8080 --network dev vietbac/springboot'
            }
        }

    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}

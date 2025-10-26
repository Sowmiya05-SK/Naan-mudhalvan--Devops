pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: env.BRANCH_NAME, url: 'https://github.com/Sowmiya05-SK/Naan-mudhalvan--Devops.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building branch: ${env.BRANCH_NAME}"
                sh 'mvn clean install'
            }
        }

        stage('Run App') {
            steps {
                echo "Running application..."
                // Run your JavaFX app or backend
                sh 'mvn javafx:run'
            }
        }
    }
}

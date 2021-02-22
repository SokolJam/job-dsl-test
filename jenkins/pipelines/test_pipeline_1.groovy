pipeline {
    agent {
        kubernetes {
            yamlFile 'config/jenkins_agent.yml'
            podRetention never()
        }
    }

    stages {
        stage('Test ... ') {
            steps {
                echo "Cool :)"
            }
        }

    }
}

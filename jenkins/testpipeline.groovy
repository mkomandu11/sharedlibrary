@Library('my-shared-library') _  // Load the shared library

pipeline {
    agent any
   environment{
        HOSTS=loadConfig('HOSTS')
        DOCKER_IMAGE=loadConfig('DOCKER.IMAGE')
    }
    parameters {
        choice(name: 'ENV', choices: loadConfig('ENV'), description: 'Stage Environment')
        string(
            defaultValue: loadConfig('TREAT_VERSION'),
            name: 'TREAT_VERSION',
            trim: true
        )
       }
    stages {
        stage('Fetch Config') {
            steps {
                script {
                    // Fetch the entire config as a map
                    echo "Hosts: ${HOSTS}"
                    echo "Docker Image :${DOCKER_IMAGE}"
                    echo "$params.ENV"
                    echo "$params.TREAT_VERSION"
                }
            }
        }
    }
}

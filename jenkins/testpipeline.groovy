@Library('jenkins-shared-library-example') _
pipeline {
    agent any
    stages {
        stage('Load Specific YAML Entries') {
            steps {
                script {
                    def environmentName = loadConfig('environment.name')
                    def databaseHost = loadConfig('database.host')
                    def hosts = loadConfig('HOSTS')
                    
                    echo "Environment Name: ${environmentName}"
                    echo "Database Host: ${databaseHost}"
                     echo "Host: ${hosts}"
                }
            }
        }
    }
}

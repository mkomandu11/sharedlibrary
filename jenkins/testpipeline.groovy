@Library('my-shared-library') _  // Load the shared library

pipeline {
    agent any

    stages {
        stage('Fetch Config') {
            steps {
                script {
                    // Fetch the entire config as a map
                    def config = loadConfig()
                    echo "Full Config: ${config}"

                    // Fetch a specific value from the config
                    def envName = loadConfig('environment.name')
                    echo "Environment Name: ${envName}"

                    def dbHost = loadConfig('database.host')
                    echo "Database Host: ${dbHost}"

                    def hosts = loadConfig('HOSTS')
                    echo "Hosts: ${hosts}"
                }
            }
        }
    }
}

@Library('jenkins-shared-library-example') _  // Load the shared library

pipeline {
    agent any
    stages {
        stage('Load YAML Data') {
            steps {
                script {
                    // Call the shared library directly with or without keyPath
                    def environmentName = loadConfig('environment.name')  // Get specific value
                    def databaseHost = loadConfig('database.host')        // Get specific value
                    def hostsString = loadConfig('HOSTS')                  // Get HOSTS as a string
                    
                    // Output the values
                    echo "Environment Name: ${environmentName}"
                    echo "Database Host: ${databaseHost}"
                    echo "Hosts: ${hostsString}"
                }
            }
        }
    }
}

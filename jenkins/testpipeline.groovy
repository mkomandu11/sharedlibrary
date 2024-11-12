@Library('jenkins-shared-library-example') _  // Load the shared library
pipeline {
    agent any
    stages {
        stage('Load YAML Data') {
            steps {
                script {
                    // Access static methods directly through the class
                    def environmentName = LoadConfig.getEnvironmentName()
                    def databaseHost = LoadConfig.getDatabaseHost()
                    def hostsString = LoadConfig.getHosts()
                    
                    // Output the values
                    echo "Environment Name: ${environmentName}"
                    echo "Database Host: ${databaseHost}"
                    echo "Hosts: ${hostsString}"
                }
            }
        }
    }
}

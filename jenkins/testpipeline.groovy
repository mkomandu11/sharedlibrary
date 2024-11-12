@Library('jenkins-shared-library-example') _
pipeline {
    agent any
    stages {
        stage('Load Specific YAML Entries') {
            steps {
                script {
                    // Directly use static methods to fetch specific entries
                    def environmentName = loadConfig.getEnvironmentName()
                    def databaseHost = loadConfig.getDatabaseHost()
                    def hostsString = loadConfig.getHosts()
                    
                    // Output the values
                    echo "Environment Name: ${environmentName}"
                    echo "Database Host: ${databaseHost}"
                    echo "Hosts: ${hostsString}"
                }
            }
        }
    }
}

@Library('jenkins-shared-library-example') _  // Ensure your library is loaded
pipeline {
    agent any
    stages {
        stage('Load Specific YAML Entries') {
            steps {
                script {
                    // Call the methods directly without needing an instance
                    def environmentName = loadConfig.getEnvironmentName()  // Access instance method
                    def databaseHost = loadConfig.getDatabaseHost()  // Access instance method
                    def hostsString = loadConfig.getHosts()  // Access instance method
                    
                    // Output the values
                    echo "Environment Name: ${environmentName}"
                    echo "Database Host: ${databaseHost}"
                    echo "Hosts: ${hostsString}"
                }
            }
        }
    }
}

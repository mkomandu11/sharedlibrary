@Library('jenkins-shared-library-example') _  // Ensure your library is loaded
pipeline {
    agent any
    stages {
        stage('Load Specific YAML Entries') {
            steps {
                script {
                    // Call the methods directly to retrieve values from YAML config
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

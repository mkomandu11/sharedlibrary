@Library('jenkins-shared-library-example') _
pipeline {
    agent any
    stages {
        stage('Load Specific YAML Entries') {
            steps {
                script {
                    def configLoader = loadConfig.new() // Create an instance of the class
                    def environmentName = configLoader.getEnvironmentName()
                    def databaseHost = configLoader.getDatabaseHost()
                    def hostsString = configLoader.getHosts()

                    // Output the values
                    echo "Environment Name: ${environmentName}"
                    echo "Database Host: ${databaseHost}"
                    echo "Hosts: ${hostsString}"
                }
            }
        }
    }
}

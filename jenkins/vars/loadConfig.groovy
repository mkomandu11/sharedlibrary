// vars/loadConfig.groovy
class LoadConfig {
    // Define a static variable to cache the YAML data
    static def cache = [:]  // Static variable to hold the cache

    // Method to load the YAML content (only once)
    static def loadYamlContent() {
        if (!cache.containsKey('config')) {
            // Load and cache the YAML content
            def yamlContent = libraryResource('config.yml')
            cache['config'] = readYaml text: yamlContent
        }
        return cache['config']
    }

    // Method to get specific key values by keyPath
    static def getConfigValue(String keyPath) {
        def config = loadYamlContent()  // Load the config (from cache or fresh)
        def keys = keyPath.split('\\.')
        def value = config
        for (key in keys) {
            value = value[key]
            if (value == null) {
                error "Key path '${keyPath}' not found in configuration."
            }
        }
        return value
    }

    // Method to get the 'HOSTS' as a single string
    static def getHosts() {
        def hosts = getConfigValue('HOSTS')
        if (hosts instanceof List) {
            return hosts.join(', ')  // Join the list into a string
        }
        error "HOSTS is not a list in the configuration."
    }

    // Method to get the 'environment.name'
    static def getEnvironmentName() {
        return getConfigValue('environment.name')
    }

    // Method to get the 'database.host'
    static def getDatabaseHost() {
        return getConfigValue('database.host')
    }
}

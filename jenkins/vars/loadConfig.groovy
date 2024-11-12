// vars/loadConfig.groovy
def cache = [:]  // A global map to store cached values

// Method to load the YAML content (only once)
def loadYamlContent() {
    if (!cache.containsKey('config')) {
        // Load and cache the YAML content
        def yamlContent = libraryResource('config.yml')
        cache['config'] = readYaml text: yamlContent
    }
    return cache['config']
}

// Method to get specific key values by keyPath
def getConfigValue(String keyPath) {
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
def getHosts() {
    def hosts = getConfigValue('HOSTS')
    if (hosts instanceof List) {
        return hosts.join(', ')  // Join the list into a string
    }
    error "HOSTS is not a list in the configuration."
}

// Method to get the 'environment.name'
def getEnvironmentName() {
    return getConfigValue('environment.name')
}

// Method to get the 'database.host'
def getDatabaseHost() {
    return getConfigValue('database.host')
}

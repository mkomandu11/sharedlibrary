// vars/loadConfig.groovy
@Field def cache = [:]  // Static-like variable for caching

// Define the main entry point using the `call` method
def call(String keyPath = null) {
    def config = loadYamlContent()  // Load config from cache or fresh
    
    // If a specific keyPath is requested, navigate the config map
    if (keyPath) {
        // Special handling for 'HOSTS' key to join the list into a string
        if (keyPath == 'HOSTS') {
            return getHosts()
        }
        return getConfigValue(keyPath)
    }
    
    return config  // If no keyPath, return the entire config
}

// Method to load YAML content (only once)
def loadYamlContent() {
    if (!cache.containsKey('config')) {
        // Load and cache the YAML content
        def yamlContent = libraryResource('config.yml')
        cache['config'] = readYaml text: yamlContent
    }
    return cache['config']
}

// Method to get specific config value
def getConfigValue(String keyPath) {
    def keys = keyPath.split('\\.')
    def value = loadYamlContent()  // Get the whole config (or cached config)
    for (key in keys) {
        value = value[key]
        if (value == null) {
            error "Key path '${keyPath}' not found in configuration."
        }
    }
    return value
}

// Method to get 'HOSTS' as a single string
def getHosts() {
    def hosts = getConfigValue('HOSTS')  // Fetch the 'HOSTS' list
    if (hosts instanceof List) {
        return hosts.join(', ')  // Join the list into a comma-separated string
    }
    error "'HOSTS' is not a list in the configuration."
}

// Method to get the 'environment.name'
def getEnvironmentName() {
    return getConfigValue('environment.name')
}

// Method to get the 'database.host'
def getDatabaseHost() {
    return getConfigValue('database.host')
}

// vars/loadConfig.groovy
static def configCache = null

// Static method to load the YAML content (only once)
static def loadYamlContent() {
    if (configCache == null) {
        def yamlContent = libraryResource('config.yml')
        configCache = readYaml text: yamlContent
    }
    return configCache
}

// Static method to get specific key values by keyPath
static def getConfigValue(String keyPath) {
    def config = loadYamlContent()
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

// Static method to get the 'HOSTS' as a single string
static def getHosts() {
    def hosts = getConfigValue('HOSTS')
    if (hosts instanceof List) {
        return hosts.join(', ')
    }
    error "HOSTS is not a list in the configuration."
}

// Static method to get the 'environment.name'
static def getEnvironmentName() {
    return getConfigValue('environment.name')
}

// Static method to get the 'database.host'
static def getDatabaseHost() {
    return getConfigValue('database.host')
}

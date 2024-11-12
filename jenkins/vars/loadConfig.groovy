// vars/loadConfig.groovy
def configCache = null

// Instance method to load the YAML content (only once)
def loadYamlContent() {
    if (configCache == null) {
        def yamlContent = libraryResource('config.yml')
        configCache = readYaml text: yamlContent
    }
    return configCache
}

// Instance method to get specific key values by keyPath
def getConfigValue(String keyPath) {
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

// Instance method to get the 'HOSTS' as a single string
def getHosts() {
    def hosts = getConfigValue('HOSTS')
    if (hosts instanceof List) {
        return hosts.join(', ')
    }
    error "HOSTS is not a list in the configuration."
}

// Instance method to get the 'environment.name'
def getEnvironmentName() {
    return getConfigValue('environment.name')
}

// Instance method to get the 'database.host'
def getDatabaseHost() {
    return getConfigValue('database.host')
}

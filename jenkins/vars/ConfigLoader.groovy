// vars/ConfigLoader.groovy
class ConfigLoader {
    private static def configCache = null

    // Load YAML from the resources folder
    static def loadConfig() {
        if (!configCache) {
            // Read the YAML content from the resources folder
            def yamlContent = libraryResource('config.yaml')
            configCache = readYaml text: yamlContent
        }
        return configCache
    }

    // Retrieve value by key path
    static def getConfigValue(String keyPath) {
        def config = loadConfig()
        def keys = keyPath.split('\\.')  // Split by dot for nested keys
        def value = config
        for (key in keys) {
            value = value[key]
            if (value == null) {
                error "Key path '${keyPath}' not found in configuration."
            }
        }

        // If the requested key is 'hosts', convert the list to a comma-separated string
        if (keyPath == 'hosts' && value instanceof List) {
            return value.join(', ')
        }

        return value
    }
}

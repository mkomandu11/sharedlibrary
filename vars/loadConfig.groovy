// vars/loadConfig.groovy
def call(String keyPath = null) {
    // Load the YAML file content from the hardcoded path
    def yamlContent = libraryResource('config.yaml')
    def config = readYaml text: yamlContent

    // If a specific keyPath is requested, navigate the config map
    if (keyPath) {
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

    // Return the entire config if no specific keyPath is provided
    return config
}

// vars/loadConfig.groovy

def call(String keyPath = null) {
    // If no key path is provided, return the entire config
    if (!keyPath) {
        return ConfigLoader.loadConfig()  // Return entire config
    }

    // Otherwise, return the specific value for the provided key path
    return ConfigLoader.getConfigValue(keyPath)
}

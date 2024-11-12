

def call(String keyPath = null) {

    def yamlContent = libraryResource('config.yaml')  //
    def config = readYaml text: yamlContent  //


    if (!keyPath) {
        return config
    }


    def keys = keyPath.split('\\.')
    def value = config
    for (key in keys) {
        value = value[key]
        if (value == null) {
            error "Key path '${keyPath}' not found in configuration."
        }
    }


    if (keyPath == 'HOSTS') {

        return value.collect { '--add-host ' + it }.join(' ')
    }

    return value
}

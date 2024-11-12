// vars/loadConfig.groovy
def call(String filePath = 'config.yml') {
    def yamlContent = libraryResource(filePath)
    def config = readYaml text: yamlContent
    return config
}

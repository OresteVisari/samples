import org.cloudifysource.utilitydomain.context.ServiceContextFactory

context = ServiceContextFactory.getServiceContext()
evaluate(new File("${context.serviceDirectory}/scripts/chmod-init.groovy"))
builder = new AntBuilder()

def config = new ConfigSlurper().parse(new File("${context.serviceDirectory}/service.properties").toURL())

builder.sequential {
  echo(message:"apache_install.groovy: Running Apache 2 install script from FastConnect...")
  exec(executable:"${context.serviceDirectory}/scripts/install_apache.sh", osfamily:"unix", failonerror:"true") {
    env(key:"PORT", value:config.apache.port)
    env(key:"DOC_ROOT", value:config.apache.document_root)
  }
}

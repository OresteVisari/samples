import org.cloudifysource.utilitydomain.context.ServiceContextFactory

context = ServiceContextFactory.getServiceContext()
evaluate(new File("${context.serviceDirectory}/scripts/chmod-init.groovy"))

def config = new ConfigSlurper().parse(new File("${context.serviceDirectory}/service.properties").toURL())
def nameMysql = args[0]

// Configuring and starting MySQL regarding 2 variables : storage_path and port
def builder = new AntBuilder()
builder.sequential {
  echo(message:"mysql_start.groovy: Running Mysql start script from FastConnect...")
  exec(executable:"${context.serviceDirectory}/scripts/startMysql.sh", osfamily:"unix",failonerror: "true") {
    env(key:"VOLUME_HOME", value:config[nameMysql].storage_path)
    env(key:"PORT", value: config[nameMysql].port)
    env(key:"DB_NAME", value: config[nameMysql].db_name)
    env(key:"DB_USER", value: config[nameMysql].db_user)
    env(key:"DB_PASSWORD", value: config[nameMysql].db_password)
    env(key:"BIND_ADRESS", value:config[nameMysql].bind_address)
  }
}

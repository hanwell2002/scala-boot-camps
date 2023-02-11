package config

import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

import java.io._
import java.io.{File, FileInputStream, FileNotFoundException, InputStreamReader}
import java.nio.charset.StandardCharsets.UTF_8
import java.net.URL
import java.util.Properties
import scala.io.Source
import scala.jdk.CollectionConverters.MapHasAsScala

object LoadPropertiesFromFile extends App {
  val logger = LoggerFactory.getLogger(this.getClass)

  // var configPath = "C:/bootcamps/scala-boot-camps/app-env-conf/src/main/resources/config/KafkaConnection.properties"
  var absPath = "C:/var/config/KafkaConnection.properties"
  var fileInClassPath = "application2.properties"

  /*// method o1
  method01(absPath)
  //method 02
  val props22 = loadfrom(absPath)
  val port22 = props22.get("port")
  println(s"port22 = $port22")

  readFromProperties
  readFromConf
  method07(absPath)
  method05(fileInClassPath)
  */
  readJson // --OK
  method09

  val jsonPath = "C:/var/config/json-sample.json"
  loadJsonProperties(jsonPath)

  //====================================================
  def javaWay(): Unit = {
    /*
    Properties properties = new Properties();
    FileInputStream in = new FileInputStream("database.properties");
    properties.load(in);
    in.close();

    String url = properties.getProperty("URL");
    String username = properties.getProperty("username");
    String passwd = properties.getProperty("password");
    */
  }

  def method04(configPath: String): Unit = {
    val resource = Source.fromResource("application2.properties")
    val configs: Iterator[String] = resource.getLines
    println("---------configs---------------")
    configs.foreach(println)
    println("---------/configs---------------")

    val readmeText: Iterator[String] = Source.fromResource("application2.properties").getLines
    for (e <- readmeText) {
      println("m04:> " + e)
    }
  }

  // properties file located at ./resources directory
  def method08(): Unit = {
    println("method 008---------------------")
    val stream = Thread.currentThread.getContextClassLoader.getResourceAsStream(fileInClassPath)
    // properties.load(stream)  //works!

    val properties: Properties = new Properties()
    // if (url != null) {
    if (stream != null) {
      // val source = Source.fromURL(url)
      val source = Source.fromInputStream(stream)
      properties.load(source.bufferedReader())
      val host = properties.get("host")
      val port = properties.get("port")
      val schema = properties.get("schema")
      println(s"m08 >>> host=${host}, port=${port}, schema=${schema}")
    } else {
      println("m08: failed to load properties")
    }
  }

  def method07(configPath: String) {
    var configMap = Source.fromFile(configPath)
      .getLines()
      .filter(line => line.contains("="))
      .map { line =>
        val tkns = line.split("=")
        if (tkns.size == 1) {
          (tkns(0) -> "")
        } else {
          (tkns(0) -> tkns(1))
        }
      }.toMap

    println(configMap)

    val host2 = configMap("host")
    val port2 = configMap("port")
    val schema2 = configMap("schema")
    println(s"m07:host=${host2}, port=${port2}, schema=${schema2}")
  }

  def method05(fileInClassPath: String): Unit = {
    val properties = new Properties
    val url: URL = this.getClass.getResource(fileInClassPath)
    println("m05:url---->" + url)

    properties.load(Thread.currentThread.getContextClassLoader.getResourceAsStream(fileInClassPath)) //works!
    val host33 = properties.get("host")
    val port33 = properties.get("port")
    val schema33 = properties.get("schema")
    println(s"m05:> host33=${host33}, port33=${port33}, schema33=${schema33}")
  }

  //
  def method09(): Unit = {
    //does not work:  getClass.getResource(fileInClassPath)
    val resource: URL = getClass.getResource(fileInClassPath)
    println(resource) //null
    val resource2 = Source.fromResource(fileInClassPath)
    // resource2.foreach(e=> println("m09:resource2 -> " + e.toString))
    val lines: Iterator[String] = resource2.getLines
    lines.foreach(e => println("m09:Line -> " + e.toString))
  }

  // standard Java way, load config from a absolute path : /var/config/my.properties
  def method01(configPath: String): Unit = {
    println("m01: config path = " + configPath)
    val in = new InputStreamReader(new FileInputStream(configPath), UTF_8)
    val props = new Properties()
    try {
      props.load(in)
    } finally {
      in.close()
    }

    val host = props.get("host")
    val port = props.get("port")
    val schema = props.get("schema")
    println(s"m01>> host=${host}, port=${port}, schema=${schema}")

    // convert to Map
    //Map[String, String]/May(anyref, anyref)
    val prp = props.asScala.toMap
    println(prp)

    val server = prp("host")
    println(s"m01>> server = $server")
  }

  //=========================================================
  def readJson_ok(): Unit = {
    val jsonPath = "C:/var/config/json-sample.json"
    // does not work!
    // val fileStream = getClass.getResource("json-sample-2.json")
    // val fileStream = getClass.getResourceAsStream("json-sample-2.json")
    //  works
    // val fileStream = Thread.currentThread.getContextClassLoader.getResourceAsStream("json-sample-2.json")
    val fileStream = new FileInputStream(jsonPath);
    val lines = Source.fromInputStream(fileStream).getLines
    // val lines = Source.fromURL(fileStream).getLines
    lines.foreach(line => println(line))
  }

  def readJson(): Unit = {
    val jsonPath = "C:/var/config/json-sample.json"
    val fileStream0 = new InputStreamReader(new FileInputStream(jsonPath), UTF_8)
    val fileStream = new FileInputStream(jsonPath);
    val properties = new Properties()
    properties.load(fileStream)

    println(properties)
    println(properties.getProperty("glossary.title"))
  }

  def loadJsonProperties(configPath: String): Unit = {
    // val jsonPath = "C:/var/config/json-sample.json"
    // val applicationConf: Config = ConfigFactory.load(configPath)
    val file = new File(configPath)
    val settings = ConfigFactory.parseFile(file) //.withFallback(defaults)

    val title = settings.getString("glossary.title")
    logger.info(title)
  }

  def dirFolder(): Unit = {
    val path = getClass.getResource("C:/var/data")
    val folder = new File(path.getPath)
    if (folder.exists && folder.isDirectory)
      folder.listFiles
        .toList
        .foreach(file => println(file.getName))
  }

  // method - 10
  def readFromProperties(): Unit = {
    //val path = "C:/bootcamps/scala-boot-camps/app-env-conf/src/main/resources/config/application.properties"
    val path = "application.properties"
    // Assuming that application.properties is in the root folder of your application
    val url = getClass.getResource(path)
    val properties: Properties = new Properties()

    if (url != null) {
      val source = Source.fromURL(url)
      properties.load(source.bufferedReader())
    }
    else {
      // logger.error
      println("m10:properties file cannot be loaded at path " + path)
      throw new FileNotFoundException("m10:Properties file cannot be loaded")
    }

    val table = properties.getProperty("hbase_table_name")
    val zquorum = properties.getProperty("hbase.zookeeper.quorum")
    val port = properties.getProperty("hbase.zookeeper.property.clientPort")

    println(s"m10: >>> table=$table, zquorum=$zquorum, port=$port")
  }

  def readFromConf(): Unit = {
    // Assuming that application.conf is in the root folder of your application
    // ConfigFactory looks for the name application.conf by default so you need not give this name
    val config = ConfigFactory.load("application.conf").getConfig("com.arceed.bigdata")
    val sparkConfig = config.getConfig("spark")
    val mysqlConfig = config.getConfig("mysql")

    println(s"sparkConfig=$sparkConfig \nmysqlConfig=$mysqlConfig")
    //    val appName = sparkConfig.getString(BatchConstants.GET_APP_NAME)
    //    println(appName) // prints my-app
  }

  /**
   * You have three options:
   *
   * take advantage of relative path to your current package (where Test.class is):
   *
   * getClass.getResource("test.fxml")
   * you can use absolute path:
   *
   * getClass.getResource("/com/mysite/main/test.fxml")
   * or load through the ClassLoader (note that it always start from root):
   *
   * getClass.getClassLoader.getResource("com/mysite/main/test.fxml")
   * In IntelliJ IDEA, make sure you have added ;?*.fxml to the:
   *
   * Settings (Preferences on Mac) | Compiler | Resource Patterns.
   *
   * @param file
   * @return
   */
  def loadfrom(file: String): Properties = {
    val properties = new Properties()
    // First check if the file is on the classpath
    val is = {
      getClass.getResourceAsStream(file) match {
        case null =>
          // Try and load it as a file
          println("here....................")
          val f = new File(file)
          if (f.isFile) {
            println("here2....................")

            new FileInputStream(f)
          } else {
            throw new IllegalArgumentException(s"File $file not found as classpath resource or on the filesystem")
          }
        case found => found
      }
    }

    try {
      properties.load(is)
      properties
    } finally {
      is.close()
    }
  }
}



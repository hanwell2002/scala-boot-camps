import scalikejdbc._
import org.slf4j.LoggerFactory
// import ch.qos.logback.core.util.StatusPrinter
// import ch.qos.logback.classic.LoggerContext
/*
import com.typesafe.scalalogging._
import com.typesafe.scalalogging.slf4j.LazyLogging
*/
/*
 Create a directory to store the log file:
    mkdir C:\var\log
    or mkdir /var/log in Unix like OS
* */
object Main extends App{
    println("Application started! \n")
    val logger = LoggerFactory.getLogger(this.getClass)
    logger.info("Application started...")

    logger.debug("Created logger.")

    // Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton("jdbc:postgresql://localhost:5432/postgres", "postgres", "Admin$777")
    logger.debug("Setup Connection Pool")

    implicit val session = AutoSession

    logger.info("Query data from employee table")
    val employees: List[Map[String, Any]] = sql"select first_name, last_name, email, phone from employee".map(_.toMap).list.apply()
    employees.foreach(println)

    logger.info("Query data from city table")
    val cities: List[Map[String, Any]] = sql"SELECT * FROM city".map(_.toMap).list.apply()
    //cities.foreach(println)

    logger.info(">> *** Completed Normally! ***")
    println("\n*** Application completed Normally ***")
}
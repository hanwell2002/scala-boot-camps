package com.newhopebootcamps.util

import java.io.{BufferedWriter, File, FileInputStream, FileOutputStream, FileWriter, IOException}
import scala.io.Source

object TextFileUtils extends App {
  // a demo data to write to text file in csv format
  val csv =
    """January, 10000.00, 9000.00, 1000.00
      |February, 11000.00, 9500.00, 1500.00
      |March, 12000.00, 10000.00, 2000.00
      |April, 10000.00, 9000.00, 1000.00
      |May, 11000.00, 9500.00, 1500.00
      |June, 12000.00, 10000.00, 2000.00
      |July, 10000.00, 9000.00, 1000.00
      |August, 11000.00, 9500.00, 1500.00
      |September, 12000.00, 10000.00, 2000.00
      |October, 10000.00, 9000.00, 1000.00
      |November, 11000.00, 9500.00, 1500.00
      |December, 12000.00, 10000.00, 2000.00
      |""".stripMargin

  println("========================================================")
  //  println(csv)
  var multiline =
    """January, 10000.00, 9000.00, 1000.00
      #February, 11000.00, 9500.00, 1500.00
      #March, 12000.00, 10000.00, 2000.00
      #April, 10000.00, 9000.00, 1000.00
      #May, 11000.00, 9500.00, 1500.00
      #June, 12000.00, 10000.00, 2000.00
      #""".stripMargin('#').replaceAll("\r\n", "@")
  println("==========================2==============================")

  //println(multiline)


  /*
  val FILE_NAME = "/opt/bootcamps/data/finance.csv"

  writeFile(FILE_NAME, csv)
  readFile(FILE_NAME)
  */
  val text_file_name = "/opt/bootcamps/data/photo.txt"
  printOutFile(text_file_name)

  /**
   * read `file` from specified path
   *
   * @param filePath
   */
  def readFile(filePath: String): Unit = {
    println("Month, Income, Expenses, Profit")
    val bufferedSource = io.Source.fromFile(filePath)
    for (line <- bufferedSource.getLines) {
      //   println(line)
      // if(line.length != 0) {
      if (!line.isEmpty()) {
        // println(s"Length of Variable is ${line.length}")
        val cols = line.split(",").map(_.trim)

        // do whatever you want with the columns here
        println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
      }
    }
    bufferedSource.close
  }

  /*
  // PrintWriter
  import java.io._
  val pw = new PrintWriter(new File("hello.txt" ))
  pw.write("Hello, world")
  pw.close

  // FileWriter
  val file = new File(canonicalFilename)
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write(text)
  bw.close()
*/

  /**
   * write a `Seq[String]` to the `filename`.
   *
   * @param filename
   * @param lines
   */
  def writeLinesToFile(filename: String, lines: Seq[String]): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))

    for (line <- lines) {
//      bw.write(line)
      bw.write(line, 3, 3)
      bw.write("\n")
    }

    bw.close()
  }

  /**
   * write a `String` to the `filename`.
   *
   * @param filename
   * @param s
   */
  def writeFile(filename: String, s: String): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(s)
    bw.close()
  }

  def printOutFile(filename: String): Unit = {
    // val filename = "phonto.txt"
    val allines = scala.io.Source.fromFile(filename).getLines
    for (line <- allines) {
      println(line)
    }
  }

  def printOutString(): Unit = {
     val string = "/opt/bootcamps/data/photo.txt"
//    val allines = scala.io.Source.fromString(string).getLines
   val allines = scala.io.Source.fromFile(string).getLines
    for (line <- allines) {
      println(line)
    }
  }

  printOutString()

  val linesToFile = "/opt/bootcamps/data/linesToFile.txt"

  val lines = Seq(
    "abc,efg",
    "123,567",
    "GOOGL,COM"
  )
  writeLinesToFile(linesToFile, lines)


  def readBinaryFile=
    {
      val jpg: String = "/opt/bootcamps/data/redis-logo"
      var in = None: Option[FileInputStream]
      var out = None: Option[FileOutputStream]
      try {
        in = Some(new FileInputStream(jpg+".jpg"))

        out = Some(new FileOutputStream(jpg+".copy"+".jpg"))
        var c = 0

        c = in.get.read
        while ( c != -1)
        {
          out.get.write(c)
          c = in.get.read
        }
      } catch {
        case e: IOException => e.printStackTrace
      } finally {
        println("entered finally ...")
        if (in.isDefined) in.get.close
        if (out.isDefined) out.get.close
      }
    }

  readBinaryFile
}

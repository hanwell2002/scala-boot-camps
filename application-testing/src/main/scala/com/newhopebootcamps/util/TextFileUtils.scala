package com.newhopebootcamps.util

import java.io.{BufferedWriter, File, FileWriter}
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
      bw.write(line)
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
    for (line <- Source.fromFile(filename).getLines) {
      println(line)
    }
  }
}

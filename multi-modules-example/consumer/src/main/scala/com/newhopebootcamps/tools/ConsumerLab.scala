package com.newhopebootcamps.tools

import java.io.{BufferedWriter, File, FileWriter}
import scala.io.Source

class ConsumerLab {
  val text_file_name = "/opt/bootcamps/data/photo.txt"
  printOutFile(text_file_name)

  def printOutFile(filename: String): Unit = {
    for (line <- Source.fromFile(filename).getLines) {
      println(line)
    }
  }

  def displayFile(filename: String) = {
    printOutFile(filename)
  }
}

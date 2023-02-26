package com.newhopebootcamps.util

object ReadCsvTest extends App {

  // ** Copy peoples.csv, photo.csv to /opt/bootcamps/data/
  val FILE_PATH = "/opt/bootcamps/data/"

  readCSV(FILE_PATH + "people.csv")
  val arr = readCSV_all_double_columns(FILE_PATH + "doubles.csv")
  println("\n-----------------0----------------------")
  arr.map(_.mkString(", ")).foreach(println)

  println("-----------------1-----------------------")
  arr foreach { row => row foreach { item => print(item + ", ") }; println }

  println("-----------------2-----------------------")
  //print(arr.map(_.mkString).mkString("|", "\n", ","))
  print(arr.map(_.mkString).mkString("\n"))

  def readCSV(filePath: String): Unit = {
    val bufferedSource = io.Source.fromFile(filePath)

    println("Name, Age, Job")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(";").map(_.trim)
      //  do whatever you want with the columns here
      println(s"${cols(0)},${cols(1)}, ${cols(2)}")
    }
    bufferedSource.close
  }

  def readCSV_all_double_columns(filePath: String): Array[Array[Double]] = {
    TextFileUtils.printOutFile(filePath)

    io.Source.fromFile(filePath)
      .getLines()
      .map(_.split(",").map(_.trim.toDouble * 100))
      .toArray
  }

}

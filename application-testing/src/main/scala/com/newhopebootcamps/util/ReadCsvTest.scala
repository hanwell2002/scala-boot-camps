package com.newhopebootcamps.util

object ReadCsvTest extends App {

  // ** Copy peoples.csv, photo.csv to /opt/bootcamps/data/
  val FILE_PATH = "/opt/bootcamps/data/"

  //first example read a csv
  readCSV(FILE_PATH + "people.csv")

  // another example for reading text and convert to 2 dim array double
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
      val bus_date = getCurrDate();
      println(s"${bus_date}, ${cols(0)},${cols(1)}, ${cols(2)} ")
    }
    bufferedSource.close
  }

  def getCurrDate():String ={
    return "20230305"
  }

  def readCSV_all_double_columns(filePath: String): Array[Array[Double]] = {
    TextFileUtils.printOutFile(filePath)

    io.Source.fromFile(filePath)
      .getLines()
      .map(_.split(",").map(_.trim.toDouble * 100.0))
      .toArray
  }
  //Array("10 ", " 200.0", "  99.88 ")
}

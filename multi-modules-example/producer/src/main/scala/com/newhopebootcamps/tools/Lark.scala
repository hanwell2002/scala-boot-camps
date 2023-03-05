package com.newhopebootcamps.tools

object Lark {
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

  def printStr(str: String): Unit = {
      println(str)
  }

}

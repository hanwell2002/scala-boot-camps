package com.newhopebootcamps.util

import java.text.SimpleDateFormat
import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import java.util.{Calendar, Date}
import scala.util.Try

object CommonUtils {
  def isEmpty(x: String) = x == null || x.isEmpty

  /**
   *  formatString:  "yyyy-MM-dd"
   * @param formatString
   * @return
   */
  def currentDateString(formatString: String): String = {
    val formatter = DateTimeFormatter.ofPattern(formatString)
    val fileName = formatter.format(LocalDateTime.now())
    fileName
  }

  /**
   * toLocalDate("02/28/2021", "MM/dd/yyyy")
   *
   * @param dateString
   * @param formatString
   * @return
   */
  def toLocalDate(dateString: String, formatString: String): LocalDate = {
    val formatter = DateTimeFormatter.ofPattern(formatString)
    val date = LocalDate.parse(dateString, formatter)
    date
  }

  /**
   * toSimpleDate("2022-09-15 00:00:00.0") // Some("20220915")
   * toSimpleDate("Abc") // None
   *
   * @param dateString
   * @return
   */
  def toSimpleDate(dateString: String): Option[String] = {
    val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    Try {
      LocalDateTime.parse(dateString, parser)
    }.toOption
      .map(_.format(formatter))
  }

}

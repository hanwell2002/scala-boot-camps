package utility.logging

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LogUtilis {
  val ts_format = "yyyyMMdd HH:mm:ss SSS"

  def info(msg: String) = {
    val ts = LocalDateTime.now.format(DateTimeFormatter.ofPattern(ts_format))
    println(s">>$ts INFO: $msg")
  }

  def debug(msg: String) = {
    val ts = LocalDateTime.now.format(DateTimeFormatter.ofPattern(ts_format))
    println(s">>$ts DEBUG: $msg")
  }

  def warn(msg: String) = {
    val ts = LocalDateTime.now.format(DateTimeFormatter.ofPattern(ts_format))
    println(s">>$ts WARN: $msg")
  }

  def error(msg: String) = {
    val ts = LocalDateTime.now.format(DateTimeFormatter.ofPattern(ts_format))
    println(s">>$ts ERROR- FATAL: $msg")
  }

}

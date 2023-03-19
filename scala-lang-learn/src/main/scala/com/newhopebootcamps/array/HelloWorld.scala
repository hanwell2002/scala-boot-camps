package com.newhopebootcamps.array

object HelloWorld extends App {

  println("Hello, world!")

  val cache = collection.mutable.Map[String, String]()

  def f(x: String): String = {
    println("taking my time.");
    Thread.sleep(100)
    x.reverse
  }

  def cachedF(s: String): String = cache.getOrElseUpdate(s, f(s))


}

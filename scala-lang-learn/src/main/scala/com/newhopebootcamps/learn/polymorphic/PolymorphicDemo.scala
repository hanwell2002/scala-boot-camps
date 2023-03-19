package com.newhopebootcamps.learn.polymorphic

class PolymorphicDemo {
  def listOfDuplicates[A](x: A, length: Int): List[A] = {
    if (length < 1)
      Nil
    else
      x :: listOfDuplicates(x, length - 1)
  }
}

object PolymorphicDemo extends App {
  private val demo = new PolymorphicDemo
  val intListResult = demo.listOfDuplicates[Int](3, 4)
  val strListResult = demo.listOfDuplicates("La", 8)

  println(intListResult) // List(3, 3, 3, 3)
  println(strListResult) // List(La, La, La, La, La, La, La, La)


}



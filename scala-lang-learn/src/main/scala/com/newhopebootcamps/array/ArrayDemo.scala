package com.newhopebootcamps.array

import scala.collection.mutable
import scala.collection.mutable._
import scala.reflect.ClassTag

object  ArrayDemo {


//  def evenElems[T](xs: Vector[T]): Array[T] = {
def evenElems[T: ClassTag](xs: Vector[T]): Array[T] = {
  val arr = new Array[T]((xs.length + 1) / 2)
  for (i <- 0 until xs.length by 2)
    arr(i / 2) = xs(i)
  arr
}

//  def evenElems2[Int](xs: Vector[Int]): Array[Int] = {
/*def evenElems2[Int](xs: Vector[Int])(implicit m: ClassTag[Int])
    val arr = new Array[Int]((xs.length + 1) / 2)
    for (i <- 0 until xs.length by 2)
      arr(i / 2) = xs(i)
    arr
  }*/

  def main(args: Array[String]) {

    val parameter_number = args.length

    if (parameter_number >= 1) {
    for(i <- 0 to parameter_number - 1) {
        println(args(i))
      }

      println("--------------")

      for (arg <- args) {
        println(arg)
      }


    }

    var arr02 = Array(1, 3, "xx")
    arr02(1) = "xx"

    for (i <- arr02) {
      println(i)
    }

    //可以使用我们传统的方式遍历，使用下标的方式遍历
    //  for (index <- 0 until arr02.length) {
    for (index <- 0 to arr02.length - 1) {
      printf("arr02[%d]=%s", index, arr02(index) + "\t")

    }


    println("")
    // List listexample<String> = new  ArrayList<>();

    val listex3 = List("2", "3")

    for (n <- 0 until listex3.length) {
      println(listex3(n))
    }


    val aseq = Array(1,3,5,7)

    val b1 = aseq.isInstanceOf[Array[Int]]

    println(b1)


    val aseq2 = Seq(1, 3, 5, 7)

    val b2 = aseq2.isInstanceOf[Seq[Int]]

    println(b2)


    val arr3 = new Array[Double](10)

    val rates = ArrayBuffer[Double](10)

    rates += 1.03
    rates += 2.02
    rates += 0.18
    rates.toArray

    var colors = ArrayBuffer[String]()
    colors += "Red"
    colors += "Green"
    colors += "Blue"
    println(colors)


    val a = Vector(1, 2, 3)

    val r = evenElems(a)


  }

}

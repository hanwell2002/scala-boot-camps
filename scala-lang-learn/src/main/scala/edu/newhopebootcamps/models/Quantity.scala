package edu.newhopebootcamps.models

class Quantity(val n: Int) extends AnyVal{
  def +(q: Quantity) = new Quantity( n + q.n)
  def -(q: Quantity) = new Quantity( n - q.n)

  //TBD write a toString method/function
  override def toString = s"$n"
}

object Calculator extends App {

  val n1 = new Quantity(10)
  val n2 = new Quantity(90)

  val sum = n1 + n2
  val sumWay2 = n1.+(n2)
  println(sum)  //need to implement toString function.
  println(sumWay2)
  println(s"sumWay2 = ${sumWay2.n} ")
  println(s"sumWay2 = $sumWay2 ")  // implemented toString function.


  val a1: Int= 10
  val a2: Int = 20
  val r = a1+ a2

  println(r)
}
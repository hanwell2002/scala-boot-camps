package com.newhopebootcamps.learn.polymorphic

import com.newhopebootcamps.model._

object PaymentManagement extends App {
  val e1 = new Employee
  e1.firstName = "Joe"
  e1.lastName = "Biden"
  e1.address = "What House, WD"
  e1.salary = 1234.56

  val e2 = new Employee
  e2.firstName = "Barack"
  e2.lastName = "Obama"
  e2.address = "What House, WD"
  e2.salary = 1234.34

  val e3 = new Employee
  e3.firstName = "Donald"
  e3.lastName = "Trump"
  e3.address = "What House, WD"
  e3.salary = 1000.12

  val m1 = new Manager("Marketing")
  val m2 = new Manager("Research & Development")

  m1.lastName = "Roosevelt"
  m1.firstName = "Franklin"
  m1.salary = 200000.0
  m1.address = "USA, WD"

  m2.firstName = "Steve"
  m2.lastName = "Jobs"
  m2.address = "Silicon"
  m2.salary = 150000.00

   printPaySlip[Manager](m2)

  def printPaySlip[A](x: A) = {
     println("printing pay slip")

    println(x)


  }


}
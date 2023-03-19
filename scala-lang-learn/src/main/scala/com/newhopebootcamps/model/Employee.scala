package com.newhopebootcamps.model

abstract class Person {
  var firstName: String
  var lastName: String
  var address: String

  override def toString:String = s"$firstName, $lastName, $address"
}

/*
case class ContingentWorker(val firstName: String,val lastName: String, val address: String, employeeId: String, val hourlyRate : Double)
  extends Person
case class Contractor(val firstName: String,val lastName: String, val address: String, employeeId: String, val taxno: Int)
  extends Person
*/

class Employee extends Person {
  override var firstName: String = ""
  override var lastName: String = ""
  override var address: String = ""
  var salary: Double = 0.0

  override def toString:String = s"$firstName, $lastName, $address, $salary"
}

class Manager(val DepartName: String) extends Employee {
  var departName: String = "HR"
}

/*
class Animal {}
class Bird extends Animal {} //协变
class Covariant[T](t: T) {}

val cov = new Covariant[Bird](new Bird)
val cov2: Covariant[Animal] = cov
*/

/*
class Animal {}

class Bird extends Animal {} //协变

class Covariant[+T](t: T) {}

val cov = new Covariant[Bird](new Bird)
val cov2: Covariant[Animal] = cov
*/


package com.newhopebootcamps.learn.polymorphic

import com.newhopebootcamps.model._

import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag


class ImmutableBox2[+A](val content: A)

object AnimalRunner extends App {

/*
  val arr: Array[Int] = Array[Int](1, 2, 3)
  val arr2: Array[Any] = arr   //error, Int cannot be used as Any
  arr2(0) = 2.54
*/

  val myAnimal: Animal = Cat("Felix")
  val myCatBox: Box[Cat] = new Box[Cat](Cat("Felix"))

  //  val myAnimalBox: Box[Animal] = myCatBox // this doesn't compile
  //  val myAnimal: Animal = myAnimalBox.content

  val myAnimalBox: Box[Animal] = new Box[Animal](Dog("Fido"))
  myAnimalBox.content = Dog("Davi")

  myAnimalBox.content = myAnimal

  //Co
  val mycat = Cat("Felix")

  val catbox: ImmutableBox2[Cat] = new ImmutableBox2[Cat](mycat)

  val animalBox: ImmutableBox2[Animal] = catbox // now this compiles

  //----------------------------------------------------------------------
  val listex = List("123", "Banana", "Grape") //only can put String
  val listOfInt = List(1, 2, 3, 10) //can only put integer
  val listOfDouble = List(100.0, 89.88, 99.99) // can only put Double

  val arrayOfInt = new Array[Int](10) //change to Generics
  val arrayOfString = new Array[String](10)

  //var arrayexaple: Array[T] = new Array[T](3)
   //example as blow:
   def listToArray[T: ClassTag](x: List[T]): Array[T] = {
     val arr: Array[T] = new Array[T](x.length)
     for (i <- 0 until x.length)
       arr(i) = x(i)

     arr
   }

  def employeelistToArray(employees: List[Employee]): Array[Employee]= {
    val arr: Array[Employee] = new Array[Employee](employees.length)
    for (i <- 0 until employees.length)
      arr(i) = employees(i)

    //return
    arr
  }

  val emps: List[Employee] = getEmployees
  println(emps.length)

  val empArr: Array[Employee] = employeelistToArray(emps)
  println("================1=====================")
  empArr.foreach {
    emp =>
      println(emp.lastName + "," + emp.lastName + ", " + emp.address)
  }

  println("================2=====================")
  empArr.foreach {
    emp =>  println(emp)
  }

  println("================3=====================")
   for( emp <- emps)
     println(emp)

  println("================4: use toArray method of collection=====================")
  val empToArray = emps.toArray
  for (emp <- empToArray)
    println(emp)

  //---1----
  val empsArr = listToArray(emps)
  println("================5=====================")
  for (emp <- empsArr)
    println(emp)

  val mngs = getManagerList
  val mngsArr = listToArray(mngs)
  println("================6=====================")
  for (emp <- mngsArr)
    println(emp)


  def getEmployees: List[Employee] = {

   var es = ListBuffer[Employee]();

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

   es  += e1
   es  += e2
   es  += e3


   //return es
   es.toList
 }

  def getManagerList: List[Manager] = {
    var result = ListBuffer[Manager]();

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

    result += m1
    result += m2

    result.toList

  }





}

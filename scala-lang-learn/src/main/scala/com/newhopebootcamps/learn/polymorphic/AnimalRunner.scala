package com.newhopebootcamps.learn.polymorphic

import com.newhopebootcamps.model._


class ImmutableBox2[+A](val content: A)

object AnimalRunner extends App{


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

  /----------------------------------------------------------------------
  val listex = List("123", "Banana", "Grape")  //only can put String
  val listOfInt = List(1,2,3,10)                //can only put integer
  val listOfDouble = List(100.0, 89.88, 99.99)   // can only put Double

  val arrayOfInt = new Array[Int](10)            //change to Generics
  val arrayOfString = new Array[String](10)

  // val arrayexaple: Array[T] = new Array[T]()

}

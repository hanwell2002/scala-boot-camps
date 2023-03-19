package com.newhopebootcamps.learn.polymorphic

import com.newhopebootcamps.model._

class ImmutableBox[+A](val content: A)

object CovarianceExample extends  App{

  val mycat = Cat("Felix")

  val catbox: ImmutableBox[Cat] = new ImmutableBox[Cat](mycat)

  val animalBox: ImmutableBox[Animal] = catbox // now this compiles


  //---------------------------------
  def printAnimalNames(animals: List[Animal]): Unit =
    animals.foreach {
      animal => println(animal.name)
    }

  val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  // prints: Whiskers, Tom
  printAnimalNames(cats)

  // prints: Fido, Rex
  printAnimalNames(dogs)


}

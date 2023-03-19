package com.newhopebootcamps.learn.polymorphic

import com.newhopebootcamps.model.{Animal, Cat, Emp}


abstract class Serializer[-A] {
    def serialize(a: A): String
    // def deserilze(a: A, x: Double, l: List)

  }

  val animalSerializer: Serializer[Animal] = new Serializer[Animal] {
    def serialize(animal: Animal): String = s"""{ "name": "${animal.name}" }"""
  }


  val catSerializer: Serializer[Cat] = animalSerializer
  catSerializer.serialize(Cat("Felix"))

object ContravarianceExample extends  App {


}

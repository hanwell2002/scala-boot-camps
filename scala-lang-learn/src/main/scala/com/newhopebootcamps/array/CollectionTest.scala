package com.newhopebootcamps.array

import scala.collection.immutable.Queue

object CollectionTest extends App {
  //ListBuffer
  import scala.collection.mutable.ListBuffer
  val listBuffer = new ListBuffer[Int]
  listBuffer += 1
  listBuffer += 2
  println(listBuffer)

  //ArrayBuffer
  import scala.collection.mutable.ArrayBuffer
  val arrayBuffer = new ArrayBuffer[Int]()
  arrayBuffer += 1
  arrayBuffer += 2
  println(arrayBuffer)

  //不可变Queue
  val queue1 = Queue[Int]()
  //进队：一个元素
  val queue2 = queue1.enqueue(1)
  //进队：一个List
  val queue3 = queue2.enqueue(List(2,3,4,5,6))
  println(queue3)
  //拆分成两个部分
  val (element,rest) = queue3.dequeue
  println(element+":"+ rest)

  //可变Queue
  import scala.collection.mutable.Queue
  val queue_ =  Queue[String]()
  queue_ += "a"
  queue_ ++= List("b","c")
  println(queue_)
  //返回第一个元素
  println(queue_.dequeue())
  //打印剩余元素
  println(queue_)

  //Stack:FIFO
  import scala.collection.mutable.Stack
  val stack = new Stack[Int]
  stack.push(1)
  stack.push(2)
  stack.push(3)
  println(stack.pop())
  println(stack)
  println(stack.pop())
  println(stack)
}


/* 结果
ListBuffer(1, 2)
ArrayBuffer(1, 2)
Queue(1, 2, 3, 4, 5, 6)
1:Queue(2, 3, 4, 5, 6)
Queue(a, b, c)
a
Queue(b, c)
3
Stack(2, 1)
2
Stack(1)

 */
package com.abhi

import fastparse.noApi._
import fastparse.WhitespaceApi

object FastParsePOC {

   val White = WhitespaceApi.Wrapper{
      import fastparse.all._
      NoTrace(" ".rep)
   }

   def print(input : Parsed[String]): Unit = {
      input match {
         case Parsed.Success(value, index) => println(s"Success: $value $index")
         case f @ Parsed.Failure(error, line, col) => println(s"Error: $error $line $col ${f.extra.traced.trace}")
      }
   }

   //def printList(input: Parsed[Seq[(String, String)]] ): Unit = {
   def printList(input: Parsed[(String, String, Seq[(String, String)])]) : Unit = {
      input match {
         //case Parsed.Success(value, index) => value.map{case (name, index) => println(s"$name $index")}
         case Parsed.Success(value, index) =>
            println(s"${value._1} ${value._2}")
            value._3.foreach{case (name, index) => println(s"$name $index")}
         case f @ Parsed.Failure(error, line, col) => println(s"Error: $error $line $col ${f.extra.traced.trace}")
      }
   }

   def main(args: Array[String]) : Unit = {
      import White._
      val base = P("(" ~ (!")" ~ AnyChar).rep(1).! ~ ")")
      val foo = P("Foo".! ~ base)
      val bar = P("Bar".! ~ base)
      val baz = P("Baz".! ~ base)
      val foobarbaz = (foo | bar | baz)
      val parser = P("Expr" ~ "(" ~ foobarbaz ~ ",".? ~ (foobarbaz).rep(sep=",") ~ ")")
      val input3 = "Expr(Baz(20),Bar(10),Foo(30))"
      val parsed = parser.parse(input3)
      printList(parsed)
   }
}

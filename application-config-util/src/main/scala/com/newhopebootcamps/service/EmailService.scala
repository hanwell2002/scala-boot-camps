package com.newhopebootcamps.service

import com.newhopebootcamps.email.Email

class EmailService {

  def register(emailString: String): Unit = {
    val scalaCenterEmail = Email.fromString(emailString)
    scalaCenterEmail match {
      case Some(email) => println(
        s"""Registered an email
           |Username: ${email.username}
           |Domain name: ${email.domainName}
         """.stripMargin)
      case None => println("Error: could not parse email")
    }
  }

}

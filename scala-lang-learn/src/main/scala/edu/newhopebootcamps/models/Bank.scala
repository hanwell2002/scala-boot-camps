package edu.newhopebootcamps.models

import edu.newhopebootcamps.services.{AccountService, CustomerService, ProductService, StatisticsService}

class Bank(val name: String, val city: String, val country: String, val email: Email)
  extends CustomerService
    with ProductService
    with AccountService
    with StatisticsService {

  println(s"$name Established 2018.")

  override def toString: String = s"[$name]" +
    s" - $numCustomers customers" +
    s" - $numDepositsProducts deposits products" +
    s" - $numDepositsAccounts deposits accounts" +
    s" - $numLendingProducts lending products" +
    s" - $numLendingAccounts lending accounts"
}

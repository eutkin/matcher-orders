package net.eutkin.matcher.io

import net.eutkin.matcher.model.Customer

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait CustomersReader {

  def readCustomers(path : String) : List[Customer]

}

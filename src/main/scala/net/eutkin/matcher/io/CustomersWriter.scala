package net.eutkin.matcher.io

import net.eutkin.matcher.model.Customer

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait CustomersWriter {

  def writeCustomers(path : String, customers : Seq[Customer])

}

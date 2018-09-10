package net.eutkin.matcher.io

import java.io.{File, PrintWriter}

import net.eutkin.matcher.model.Customer

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait SimpleCustomersWriter extends CustomersWriter {

  override def writeCustomers(path : String, customers: Seq[Customer]): Unit = {
    val writer = new PrintWriter(new File(path))
    try {
      customers.foreach(writer.println)
    } finally {
      writer.close()
    }
  }
}

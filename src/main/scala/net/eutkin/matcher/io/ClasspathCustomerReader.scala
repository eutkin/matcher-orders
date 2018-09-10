package net.eutkin.matcher.io

import net.eutkin.matcher.model._

import scala.io.Source

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait ClasspathCustomerReader extends CustomersReader {

  override def readCustomers(path: String): List[Customer] = {
    val source = Source.fromResource("clients.txt")
    try {
      source.getLines()
        .map(line => line.split("\t"))
        .map(raw => {
          val balanceByPaper: Map[PaperKind, Int] = Map(
            A -> raw(2).toInt,
            B -> raw(3).toInt,
            C -> raw(4).toInt,
            D -> raw(5).toInt
          )
          Customer(raw(0), BigDecimal(raw(1)), balanceByPaper)
        })
        .toList
    } finally {
      source.close()
    }
  }
}


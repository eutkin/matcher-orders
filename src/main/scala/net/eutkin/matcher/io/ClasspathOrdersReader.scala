package net.eutkin.matcher.io

import net.eutkin.matcher.model.{BuyOrder, Order, SaleOrder, PaperKind}

import scala.io.Source

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait ClasspathOrdersReader extends OrdersReader {

  override def readOrders(path: String): List[Order] = {
    val source = Source.fromResource(path)
    try {
      source.getLines()
        .map(line => line.split("\t"))
        .map(raw => {
          val customerName: String = raw(0)
          val price: BigDecimal = BigDecimal(raw(3))
          val count: Int = raw(4).toInt
          val action = raw(1)
          val securityPaper: PaperKind = PaperKind(raw(2))
          action match {
            case "b" => BuyOrder(customerName, securityPaper, price, count)
            case "s" => SaleOrder(customerName, securityPaper, price, count, 0)
          }
        })
        .toList
    } finally {
      source.close()
    }
  }
}


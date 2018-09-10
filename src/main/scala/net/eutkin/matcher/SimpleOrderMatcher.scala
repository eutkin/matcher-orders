package net.eutkin.matcher

import net.eutkin.matcher.model.{BuyOrder, Customer, SaleOrder, PaperKind}

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait SimpleOrderMatcher extends OrderMatcher {

  private case class Key(paper: PaperKind, count: Int)

  override def matchOrders(customers: Seq[Customer], buyOrders: List[BuyOrder], saleOrders: List[SaleOrder]): Seq[Customer] = {
    val customersByName : Map[String, Customer]= customers.groupBy(_.name).mapValues(_.head)
    val paperToOrders: Map[Key, List[SaleOrder]] = saleOrders.groupBy(order => Key(order.securityPaper, order.count)).mapValues(_.sorted)

    for (buyOrder <- buyOrders) {
      val saleOrderOpt: Option[SaleOrder] = paperToOrders.getOrElse(Key(buyOrder.securityPaper, buyOrder.count), List.empty).headOption
      if (saleOrderOpt.nonEmpty) {
        val saleOrder = saleOrderOpt.get
        val buyer: Customer = customersByName(buyOrder.customerName)
        val seller: Customer = customersByName(saleOrder.customerName)

        buyer.balance = buyer.balance - (saleOrder.price * saleOrder.count)
        buyer.balanceByPaper = buyer.balanceByPaper.map(x => {
          if (x._1 == buyOrder.securityPaper) {
            (x._1, x._2 + buyOrder.count)
          } else {
            x
          }
        })

        seller.balance = seller.balance + (saleOrder.price * saleOrder.count)
        seller.balanceByPaper = seller.balanceByPaper.map(x => {
          if (x._1 == saleOrder.securityPaper) {
            (x._1, x._2 - saleOrder.count)
          } else {
            x
          }
        })
      }
    }
    customers
  }

}

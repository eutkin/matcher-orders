package net.eutkin.matcher

import net.eutkin.matcher.model.{BuyOrder, Customer, SaleOrder}

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait OrderMatcher {

  def  matchOrders(customers: Seq[Customer], buyOrders: List[BuyOrder],saleOrders : List[SaleOrder]) : Seq[Customer]

}

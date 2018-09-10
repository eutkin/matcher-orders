package net.eutkin.matcher

import net.eutkin.matcher.io._
import net.eutkin.matcher.model.{BuyOrder, Customer, Order, SaleOrder}

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
abstract class App extends CustomersReader with OrdersReader with CustomersWriter with OrderMatcher {

  def process(): Unit = {

    val customers: List[Customer] = readCustomers("clients.txt")
    val orders: List[Order] = readOrders("orders.txt")


    val buyOrders: List[BuyOrder] = filterByOrderType[BuyOrder](orders)
    val saleOrder: List[SaleOrder] = filterByOrderType(orders)

    val customersAfterBargaining: Seq[Customer] = matchOrders(customers, buyOrders, saleOrder)

    writeCustomers("results.txt", customersAfterBargaining)


  }

  private def filterByOrderType[T <: Order](orders: List[Order]): List[T] = {
    orders.filter(_.isInstanceOf[T]).map(_.asInstanceOf[T])
  }
}

object App {

  def main(args: Array[String]): Unit = {
    val app: App = new App
      with ClasspathCustomerReader
      with ClasspathOrdersReader
      with SimpleCustomersWriter
      with SimpleOrderMatcher
    app.process()
  }


}

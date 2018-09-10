package net.eutkin.matching

import net.eutkin.matcher.SimpleOrderMatcher
import net.eutkin.matcher.model._
import org.scalatest.FunSuite

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
class OrderMatchingTest extends FunSuite with SimpleOrderMatcher {



    test("Check order matcher") {
      val customers = List(
        Customer("c1", 1000, 100, 200, 300, 400),
        Customer("c2", 2000, 150, 250, 350, 450),
        Customer("c3", 1500, 200, 250, 350, 450)
      )

      val buyOrders = List(
        BuyOrder("c1", A, 100, 5),
      )

      val saleOrders = List(
        SaleOrder("c2", A, 100, 5, index = 0),
        SaleOrder("c3", A, 50, 5, index = 1)
      )

      val expected = List(
        Customer("c1", 750, 105, 200, 300, 400),
        Customer("c2", 2000, 150, 250, 350, 450),
        Customer("c3", 1750, 195, 250, 350, 450)
      )

      val customersAfterBargaining = matchOrders(customers, buyOrders, saleOrders)

      assert(customersAfterBargaining equals expected)
    }
}

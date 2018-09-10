package net.eutkin.matching

import net.eutkin.matcher.model.{A, SaleOrder}
import org.scalatest.FunSuite

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
class SaleOrderComparatorTest extends FunSuite {

  test("Test of sale order comparator") {
    val saleOrders = List(
      SaleOrder("", A, BigDecimal(1000), 2, 0),
      SaleOrder("", A, BigDecimal(999), 3, 1),
      SaleOrder("", A, BigDecimal(1000), 3, 2)
    )
    assert(saleOrders.sorted == List(
      SaleOrder("", A, BigDecimal(999), 3, 1),
      SaleOrder("", A, BigDecimal(1000), 2, 0),
      SaleOrder("", A, BigDecimal(1000), 3, 2)
    ))
  }

}

package net.eutkin.matcher.model

sealed abstract class Order {
  val customerName: String
  val securityPaper: PaperKind
  val price: BigDecimal
  val count: Int
}

case class SaleOrder(customerName: String, securityPaper: PaperKind, price: BigDecimal, count: Int, index : Int) extends Order with Ordered[SaleOrder] {
  /**
    * Sort by paper kind, price and order index
    * @param that other sale order
    * @return
    */
  override def compare(that: SaleOrder): Int = {
    var result = securityPaper.compare(that.securityPaper)
    if (result != 0) {
      result
    } else {
      result = price.compare(that.price)
      if (result != 0) {
        result
      } else {
        index.compare(that.index)
      }
    }
  }
}

case class BuyOrder(customerName: String, securityPaper: PaperKind, price: BigDecimal, count: Int) extends Order

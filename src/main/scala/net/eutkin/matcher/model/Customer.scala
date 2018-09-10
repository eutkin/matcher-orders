package net.eutkin.matcher.model

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
case class Customer(name: String, var balance: BigDecimal, var balanceByPaper: Map[PaperKind, Int]) {
  override def toString: String = String.join("\t", name, balance.toInt.toString, balanceByPaper.values.mkString("\t"))
}

object Customer {
  def apply(name: String, balance: BigDecimal, balanceA: Int, balanceB: Int, balanceC: Int, balanceD: Int): Customer = {
    Customer(name, balance, Map(A -> balanceA, B -> balanceB, C -> balanceC, D -> balanceD))
  }

  def apply(name: String, balance: BigDecimal, balanceByPaper: Map[PaperKind, Int]): Customer = {
    new Customer(name, balance, balanceByPaper)
  }
}

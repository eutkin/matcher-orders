package net.eutkin.matcher.io

import net.eutkin.matcher.model.Order

/**
  *
  *
  * @author Евгений Уткин (evgeny.utkin@mediascope.net)
  */
trait OrdersReader {

  def readOrders(path : String) : List[Order]

}

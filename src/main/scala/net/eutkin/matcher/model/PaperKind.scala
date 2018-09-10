package net.eutkin.matcher.model

sealed abstract class PaperKind extends Ordered[PaperKind] {
  override def compare(that: PaperKind): Int = this.getClass.getSimpleName.compare(that.getClass.getSimpleName)
}

case object A extends PaperKind

case object B extends PaperKind

case object C extends PaperKind

case object D extends PaperKind

object PaperKind {

  def apply(paperName: String): PaperKind = paperName match {
    case "A" => A
    case "B" => B
    case "C" => C
    case "D" => D
  }
}
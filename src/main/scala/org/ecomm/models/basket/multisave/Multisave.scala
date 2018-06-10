package org.ecomm.models.basket.multisave

import org.ecomm.models.{ Price, UPC }

case class Multisave(requirements: Seq[MultiSaveItemRequirements], discountAmount: Price)

object Multisave {
  private val multisaveList =
    Seq.empty[Multisave]

  lazy val multisaveMap: Map[UPC, Seq[Multisave]] =
    multisaveList
      .flatMap { multisave =>
        multisave.requirements.map {
          _.upc -> multisave
        }
      }
      .groupBy(_._1)
      .mapValues(_.map(_._2))
}
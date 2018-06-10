package org.ecomm

import org.ecomm.configuration.Configuration
import org.ecomm.guice.{ Akka, ModulesProvider }
import org.ecomm.logger.impl.ApplicationLogger
import akka.http.scaladsl.server.directives.HttpRequestWithEntity
import org.ecomm.helpers.BasketHelper

class Modules[T](
    val configuration: Configuration,
    val akka: Akka,
    val applicationLogger: ApplicationLogger
)(implicit requestWithEntity: HttpRequestWithEntity[T]) {
  lazy val basketHelper: BasketHelper[T] =
    new BasketHelper
}

object Modules {
  def apply[T](modulesProvider: ModulesProvider)(implicit requestWithEntity: HttpRequestWithEntity[T]): Modules[T] =
    new Modules(
      modulesProvider.configuration,
      modulesProvider.akka,
      modulesProvider.applicationLogger
    )
}
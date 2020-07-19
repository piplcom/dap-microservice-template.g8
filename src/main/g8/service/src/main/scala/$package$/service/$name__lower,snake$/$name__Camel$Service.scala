package $package$.service.$name;format="lower,snake"$

import com.typesafe.config.Config
import $package$.service.$name;format="lower,snake"$.ServiceApi._

import scala.concurrent.{ExecutionContext, Future}

object $name;format="Camel"$Service {
  def apply (config: Config)(implicit ec: ExecutionContext) = new $name;format="Camel"$Service(config)
}

class $name;format="Camel"$Service(config: Config)(implicit ec: ExecutionContext) {
  def echo(input: $name;format = "Camel"$Input) = {
    Future.successful ($name;format="Camel"$Output (input.text) )
  }
}

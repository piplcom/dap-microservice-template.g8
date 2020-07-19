package $package$.server

import akka.actor.ActorSystem
import kamon.Kamon

import scala.concurrent.ExecutionContext

object Server extends App {
  Kamon.init()

  object Service extends Service {
    override implicit val system: ActorSystem = ActorSystem("$name;format="lower,norm"$")

    override implicit def ec: ExecutionContext = ExecutionContext.global

    override def serviceName: String = CommonSettings(system.settings.config).name
  }

  Service.start()
}

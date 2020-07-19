package $package$.server.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

import com.pipl.dap.utils.os.MachineProps
import $package$.status.build.BuildInfo

import akka.http.scaladsl.server.Directives._
import spray.json._

object Node extends SprayJsonSupport with DefaultJsonProtocol{
  val route: Route =
    get {
      path("node") {
        complete(properties.toJson)
      }
    }

  private val properties = Map(
    "version"              -> BuildInfo.version,
    "git-commit"           -> BuildInfo.gitCommit,
    "scala-version"        -> BuildInfo.scalaVersion,
    "sbt-version"          -> BuildInfo.sbtVersion,
    "machine-name"         -> MachineProps.machineName,
    "build-time"           -> (BuildInfo.builtAtString + " UTC"),
    "build-machine"        -> BuildInfo.buildMachine,
    "java-version"         -> System.getProperty("java.version"),
    "java-runtime-name"    -> System.getProperty("java.runtime.name"),
    "java-runtime-version" -> System.getProperty("java.runtime.version"),
    "java-vendor"          -> System.getProperty("java.vendor"),
    "java-vm-name"         -> System.getProperty("java.vm.name"),
    "java-vm-version"      -> System.getProperty("java.vm.version"),
    "os-arch"              -> System.getProperty("os.arch"),
    "os-name"              -> System.getProperty("os.name"),
    "os-version"           -> System.getProperty("os.version")
  )
}
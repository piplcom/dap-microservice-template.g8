import scala.sys.process.Process
import scala.util.Try
name := "$name;format="norm"$"

libraryDependencies ++= Seq(
  "com.pipl.dap" %% "dap-microservice" % Versions.`microservice-lib`,

  "io.kamon" %% "kamon-logback" % Versions.`kamon-logback`,
  "io.kamon" %% "kamon-prometheus" % Versions.`kamon-prometheus`,

  "org.scalatest" %% "scalatest" % Versions.`scalatest` % Test
)

javaAgents += "io.kamon" % "kanela-agent" % Versions.`kanela-agent` % "compile"

bashScriptExtraDefines ++= Seq(
  """addJava "-Dconfig.file=\${app_home}/../conf/application.conf"""",
  """addJava "-Dlogback.configurationFile=\${app_home}/../conf/logback-prod.xml"""",
  """addApp "-server""""
)

mappings in Universal ++= Seq(
  //  (resourceDirectory in Compile).value / "prod.conf" -> "conf/prod.conf",
  (resourceDirectory in Compile).value / "application.conf" -> "conf/application.conf",
  (resourceDirectory in Compile).value / "logback-prod.xml" -> "conf/logback-prod.xml"
)

//// ############### Build info #######################
//
//buildInfoKeys := Seq[BuildInfoKey](
//  version,
//  scalaVersion,
//  sbtVersion,
//  BuildInfoKey.action("gitCommit") {
//    Try(Process("git rev-parse HEAD").lineStream.head).getOrElse("")
//  },
//  "buildMachine" -> {
//    val pidAtName = java.lang.management.ManagementFactory.getRuntimeMXBean.getName
//    pidAtName.dropWhile(_.isDigit).tail
//  }
//)
//
//buildInfoOptions += BuildInfoOption.BuildTime
//buildInfoOptions += BuildInfoOption.ToMap
//buildInfoPackage := "$package$.utils.build"

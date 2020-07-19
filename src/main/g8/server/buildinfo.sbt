import scala.sys.process.Process
import scala.util.Try
// ############### Build info #######################

buildInfoKeys := Seq[BuildInfoKey](
  version,
  scalaVersion,
  sbtVersion,
  BuildInfoKey.action("gitCommit") {
    Try(Process("git rev-parse HEAD").lineStream.head).getOrElse("")
  },
  "buildMachine" -> {
    val pidAtName = java.lang.management.ManagementFactory.getRuntimeMXBean.getName
    pidAtName.dropWhile(_.isDigit).tail
  }
)

buildInfoOptions += BuildInfoOption.BuildTime
buildInfoOptions += BuildInfoOption.ToMap
buildInfoPackage := "$package$.status.build"
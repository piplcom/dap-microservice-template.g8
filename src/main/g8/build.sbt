ThisBuild / organization := "com.pipl"
ThisBuild / scalaVersion := "$scalaVersion$"

//import scala.sys.process.Process
//import scala.util.Try
//import Versions._

lazy val service = (project in file ("service"))

lazy val server = (project in file ("server"))
  .enablePlugins(BuildInfoPlugin, DockerPlugin, JavaAppPackaging, JavaAgent)
  .dependsOn(service)

ThisBuild / publishMavenStyle := true
ThisBuild / publishArtifact := true
publishTo := Some("GitHub Package Registry" at "https://maven.pkg.github.com/piplcom/maven-packages/")
ThisBuild / credentials += Credentials(Path.userHome / ".ivy2" / "credentials")

ThisBuild / resolvers ++= Seq(
  "GitHub Package Registry" at "https://maven.pkg.github.com/piplcom/maven-packages/"
  //,"Artifactory" at "https://artifactory.pipl.pro/artifactory/Pipl_full/" //TODO understand if we ned IBM or not
)

//// #### Release Process
import sbtrelease.ReleaseStateTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  //  publishArtifacts,
  ReleaseStep(releaseStepTask(publish in Docker in server)),
  setNextVersion,
  commitNextVersion,
  pushChanges
)


addCommandAlias("githubRelease", "release with-defaults")

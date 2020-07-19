//// #### Release Process
//
//import sbtrelease.ReleaseStateTransformations._
//
//releaseProcess := Seq[ReleaseStep](
//  checkSnapshotDependencies,
//  inquireVersions,
//  runClean,
//  runTest,
//  setReleaseVersion,
//  commitReleaseVersion,
//  tagRelease,
//  //  publishArtifacts,
//  ReleaseStep(releaseStepTask(publish in Docker in server)),
//  setNextVersion,
//  commitNextVersion,
//  pushChanges
//)
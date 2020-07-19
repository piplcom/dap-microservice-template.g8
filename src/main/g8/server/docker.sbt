// Docker settings

maintainer in Docker := "Pipl"
dockerBaseImage := "openjdk:11.0.5-jre"
packageName in Docker := name.value
dockerExposedPorts := Seq(8080, 8081, 5266)
dockerRepository in Docker := Some("gcr.io/dev-collection")


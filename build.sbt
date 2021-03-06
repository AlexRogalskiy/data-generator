name := "data-generator"

version := "0.1"

scalaVersion := "2.13.5"

resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.5" % Test,
  "org.scalacheck" %% "scalacheck" % "1.15.3" % Test,
  "org.scalatestplus" %% "scalacheck-1-14" % "3.2.2.0" % Test




)
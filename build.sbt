name := "FastParsePOC"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
   "com.lihaoyi" %% "fastparse" % "0.3.7"
)

mainClass in (Compile, run) := Some("com.abhi.FastParsePOC")
Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / tlBaseVersion       := "0.5"
ThisBuild / tlCiReleaseBranches := Seq("main")
ThisBuild / crossScalaVersions  := Seq("3.3.1")

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(name := "lucuma-broadcast-channel")
  .settings(
    Compile / npmDependencies ++= Seq(
      "broadcast-channel" -> "5.3.0"
    ),
    /* disabled because it somehow triggers many warnings */
    scalaJSLinkerConfig ~= (_.withSourceMap(false)),
    tlFatalWarnings                         := false,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    Compile / doc / sources                 := Seq(),
    libraryDependencies += "org.typelevel" %%% "cats-effect" % "3.5.1",
    coverageEnabled                         := coverageEnabled.value && !tlIsScala3.value
  )

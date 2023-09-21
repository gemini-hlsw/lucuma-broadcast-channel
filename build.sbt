/* ScalablyTyped configuration */
enablePlugins(ScalablyTypedConverterGenSourcePlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / tlBaseVersion       := "0.5"
ThisBuild / tlCiReleaseBranches := Seq("main")
ThisBuild / crossScalaVersions  := Seq("3.3.1")

lazy val root = project
  .in(file("."))
  .settings(name := "lucuma-broadcast-channel")
  .settings(
    // shade into another package
    stOutputPackage                         := "lucuma.bc",
    /* javascript / typescript deps */
    Compile / npmDependencies ++= Seq(
      "broadcast-channel" -> "5.3.0"
    ),
    stSourceGenMode                         := SourceGenMode.ResourceGenerator,
    /* disabled because it somehow triggers many warnings */
    scalaJSLinkerConfig ~= (_.withSourceMap(false)),
    stUseScalaJsDom                         := true,
    tlFatalWarnings                         := false,
    Compile / doc / sources                 := Seq(),
    // focus only on these libraries
    stMinimize                              := Selection.AllExcept("broadcast-channel"),
    stMinimizeKeep ++= List("BroadcastChannel"),
    libraryDependencies += "org.typelevel" %%% "cats-effect" % "3.5.1",
    coverageEnabled                         := coverageEnabled.value && !tlIsScala3.value
  )
  .enablePlugins(ScalablyTypedConverterGenSourcePlugin)

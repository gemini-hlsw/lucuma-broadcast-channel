/* ScalablyTyped configuration */
enablePlugins(ScalablyTypedConverterGenSourcePlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  Seq(
    homepage := Some(url("https://github.com/gemini-hlsw/lucuma-svgdotjs")),
    Global / onChangedBuildSource := ReloadOnSourceChanges
  ) ++ lucumaPublishSettings
)

lazy val root = project
  .in(file("."))
  .settings(name := "lucuma-broadcast-channel")
  .settings(
    // shade into another package
    stOutputPackage := "lucuma.bc",
    /* javascript / typescript deps */
    Compile / npmDependencies ++= Seq(
      "broadcast-channel" -> "3.3.0"
    ),
    stSourceGenMode := SourceGenMode.ResourceGenerator,
    /* disabled because it somehow triggers many warnings */
    scalaJSLinkerConfig ~= (_.withSourceMap(false)),
    // because npm is slow
    useYarn := true,
    stUseScalaJsDom := true,
    scalacOptions ~= (_.filterNot(
      Set(
        // By necessity facades will have unused params
        "-Wdead-code",
        "-Wunused:params",
        "-Wunused:imports",
        "-Wunused:explicits"
      )
    )),
    sources in (Compile, doc) := Seq(),
    // focus only on these libraries
    stMinimize := Selection.AllExcept("broadcast-channel"),
    stMinimizeKeep ++= List("BroadcastChannel"),
    libraryDependencies += "org.typelevel" %%% "cats-effect" % "2.3.3"
  )
  .settings(lucumaScalaJsSettings: _*)
  .enablePlugins(ScalablyTypedConverterGenSourcePlugin)

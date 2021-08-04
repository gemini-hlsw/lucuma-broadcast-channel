resolvers += Resolver.bintrayRepo("oyvindberg", "converter")
resolvers += Resolver.sonatypeRepo("public")

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta35")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.3")

addSbtPlugin("edu.gemini" % "sbt-lucuma" % "0.4.0")

addSbtPlugin("com.geirsson" % "sbt-ci-release" % "1.5.7")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.7.0")


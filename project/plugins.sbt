resolvers += MavenRepository("sonatype-s01-snapshots",
                             "https://s01.oss.sonatype.org/content/repositories/snapshots"
)

val sbtLucumaVersion = "0.6.1"
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"          % "1.0.0-beta37")
addSbtPlugin("edu.gemini"                  % "sbt-lucuma-lib"         % sbtLucumaVersion)
addSbtPlugin("edu.gemini"                  % "sbt-lucuma-sjs-bundler" % sbtLucumaVersion)

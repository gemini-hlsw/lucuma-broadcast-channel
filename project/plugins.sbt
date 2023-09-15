resolvers += MavenRepository("sonatype-s01-snapshots",
                             "https://s01.oss.sonatype.org/content/repositories/snapshots"
)

resolvers += Resolver.sonatypeRepo("snapshots")

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter"  % "1.0.0-beta37")
addSbtPlugin("edu.gemini"                  % "sbt-lucuma-lib" % "0.11.7-4-9805163-SNAPSHOT")

import Settings._

// Global Settings
ThisBuild / scalaVersion    := "2.13.8"
ThisBuild / organization    := "ffakenz"
ThisBuild / versionScheme   := Some("early-semver")
ThisBuild / conflictManager := ConflictManager.latestRevision
ThisBuild / javacOptions ++= Seq("-source", "17", "-target", "17")
ThisBuild / scalacOptions ++= Seq("-Ymacro-annotations", "-target:jvm-17")

libraryDependencies += "com.devsisters" %% "shardcake-core"     % "2.0.3"
libraryDependencies += "com.devsisters" %% "shardcake-entities" % "2.0.3"

lazy val root = (project in file("."))
  .settings(
    name := "schema-validator"
  )
  .settings(CommandAliases.aliases)
  .aggregate(api, server)
  .settings(
    libraryDependencies += "com.devsisters" %% "shardcake-core"     % "2.0.3",
    libraryDependencies += "com.devsisters" %% "shardcake-entities" % "2.0.3"
  )

lazy val api = project
  .settings(
    name := "api"
  )
  .settings(scalafixSettings)
  .enablePlugins(ScalafixPlugin, BuildInfoPlugin)

unmanagedBase := file("lib")

lazy val server = project
  .settings(
    name := "server"
  )
  .settings(commonSettings, scalafixSettings)
  .enablePlugins(ScalafixPlugin, BuildInfoPlugin)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.JsonSchemaValidator.all,
      Dependencies.Logback.all
    ).flatten
  )
  .settings(dockerSettings, Docker / daemonUser := "daemon")
  .enablePlugins(DockerPlugin, AshScriptPlugin)
  .settings(
    libraryDependencies += "com.devsisters" %% "shardcake-core"     % "2.0.3",
    libraryDependencies += "com.devsisters" %% "shardcake-entities" % "2.0.3"
  )
  .dependsOn(api, sharding)

val zioInteropCatsVersion = "22.0.0.0"
val zioCatsInteropVersion = "3.3.0"
val akkaActorTypedVersion = "2.6.19"
val doobieVersion         = "0.13.4"
val shardcakeVersion      = "2.0.3"
val testContainersVersion = "0.40.9"
val zioVersion            = "2.0.2"

lazy val sharding = project
  .settings(
    libraryDependencies += "dev.zio" %% "zio-actors-persistence" % "0.1.0"
  )
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio"        %% "zio-test"                     % zioVersion            % "test",
      "dev.zio"        %% "zio-test-sbt"                 % zioVersion            % "test",
      "com.dimafeng"   %% "testcontainers-scala-core"    % testContainersVersion % "test",
      "dev.zio"        %% "zio-interop-cats"             % zioCatsInteropVersion,
      "com.devsisters" %% "shardcake-core"               % shardcakeVersion,
      "com.devsisters" %% "shardcake-entities"           % shardcakeVersion,
      "com.devsisters" %% "shardcake-manager"            % shardcakeVersion,
      "com.devsisters" %% "shardcake-health-k8s"         % shardcakeVersion,
      "com.devsisters" %% "shardcake-protocol-grpc"      % shardcakeVersion,
      "com.devsisters" %% "shardcake-serialization-kryo" % shardcakeVersion,
      "com.devsisters" %% "shardcake-storage-redis"      % shardcakeVersion
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

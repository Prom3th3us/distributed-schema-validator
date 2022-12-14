import sbt._

object Dependencies {
  object Versions {
    val zio                 = "2.0.2"
    val zioTest             = "2.0.0"
    val zioConfig           = "3.0.1"
    val zioLogging          = "2.1.2"
    val zioJson             = "0.3.0"
    val zioHttp             = "2.0.0-RC10"
    val jsonSchemaValidator = "2.2.14"
    val logback             = "1.4.4"
    val tapir               = "1.1.2"
  }

  object Zio {
    val zio             = "dev.zio"                     %% "zio"                     % Versions.zio
    val zioTest         = "dev.zio"                     %% "zio-test"                % Versions.zioTest % Test
    val zioTestSbt      = "dev.zio"                     %% "zio-test-sbt"            % Versions.zioTest % Test
    val zioTestMagnolia = "dev.zio"                     %% "zio-test-magnolia"       % Versions.zioTest % Test
    val zioConfig       = "dev.zio"                     %% "zio-config"              % Versions.zioConfig
    val zioTypesafe     = "dev.zio"                     %% "zio-config-typesafe"     % Versions.zioConfig
    val zioLogging      = "dev.zio"                     %% "zio-logging"             % Versions.zioLogging
    val zioLoggingSlf4j = "dev.zio"                     %% "zio-logging-slf4j"       % Versions.zioLogging
    val zioJson         = "dev.zio"                     %% "zio-json"                % Versions.zioJson
    val zioHttp         = "io.d11"                      %% "zhttp"                   % Versions.zioHttp
    val tapirZio        = "com.softwaremill.sttp.tapir" %% "tapir-zio"               % Versions.tapir
    val tapirZioHttp    = "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server"   % Versions.tapir
    val tapirZioJson    = "com.softwaremill.sttp.tapir" %% "tapir-json-zio"          % Versions.tapir
    val tapirSwagger    = "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % Versions.tapir

    val all = Seq(
      zio,
      zioTest,
      zioTestSbt,
      zioTestMagnolia,
      zioConfig,
      zioTypesafe,
      zioLogging,
      zioLoggingSlf4j,
      zioJson,
      zioHttp,
      tapirZio,
      tapirZioHttp,
      tapirZioJson,
      tapirSwagger
      // "com.github.ffakenz" % "zio-actors-shardcake" % "main-SNAPSHOT"
    )
  }

  object JsonSchemaValidator {
    val jsonSchemaValidator = "com.github.java-json-tools" % "json-schema-validator" % Versions.jsonSchemaValidator

    val all = Seq(
      jsonSchemaValidator
    )
  }

  object Logback {
    val logback = "ch.qos.logback" % "logback-classic" % Versions.logback % Runtime

    val all = Seq(
      logback
    )
  }
}

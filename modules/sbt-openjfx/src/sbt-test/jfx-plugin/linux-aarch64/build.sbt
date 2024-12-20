name := "linux-arm-test"

version := "0.1.0"

enablePlugins(JFXPlugin)

jfxModules := Set(JFXModule.Controls, JFXModule.Custom("javafx-fxml"))

jfxVersion := "23.0.1"

val assertDependencies = taskKey[Unit]("Asserts that the correct dependencies are present as expected")

assertDependencies := {
  val logger = streams.value.log
  val osName = System.getProperty("os.name").toLowerCase
  val osArch = System.getProperty("os.arch").toLowerCase

  if (osName.contains("lin") && (osArch.contains("arm") || osArch.contains("aarch64"))) {
    val expectedDependencies = Set(
      "org.openjfx" % "javafx-controls" % "23.0.1" classifier "linux-aarch64",
      "org.openjfx" % "javafx-fxml" % "23.0.1" classifier "linux-aarch64"
    )
    val actualModules = libraryDependencies.value.toSet

    val missingDependencies = expectedDependencies.diff(actualModules)
    if (missingDependencies.nonEmpty) {
      sys.error(s"Missing required modules: ${missingDependencies.mkString(", ")}")
    }
  } else {
    logger.warn("Skipping assertion for non-Linux ARM platform")
  }
}

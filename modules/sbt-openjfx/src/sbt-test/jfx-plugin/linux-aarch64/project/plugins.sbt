val projectVersion = sys.props.getOrElse("plugin.version", sys.error("project.version is not set"))
addSbtPlugin("africa.shuwari.sbt" % "sbt-openjfx" % projectVersion)

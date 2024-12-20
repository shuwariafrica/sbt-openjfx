# sbt-openjfx

`sbt-openjfx` is an SBT plugin designed to simplify the usage of JavaFX with SBT. This plugin helps in managing JavaFX
dependencies and configurations, making it easier to develop JavaFX applications using SBT.

## Features

- **Platform Detection**: Automatically detects the current platform (Linux, macOS, Windows) and architecture (x64,
  AArch64) to configure the appropriate JavaFX dependencies.
- **Module Management**: Allows selection of specific JavaFX modules (e.g., `javafx-base`, `javafx-controls`,
  `javafx-fxml`, etc.) to include in the project.
- **Version Management**: Supports specifying the version of JavaFX libraries to be resolved and used in the project.

## Usage

To use the `sbt-openjfx` plugin, add the following to your `project/plugins.sbt` file:

```scala
addSbtPlugin("africa.shuwari.sbt" % "sbt-openjfx" % "[latest tag]")
```

In your `build.sbt` file, you can configure the plugin settings as follows:

```scala
name := "my-javafx-app"

version := "0.1.0"

enablePlugins(JFXPlugin)

jfxModules := Set(JFXModule.Controls, JFXModule.Fxml)

jfxVersion := "21.0.5"
```

TThis example sets up a JavaFX project with the `javafx-controls` and `javafx-fxml` modules, using version `21.0.5` of
the JavaFX libraries, automatically handling the platform-specific dependencies.

## Settings

The following `settingKey`s are available:

- `jfxPlatform` \[JFXPlatform\]: The currently detected JFX platform.
- `jfxModules` \[Set\[JFXModule\]\]: The selected JFX modules.
- `jfxDependencies` \[Seq\[ModuleID\]\]: The generated JFX dependencies.
- `jfxVersion` \[String\]: The version of JFX libraries to be resolved.

## Available JFX Modules

The following `JFXModule` objects are available:

| Reference            | Name              |
|----------------------|-------------------|
| `JFXModule.Base`     | `javafx-base`     |
| `JFXModule.Controls` | `javafx-controls` |
| `JFXModule.Fxml`     | `javafx-fxml`     |
| `JFXModule.Graphics` | `javafx-graphics` |
| `JFXModule.Media`    | `javafx-media`    |
| `JFXModule.Swing`    | `javafx-swing`    |
| `JFXModule.Web`      | `javafx-web`      |

You can also define custom modules using:

```scala
JFXModule.Custom("custom-module-name")
```

## Contributing

Contributions are welcome! Please feel free to submit issues and pull requests to help improve the plugin.

## License

Copyright © Shuwari Africa Ltd. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License at:

[`http://www.apache.org/licenses/LICENSE-2.0`](https://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

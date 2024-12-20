/*****************************************************************
 * Copyright © Shuwari Africa Ltd. All rights reserved.          *
 *                                                               *
 * Shuwari Africa Ltd. licenses this file to you under the terms *
 * of the Apache License Version 2.0 (the "License"); you may    *
 * not use this file except in compliance with the License. You  *
 * may obtain a copy of the License at:                          *
 *                                                               *
 *     https://www.apache.org/licenses/LICENSE-2.0               *
 *                                                               *
 * Unless required by applicable law or agreed to in writing,    *
 * software distributed under the License is distributed on an   *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,  *
 * either express or implied. See the License for the specific   *
 * language governing permissions and limitations under the      *
 * License.                                                      *
 *****************************************************************/
package africa.shuwari.sbt

import sbt.*
import sbt.Keys.*

object JFXPlugin extends AutoPlugin {

  object autoImport {
    val jfxPlatform = settingKey[JFXPlatform]("The current JFX platform")
    val jfxModules = settingKey[Set[JFXModule]]("The selected JFX modules")
    val jfxDependencies = settingKey[Seq[ModuleID]]("The generated JFX dependencies")
    val jfxVersion = settingKey[String]("The version of JFX libraries to be resolved")

    type JFXModule = africa.shuwari.sbt.JFXModule
    val JFXModule = africa.shuwari.sbt.JFXModule

    type JFXPlatform = africa.shuwari.sbt.JFXPlatform
    val JFXPlatform = africa.shuwari.sbt.JFXPlatform
  }

  override def buildSettings: Seq[Def.Setting[_]] = Seq(
    autoImport.jfxPlatform := determinePlatform.value
  )

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    autoImport.jfxModules := Set.empty,
    autoImport.jfxVersion := "21.0.5", // Set the most recent LTS version available
    autoImport.jfxDependencies := {
      val platform = autoImport.jfxPlatform.value
      val modules = autoImport.jfxModules.value
      val version = autoImport.jfxVersion.value
      modules.map { module =>
        ("org.openjfx" % module.name % version).classifier(platform.classifier)
      }.toSeq
    },
    libraryDependencies ++= autoImport.jfxDependencies.value
  )

  private def determinePlatform: Def.Initialize[JFXPlatform] = Def.setting {
    val log = sLog.value
    val os = System.getProperty("os.name").toLowerCase
    val arch = System.getProperty("os.arch").toLowerCase
    (os, arch) match {
      case (os, arch) if os.contains("lin") && arch.matches(".*(aarch64|arm64).*") => JFXPlatform.LinuxAarch64
      case (os, arch) if os.contains("mac") && arch.matches(".*(aarch64|arm64).*") => JFXPlatform.MacOSAarch64
      case (os, arch) if os.contains("win") && arch.matches(".*(aarch64|arm64).*") => JFXPlatform.WindowsAarch64
      case (os, arch) if os.contains("lin") && arch.matches(".*(amd64|x86_64).*")  => JFXPlatform.Linux
      case (os, arch) if os.contains("mac") && arch.matches(".*(amd64|x86_64).*")  => JFXPlatform.MacOSX
      case (os, arch) if os.contains("win") && arch.matches(".*(amd64|x86_64).*")  => JFXPlatform.Windows
      case _ =>
        log.error(s"Unsupported JavaFX platform: $os $arch")
        JFXPlatform.Unknown
    }
  }

}

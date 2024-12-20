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

sealed trait JFXPlatform {
  def classifier: String
}

object JFXPlatform {
  case object Linux extends JFXPlatform {
    val classifier: String = "linux"
  }
  case object MacOSX extends JFXPlatform {
    val classifier: String = "mac"
  }
  case object Windows extends JFXPlatform {
    val classifier: String = "win"
  }
  case object LinuxAarch64 extends JFXPlatform {
    val classifier: String = "linux-aarch64"
  }
  case object MacOSAarch64 extends JFXPlatform {
    val classifier: String = "mac-aarch64"
  }
  case object WindowsAarch64 extends JFXPlatform {
    val classifier: String = "win-aarch64"
  }

  private[sbt] case object Unknown extends JFXPlatform {
    val classifier: String = "unknown"
  }
}

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

sealed trait JFXModule {
  def name: String
}

object JFXModule {

  def apply(module: String): JFXModule = Custom(module)

  case object Base extends JFXModule {
    val name: String = "javafx-base"
  }

  case object Controls extends JFXModule {
    val name: String = "javafx-controls"
  }

  case object Fxml extends JFXModule {
    val name: String = "javafx-fxml"
  }

  case object Graphics extends JFXModule {
    val name: String = "javafx-graphics"
  }

  case object Media extends JFXModule {
    val name: String = "javafx-media"
  }

  case object Swing extends JFXModule {
    val name: String = "javafx-swing"
  }

  case object Web extends JFXModule {
    val name: String = "javafx-web"
  }

  final case class Custom(override val name: String) extends JFXModule

}

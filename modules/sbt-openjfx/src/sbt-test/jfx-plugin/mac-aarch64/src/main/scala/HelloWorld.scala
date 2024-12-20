package com.example

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class HelloWorld extends Application {

  override def start(stage: Stage): Unit = {
    val label = new Label("Hello, JavaFX!")
    val scene = new Scene(label, 400, 200)
    stage.setScene(scene)
    stage.setTitle("Hello World")
    stage.show()
  }

  def main(args: Array[String]): Unit =
    Application.launch(classOf[HelloWorld], args: _*)
}

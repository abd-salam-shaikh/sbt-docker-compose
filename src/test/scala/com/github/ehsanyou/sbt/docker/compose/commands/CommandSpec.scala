package com.github.ehsanyou.sbt.docker.compose.commands

import com.github.ehsanyou.sbt.docker.compose.DataTypes.DockerComposeOption
import org.scalatest.Matchers
import org.scalatest.WordSpec

class CommandSpec extends WordSpec with Matchers {

  "Command #asString" should {

    "build command with multiple list of options" in {
      Command.asString(
        "test",
        Seq(DockerComposeOption("-a", "value"), DockerComposeOption("-b", "value")),
        Seq(DockerComposeOption("-c", "value"), DockerComposeOption("-d", "value"))
      )() shouldEqual "test -a value -b value -c value -d value"
    }

    "build command with single list of options" in {
      Command.asString("test", Seq(DockerComposeOption("-a", "value"), DockerComposeOption("-b", "value")))() shouldEqual "test -a value -b value"
    }

    "build command with empty list of options" in {
      Command.asString("test", Seq())() shouldEqual "test"
    }

    "build command with two list of options one of them empty" in {
      Command.asString("test", Seq(), Seq(DockerComposeOption("-c", "value"), DockerComposeOption("-d", "value")))() shouldEqual "test -c value -d value"
    }

    "build command exluding -p option" in {
      Command.asString("test", Seq(DockerComposeOption("-p", "value"), DockerComposeOption("-d", "value")))("-p") shouldEqual "test -d value"
    }
  }
}

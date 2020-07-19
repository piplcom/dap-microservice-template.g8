package $package$.server

import com.typesafe.config.Config

object CommonSettings {
  def apply(config: Config): CommonSettings = new CommonSettings(config)
}

class CommonSettings(config: Config) {
  val name = config.getString("common.name")
}
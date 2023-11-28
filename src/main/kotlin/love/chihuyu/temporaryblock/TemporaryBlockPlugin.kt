package love.chihuyu.temporaryblock

import org.bukkit.plugin.java.JavaPlugin

class TemporaryBlockPlugin : JavaPlugin() {
    companion object {
        lateinit var TemporaryBlockPlugin: JavaPlugin
    }

    init {
        TemporaryBlockPlugin = this
    }
}

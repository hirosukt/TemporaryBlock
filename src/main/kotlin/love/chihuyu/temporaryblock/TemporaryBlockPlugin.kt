package love.chihuyu.temporaryblock

import love.chihuyu.temporaryblock.listener.BlockPlaceListener
import love.chihuyu.temporaryblock.recipe.TemporaryBlockRecipe
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin

class TemporaryBlockPlugin : JavaPlugin() {
    companion object {
        lateinit var TemporaryBlockPlugin: JavaPlugin
        val mMessage: MiniMessage = MiniMessage.miniMessage()
        const val isTemporaryNBT = "is_temporary"
    }

    init {
        TemporaryBlockPlugin = this
    }

    override fun onEnable() {
        super.onEnable()
        logger.info("Plugin has loaded.")

        TemporaryBlockRecipe.register()

        listOf(
            BlockPlaceListener
        ).forEach {
            server.pluginManager.registerEvents(it, this)
        }
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("Plugin has unloaded.")
    }
}

package love.chihuyu.temporaryblock.listener

import de.tr7zw.nbtapi.NBT
import love.chihuyu.chihuyusnippets.schedular.Schedular.sync
import love.chihuyu.temporaryblock.TemporaryBlockPlugin.Companion.TemporaryBlockPlugin
import love.chihuyu.temporaryblock.TemporaryBlockPlugin.Companion.isTemporaryNBT
import love.chihuyu.temporaryblock.recipe.TemporaryBlockRecipe
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

object BlockPlaceListener: Listener {

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        var isTemporary = false
        NBT.get(event.itemInHand) {
            isTemporary = it.getBoolean(isTemporaryNBT)
        }

        val location = event.blockPlaced.location

        if (!isTemporary) return

        var counter = 0
        TemporaryBlockPlugin.sync(0, 1) {
            if (counter > 100 &&
                event.player.world.getNearbyEntities(location, -1.5, 2.5, .5).isEmpty() &&
                event.player.world.getNearbyEntities(location, .5, 2.5, -1.5).isEmpty()) {
                event.blockPlaced.breakNaturally(true)
                event.player.inventory.addItem(TemporaryBlockRecipe.temporaryBlock.apply { amount = 1 })
                cancel()
            }
            counter += 1
        }
    }
}
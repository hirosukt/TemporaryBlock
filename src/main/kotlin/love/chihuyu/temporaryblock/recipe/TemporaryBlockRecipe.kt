package love.chihuyu.temporaryblock.recipe

import de.tr7zw.nbtapi.NBT
import love.chihuyu.temporaryblock.TemporaryBlockPlugin.Companion.TemporaryBlockPlugin
import love.chihuyu.temporaryblock.TemporaryBlockPlugin.Companion.isTemporaryNBT
import love.chihuyu.temporaryblock.TemporaryBlockPlugin.Companion.mMessage
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

object TemporaryBlockRecipe {
    val temporaryBlock = ItemStack(Material.GLASS).apply {
        itemMeta = itemMeta.apply {
            displayName(mMessage.deserialize("<aqua><bold>Temporary Block"))
        }

        addUnsafeEnchantment(Enchantment.MENDING, 0)
        addItemFlags(ItemFlag.HIDE_ENCHANTS)
        lore(listOf(mMessage.deserialize("<white>置くと五秒後に消えるブロック。"), mMessage.deserialize("<white>なんか獣臭い。")))

        NBT.modify(this) {
            it.setBoolean(isTemporaryNBT, true)
        }

        amount = 2
    }

    val recipe = ShapedRecipe(NamespacedKey(TemporaryBlockPlugin, "temporaryblock"), temporaryBlock).apply {
        shape(" G ", "GFG", " G ")
        setIngredient('G', Material.GLASS)
        setIngredient('F', Material.FEATHER)
    }

    fun register() {
        TemporaryBlockPlugin.server.addRecipe(recipe)
    }
}
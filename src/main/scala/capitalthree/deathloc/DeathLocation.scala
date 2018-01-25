package capitalthree.deathloc

import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.text.TextComponentString
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.fml.common.Mod

@Mod(modid = "deathlocation", version = "1", name = "DeathLocation", modLanguage = "scala", acceptableRemoteVersions="*")
object DeathLocation {
  @EventHandler def init(e: FMLPreInitializationEvent) = MinecraftForge.EVENT_BUS.register(DieHandler)
}

object DieHandler {
  @SubscribeEvent(priority = EventPriority.LOWEST) def died(e: LivingDeathEvent) = e.getEntity match {
    case player: EntityPlayerMP => player.sendMessage(new TextComponentString(
      s"Death location: ${player.posX.round}, ${player.posY.round}, ${player.posZ.round}"
    ))
    case _ =>
  }
}
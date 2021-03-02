package fb;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import fb.biome.FBBiome;
import fb.database.FBDatabase.*;
import fb.database.FBFaction;
import fb.entity.FBEntity;
import fb.proxy.FBServerProxy;
import got.common.util.GOTCommander;
import net.minecraft.util.ResourceLocation;

@Mod(modid = "fb", version = "Frey & Bolton", name = "Game of Thrones: DLC", dependencies = "required-after:got")
public class FreyBolton {
	@SidedProxy(clientSide = "fb.proxy.FBClientProxy", serverSide = "fb.proxy.FBServerProxy")
	public static FBServerProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		FBFaction.preInit();
		FBBiome.preInit();
		FBCapes.preInit();
		FBShields.preInit();
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event) {
		GOTCommander.setServerMapImage(new ResourceLocation("fb:map/map.png"));
		FBFaction.onInit();
		FBEntity.onInit();
		proxy.onInit();
	}
}
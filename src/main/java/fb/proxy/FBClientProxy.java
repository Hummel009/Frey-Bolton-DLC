package fb.proxy;

import got.common.util.GOTCommander;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.util.ResourceLocation;

public class FBClientProxy extends FBServerProxy implements IResourceManagerReloadListener {

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		GOTCommander.setClientMapImage(new ResourceLocation("fb:map/map.png"));
	}

	@Override
	public void onInit() {
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new FBClientProxy());
	}
}
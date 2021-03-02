package fb.entity.frey;

import got.common.entity.other.GOTBannerBearer;
import got.common.item.other.GOTItemBanner;
import net.minecraft.world.World;

public class FBEntityFreyBannerBearer extends FBEntityFreySoldier implements GOTBannerBearer {
	public FBEntityFreyBannerBearer(World world) {
		super(world);
	}

	@Override
	public GOTItemBanner.BannerType getBannerType() {
		return GOTItemBanner.BannerType.FREY;
	}
}

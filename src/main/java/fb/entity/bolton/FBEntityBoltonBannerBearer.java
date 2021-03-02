package fb.entity.bolton;

import got.common.entity.other.GOTBannerBearer;
import got.common.item.other.GOTItemBanner;
import net.minecraft.world.World;

public class FBEntityBoltonBannerBearer extends FBEntityBoltonSoldier implements GOTBannerBearer {
	public FBEntityBoltonBannerBearer(World world) {
		super(world);
	}

	@Override
	public GOTItemBanner.BannerType getBannerType() {
		return GOTItemBanner.BannerType.BOLTON;
	}
}

package fb.entity;

import fb.database.FBFaction;
import fb.entity.bolton.*;
import fb.entity.frey.*;
import got.common.entity.GOTEntity;
import got.common.entity.other.GOTEntityRegistry;

public class FBEntity {
	public static int id = GOTEntity.id;

	public static void onInit() {
		GOTEntityRegistry.register(FBEntityBoltonMan.class, "BoltonMan", id++, FBFaction.BOLTON);
		GOTEntityRegistry.register(FBEntityBoltonLevyman.class, "BoltonLevyman", id++, FBFaction.BOLTON);
		GOTEntityRegistry.register(FBEntityBoltonLevymanArcher.class, "BoltonLevymanArcher", id++, FBFaction.BOLTON);
		GOTEntityRegistry.register(FBEntityBoltonSoldier.class, "BoltonSoldier", id++, FBFaction.BOLTON);
		GOTEntityRegistry.register(FBEntityBoltonSoldierArcher.class, "BoltonSoldierArcher", id++, FBFaction.BOLTON);
		GOTEntityRegistry.register(FBEntityBoltonBannerBearer.class, "BoltonBannerBearer", id++, FBFaction.BOLTON);
		GOTEntityRegistry.register(FBEntityBoltonCaptain.class, "BoltonCaptain", id++, FBFaction.BOLTON);

		GOTEntityRegistry.register(FBEntityFreyMan.class, "FreyMan", id++, FBFaction.FREY);
		GOTEntityRegistry.register(FBEntityFreyLevyman.class, "FreyLevyman", id++, FBFaction.FREY);
		GOTEntityRegistry.register(FBEntityFreyLevymanArcher.class, "FreyLevymanArcher", id++, FBFaction.FREY);
		GOTEntityRegistry.register(FBEntityFreySoldier.class, "FreySoldier", id++, FBFaction.FREY);
		GOTEntityRegistry.register(FBEntityFreySoldierArcher.class, "FreySoldierArcher", id++, FBFaction.FREY);
		GOTEntityRegistry.register(FBEntityFreyBannerBearer.class, "FreyBannerBearer", id++, FBFaction.FREY);
		GOTEntityRegistry.register(FBEntityFreyCaptain.class, "FreyCaptain", id++, FBFaction.FREY);
	}
}
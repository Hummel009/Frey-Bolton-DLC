package fb.database;

import got.common.GOTDimension.DimensionRegion;
import got.common.faction.*;
import got.common.util.GOTCommander;
import got.common.world.map.GOTWaypoint;

public class FBFaction {
	public static final GOTFaction FREY = GOTCommander.addFaction("FREY", 3955986, DimensionRegion.WESTEROS);
	public static final GOTFaction BOLTON = GOTCommander.addFaction("BOLTON", 0x6e0000, DimensionRegion.WESTEROS);

	public static void preInit() {
		FREY.isolationist = true;
		FREY.isViolent = true;
		BOLTON.isViolent = true;
	}

	public static void onInit() {
		FBFaction.setupRelations();
		FBFaction.setupMapInfo();
		FBFaction.setupControlZones();
		FBFaction.setupRanks();
	}

	private static void setupMapInfo() {
		FREY.factionMapInfo = new GOTFactionProfile(GOTWaypoint.TwinsLeft, 100);
		BOLTON.factionMapInfo = new GOTFactionProfile(GOTWaypoint.Dreadfort, 100);
	}

	private static void setupRanks() {
		BOLTON.addRank(10.0f, "guest").makeTitle();
		BOLTON.addRank(50.0f, "friend").makeTitle();
		BOLTON.addRank(100.0f, "warrior").setPledgeRank().makeTitle();
		BOLTON.addRank(200.0f, "leutenant").makeTitle();
		BOLTON.addRank(500.0f, "kingsguard").makeTitle();
		BOLTON.addRank(1000.0f, "commander").makeTitle();
		BOLTON.addRank(2000.0f, "general").makeTitle();

		FREY.addRank(10.0f, "guest").makeTitle();
		FREY.addRank(50.0f, "friend").makeTitle();
		FREY.addRank(100.0f, "warrior").setPledgeRank().makeTitle();
		FREY.addRank(200.0f, "leutenant").makeTitle();
		FREY.addRank(500.0f, "kingsguard").makeTitle();
		FREY.addRank(1000.0f, "commander").makeTitle();
		FREY.addRank(2000.0f, "general").makeTitle();
	}

	private static void setupControlZones() {
		FREY.addControlZone(new GOTFactionSphere(GOTWaypoint.TwinsLeft, 644));
		BOLTON.addControlZone(new GOTFactionSphere(GOTWaypoint.Dreadfort, 300));
	}

	private static void setupRelations() {
		GOTFactionRelations.setRelations(FREY, BOLTON, GOTFactionRelations.Relation.FRIEND);
		GOTFactionRelations.setRelations(FREY, GOTFaction.WESTERLANDS, GOTFactionRelations.Relation.ALLY);
		GOTFactionRelations.setRelations(FREY, GOTFaction.RIVERLANDS, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(FREY, GOTFaction.NORTH, GOTFactionRelations.Relation.MORTAL_ENEMY);
		GOTFactionRelations.setRelations(BOLTON, GOTFaction.WESTERLANDS, GOTFactionRelations.Relation.ALLY);
		GOTFactionRelations.setRelations(BOLTON, GOTFaction.RIVERLANDS, GOTFactionRelations.Relation.ENEMY);
		GOTFactionRelations.setRelations(BOLTON, GOTFaction.NORTH, GOTFactionRelations.Relation.MORTAL_ENEMY);
	}
}
package fb.biome;

import java.util.Random;

import fb.entity.frey.FBEntityFreyLevyman;
import fb.entity.frey.FBEntityFreyLevymanArcher;
import fb.entity.frey.FBEntityFreyMan;
import fb.entity.frey.FBEntityFreySoldier;
import fb.entity.frey.FBEntityFreySoldierArcher;
import got.client.sound.GOTBiomeMusic;
import got.client.sound.GOTBiomeMusic.MusicRegion;
import got.common.database.GOTInvasions;
import got.common.database.GOTSpawnList;
import got.common.entity.other.GOTEntityScrapTrader;
import got.common.world.biome.westeros.GOTBiomeWesteros;
import got.common.world.map.GOTWaypoint;
import got.common.world.map.GOTWaypoint.WaypointRegion;
import got.common.world.spawning.GOTBiomeSpawnList;
import got.common.world.spawning.GOTBiomeSpawnList.SpawnListContainer;
import got.common.world.spawning.GOTEventSpawner;
import got.common.world.spawning.GOTSpawnEntry;
import got.common.world.spawning.GOTSpawner;
import got.common.world.structure.other.GOTStructureTower;
import net.minecraft.world.World;

public class FBBiomeFrey extends GOTBiomeWesteros {
	public static GOTSpawnList FREY_MEN = new GOTSpawnList(new GOTSpawnEntry(FBEntityFreyMan.class, 10, 4, 6));
	public static GOTSpawnList FREY_SOLDIERS = new GOTSpawnList(new GOTSpawnEntry(FBEntityFreyLevyman.class, 20, 2, 4), new GOTSpawnEntry(FBEntityFreyLevymanArcher.class, 10, 2, 8), new GOTSpawnEntry(FBEntityFreySoldier.class, 20, 2, 4), new GOTSpawnEntry(FBEntityFreySoldierArcher.class, 10, 2, 8));

	public FBBiomeFrey(int i, boolean major) {
		super(i, major);
		SpawnListContainer[] container0 = new SpawnListContainer[2];
		container0[0] = GOTBiomeSpawnList.entry(FREY_MEN, 15).setSpawnChance(100);
		container0[1] = GOTBiomeSpawnList.entry(FREY_SOLDIERS, 10).setSpawnChance(100);
		npcSpawnList.newFactionList(100, 0.0f).add(container0);

		SpawnListContainer[] container1 = new SpawnListContainer[1];
		container1[0] = GOTBiomeSpawnList.entry(GOTSpawnList.RIVERLANDS_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container1);

		SpawnListContainer[] container2 = new SpawnListContainer[1];
		container2[0] = GOTBiomeSpawnList.entry(GOTSpawnList.NORTH_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container2);

		SpawnListContainer[] container3 = new SpawnListContainer[1];
		container3[0] = GOTBiomeSpawnList.entry(GOTSpawnList.WALKERS_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container3);
		registerTravellingTrader(GOTEntityScrapTrader.class);

		invasionSpawns.addInvasion(GOTInvasions.RIVERLANDS, GOTEventSpawner.EventChance.COMMON);
		invasionSpawns.addInvasion(GOTInvasions.NORTH, GOTEventSpawner.EventChance.COMMON);

		GOTStructureTower tower = new GOTStructureTower(this, 0.0f);
		tower.affix(GOTWaypoint.TwinsRight, -2, 0, 3);
		tower.affix(GOTWaypoint.TwinsLeft, 1, 0, 1);
		decorator.addVillage(tower);
	}

	@Override
	public WaypointRegion getBiomeWaypoints() {
		return WaypointRegion.RIVERLANDS;
	}

	@Override
	public MusicRegion getBiomeMusic() {
		return GOTBiomeMusic.WESTEROS.getSubregion("frey");
	}

	@Override
	public void decorate(World world, Random random, int i, int k) {
		super.decorate(world, random, i, k);
		if (GOTSpawner.WalderFrey.generatesAt(world, i, k)) {
			new GOTSpawner.WalderFrey(false).generateWithSetRotation(world, random, i, world.getTopSolidOrLiquidBlock(i, k), i, 2);
		}
	}
}
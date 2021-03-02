package fb.biome;

import java.util.Random;

import fb.entity.bolton.FBEntityBoltonLevyman;
import fb.entity.bolton.FBEntityBoltonLevymanArcher;
import fb.entity.bolton.FBEntityBoltonMan;
import fb.entity.bolton.FBEntityBoltonSoldier;
import fb.entity.bolton.FBEntityBoltonSoldierArcher;
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

public class FBBiomeBolton extends GOTBiomeWesteros {
	public static GOTSpawnList BOLTON_MEN = new GOTSpawnList(new GOTSpawnEntry(FBEntityBoltonMan.class, 10, 4, 6));
	public static GOTSpawnList BOLTON_SOLDIERS = new GOTSpawnList(new GOTSpawnEntry(FBEntityBoltonLevyman.class, 20, 2, 4), new GOTSpawnEntry(FBEntityBoltonLevymanArcher.class, 10, 2, 8), new GOTSpawnEntry(FBEntityBoltonSoldier.class, 20, 2, 4), new GOTSpawnEntry(FBEntityBoltonSoldierArcher.class, 10, 2, 8));

	public FBBiomeBolton(int i, boolean major) {
		super(i, major);
		registerTravellingTrader(GOTEntityScrapTrader.class);

		SpawnListContainer[] container0 = new SpawnListContainer[2];
		container0[0] = GOTBiomeSpawnList.entry(BOLTON_MEN, 15).setSpawnChance(100);
		container0[1] = GOTBiomeSpawnList.entry(BOLTON_SOLDIERS, 10).setSpawnChance(100);
		npcSpawnList.newFactionList(100, 0.0f).add(container0);

		SpawnListContainer[] container1 = new SpawnListContainer[1];
		container1[0] = GOTBiomeSpawnList.entry(GOTSpawnList.NORTH_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container1);

		SpawnListContainer[] container2 = new SpawnListContainer[2];
		container2[0] = GOTBiomeSpawnList.entry(GOTSpawnList.WILDING_MILITARY, 10).setSpawnChance(100).setConquestOnly();
		container2[1] = GOTBiomeSpawnList.entry(GOTSpawnList.WILDING_GIANT, 10).setSpawnChance(1000).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container2);

		SpawnListContainer[] container3 = new SpawnListContainer[1];
		container3[0] = GOTBiomeSpawnList.entry(GOTSpawnList.WALKERS_CONQUEST, 10).setSpawnChance(100).setConquestOnly();
		npcSpawnList.newFactionList(0).add(container3);
		invasionSpawns.addInvasion(GOTInvasions.NORTH, GOTEventSpawner.EventChance.COMMON);

		GOTStructureTower tower = new GOTStructureTower(this, 0.0f);
		tower.affix(GOTWaypoint.RamseyTower);
		decorator.addVillage(tower);
	}

	@Override
	public WaypointRegion getBiomeWaypoints() {
		return WaypointRegion.NORTH;
	}

	@Override
	public MusicRegion getBiomeMusic() {
		return GOTBiomeMusic.WESTEROS.getSubregion("bolton");
	}

	@Override
	public void decorate(World world, Random random, int i, int k) {
		super.decorate(world, random, i, k);
		if (GOTSpawner.Dreadfort.generatesAt(world, i, k)) {
			new GOTSpawner.Dreadfort(false).generateWithSetRotation(world, random, i, world.getTopSolidOrLiquidBlock(i, k), k, 0);
		}
	}
}
package fb.biome;

import got.common.world.biome.GOTBiome;

public class FBBiome {
	public static GOTBiome bolton;
	public static GOTBiome frey;

	public static void preInit() {
		bolton = new FBBiomeBolton(GOTBiome.id++, true).setTemperatureRainfall(0.4f, 0.4f).setMinMaxHeight(0.1f, 0.15f).setColor(0x88A844).setBiomeName("bolton");
		frey = new FBBiomeFrey(GOTBiome.id++, true).setTemperatureRainfall(0.8f, 0.8f).setMinMaxHeight(0.1f, 0.15f).setColor(0x78B56D).setBiomeName("frey");
	}
}
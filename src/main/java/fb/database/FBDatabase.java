package fb.database;

import got.common.database.*;
import got.common.util.GOTCommander;

public class FBDatabase {
	public static class FBCapes {
		public static GOTCapes FREY;
		public static GOTCapes BOLTON;
		
		public static void preInit() {
			FREY = GOTCommander.addAlignmentCape("FREY", FBFaction.FREY);
			BOLTON = GOTCommander.addAlignmentCape("BOLTON", FBFaction.BOLTON);
		}
	}

	public static class FBShields {
		public static GOTShields FREY;
		public static GOTShields BOLTON;
		
		public static void preInit() {
			FREY = GOTCommander.addAlignmentShield("FREY", FBFaction.FREY);
			BOLTON = GOTCommander.addAlignmentShield("BOLTON", FBFaction.BOLTON);
		}
	}
}
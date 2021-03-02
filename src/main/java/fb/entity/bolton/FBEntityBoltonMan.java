package fb.entity.bolton;

import fb.database.FBFaction;
import got.GOT;
import got.common.database.GOTChestContents;
import got.common.database.GOTFoods;
import got.common.database.GOTNames;
import got.common.entity.ai.GOTEntityAIAttackOnCollide;
import got.common.entity.ai.GOTEntityAIDrink;
import got.common.entity.ai.GOTEntityAIEat;
import got.common.entity.ai.GOTEntityAIFollowHiringPlayer;
import got.common.entity.ai.GOTEntityAIHiredRemainStill;
import got.common.entity.animal.GOTEntityHorse;
import got.common.entity.other.GOTEntityNPC;
import got.common.entity.other.GOTNPCMount;
import got.common.entity.westeros.north.GOTEntityNorthMan;
import got.common.faction.GOTFaction;
import got.common.quest.GOTMiniQuest;
import got.common.quest.GOTMiniQuestFactory;
import got.common.world.biome.westeros.GOTBiomeArryn;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class FBEntityBoltonMan extends GOTEntityNorthMan {
	public static ItemStack[] weapons = new ItemStack[] { new ItemStack(GOT.westerosDagger), new ItemStack(GOT.ironDagger), new ItemStack(GOT.bronzeDagger), new ItemStack(Items.iron_axe), new ItemStack(GOT.bronzeAxe), new ItemStack(Items.stone_axe) };

	public FBEntityBoltonMan(World world) {
		super(world);
		setSize(0.6f, 1.8f);
		getNavigator().setAvoidsWater(true);
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new GOTEntityAIHiredRemainStill(this));
		tasks.addTask(2, createArrenAttackAI());
		tasks.addTask(3, new GOTEntityAIFollowHiringPlayer(this));
		tasks.addTask(4, new EntityAIOpenDoor(this, true));
		tasks.addTask(5, new EntityAIWander(this, 1.0));
		tasks.addTask(6, new GOTEntityAIEat(this, GOTFoods.WESTEROS, 8000));
		tasks.addTask(6, new GOTEntityAIDrink(this, GOTFoods.WESTEROS_DRINK, 8000));
		tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 8.0f, 0.02f));
		tasks.addTask(7, new EntityAIWatchClosest2(this, GOTEntityNPC.class, 5.0f, 0.02f));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityLiving.class, 8.0f, 0.02f));
		tasks.addTask(9, new EntityAILookIdle(this));
		this.addTargetTasks(false);
	}

	protected EntityAIBase createArrenAttackAI() {
		return new GOTEntityAIAttackOnCollide(this, 1.4, false);
	}

	@Override
	public GOTNPCMount createMountToRide() {
		GOTEntityHorse horse = (GOTEntityHorse) super.createMountToRide();
		return horse;
	}

	@Override
	public void setupNPCGender() {
		familyInfo.setMale(rand.nextBoolean());
	}

	@Override
	public void setupNPCName() {
		familyInfo.setName(GOTNames.getWesterosName(rand, familyInfo.isMale()));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		int i = rand.nextInt(weapons.length);
		npcItemsInv.setMeleeWeapon(weapons[i].copy());
		npcItemsInv.setIdleItem(null);
		return data;
	}

	@Override
	public GOTFaction getFaction() {
		return FBFaction.BOLTON;
	}

	@Override
	public String getNPCName() {
		return familyInfo.getName();
	}

	@Override
	protected void onAttackModeChange(GOTEntityNPC.AttackMode mode, boolean mounted) {
		if (mode == GOTEntityNPC.AttackMode.IDLE) {
			setCurrentItemOrArmor(0, npcItemsInv.getIdleItem());
		} else {
			setCurrentItemOrArmor(0, npcItemsInv.getMeleeWeapon());
		}
	}

	@Override
	protected void dropFewItems(boolean flag, int i) {
		super.dropFewItems(flag, i);
		int bones = rand.nextInt(2) + rand.nextInt(i + 1);
		for (int l = 0; l < bones; ++l) {
			dropItem(Items.bone, 1);
		}
		dropWesterosItems(flag, i);
	}

	@Override
	protected void dropWesterosItems(boolean flag, int i) {
		if (rand.nextInt(6) == 0) {
			dropChestContents(GOTChestContents.WESTEROS, 1, 2 + i);
		}
	}

	@Override
	public float getAlignmentBonus() {
		return 1.0f;
	}

	@Override
	public boolean getCanSpawnHere() {
		if (super.getCanSpawnHere()) {
			if (liftSpawnRestrictions) {
				return true;
			}
			int i = MathHelper.floor_double(posX);
			int j = MathHelper.floor_double(boundingBox.minY);
			int k = MathHelper.floor_double(posZ);
			if (j > 62 && worldObj.getBlock(i, j - 1, k) == worldObj.getBiomeGenForCoords(i, k).topBlock) {
				return true;
			}
		}
		return false;
	}

	@Override
	public float getBlockPathWeight(int i, int j, int k) {
		float f = 0.0f;
		BiomeGenBase biome = worldObj.getBiomeGenForCoords(i, k);
		if (biome instanceof GOTBiomeArryn) {
			f += 20.0f;
		}
		return f;
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isDrunkard()) {
			return "westeros/north/drunkard/neutral";
		}
		if (isFriendly(entityplayer)) {
			return "westeros/north/man/friendly";
		}
		return "westeros/north/man/hostile";
	}

	@Override
	public GOTMiniQuest createMiniQuest() {
		return GOTMiniQuestFactory.WESTEROS.createQuest(this);
	}

	@Override
	public GOTMiniQuestFactory getBountyHelpSpeechDir() {
		return GOTMiniQuestFactory.WESTEROS;
	}
}

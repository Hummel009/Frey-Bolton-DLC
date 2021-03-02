package fb.entity.frey;

import fb.database.FBDatabase.FBCapes;
import got.GOT;
import got.common.GOTLevelData;
import got.common.database.*;
import got.common.entity.ai.GOTEntityAIAttackOnCollide;
import got.common.entity.animal.GOTEntityHorse;
import got.common.entity.other.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FBEntityFreyCaptain extends FBEntityFreySoldier implements GOTUnitTradeable {
	public static GOTUnitTradeEntries FREY = new GOTUnitTradeEntries(50.0f, new GOTUnitTradeEntry(FBEntityFreyLevyman.class, 5, 5.0f), new GOTUnitTradeEntry(FBEntityFreyLevymanArcher.class, 10, 10.0f), new GOTUnitTradeEntry(FBEntityFreyLevyman.class, GOTEntityHorse.class, "Rider", 15, 15.0f), new GOTUnitTradeEntry(FBEntityFreySoldier.class, 10, 10.0f), new GOTUnitTradeEntry(FBEntityFreySoldierArcher.class, 15, 15.0f), new GOTUnitTradeEntry(FBEntityFreySoldier.class, GOTEntityHorse.class, "Rider", 20, 20.0f).setMountArmor(GOT.westerosHorseArmor), new GOTUnitTradeEntry(FBEntityFreyBannerBearer.class, 10, 20.0f));

	public FBEntityFreyCaptain(World world) {
		super(world);
		this.addTargetTasks(false);
		npcCape = FBCapes.FREY;
	}

	@Override
	public EntityAIBase createArrenAttackAI() {
		return new GOTEntityAIAttackOnCollide(this, 1.4, false);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		npcItemsInv.setMeleeWeapon(new ItemStack(GOT.westerosSword));
		npcItemsInv.setMeleeWeaponMounted(npcItemsInv.getMeleeWeapon());
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());
		npcItemsInv.setIdleItemMounted(npcItemsInv.getMeleeWeaponMounted());
		setCurrentItemOrArmor(4, null);
		return data;
	}

	@Override
	public float getAlignmentBonus() {
		return 5.0f;
	}

	@Override
	public GOTUnitTradeEntries getUnits() {
		return FREY;
	}

	@Override
	public boolean canTradeWith(EntityPlayer entityplayer) {
		return GOTLevelData.getData(entityplayer).getAlignment(getFaction()) >= 50.0f && isFriendly(entityplayer);
	}

	@Override
	public void onUnitTrade(EntityPlayer entityplayer) {
		GOTLevelData.getData(entityplayer);
	}

	@Override
	public boolean shouldTraderRespawn() {
		return true;
	}

	@Override
	public String getSpeechBank(EntityPlayer entityplayer) {
		if (isFriendly(entityplayer)) {
			if (canTradeWith(entityplayer)) {
				return "westeros/north/captain/friendly";
			}
			return "westeros/north/captain/neutral";
		}
		return "westeros/north/soldier/hostile";
	}

	@Override
	public GOTInvasions getConquestHorn() {
		return null;
	}
}

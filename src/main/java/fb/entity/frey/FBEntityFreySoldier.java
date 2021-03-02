package fb.entity.frey;

import fb.database.FBDatabase.FBShields;
import got.GOT;
import got.common.entity.other.GOTEntityNPC;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FBEntityFreySoldier extends FBEntityFreyLevyman {
	public FBEntityFreySoldier(World world) {
		super(world);
		spawnRidingHorse = rand.nextInt(6) == 0;
		npcShield = FBShields.FREY;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(npcRangedAccuracy).setBaseValue(0.75);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		data = super.onSpawnWithEgg(data);
		int i = rand.nextInt(6);
		if (i == 0 || i == 1 || i == 2 || i == 3) {
			npcItemsInv.setMeleeWeapon(new ItemStack(GOT.westerosHammer));
		} else if (i == 4) {
			npcItemsInv.setMeleeWeapon(new ItemStack(GOT.westerosSword));
		} else if (i == 5) {
			npcItemsInv.setMeleeWeapon(new ItemStack(GOT.westerosPike));
		}
		if (rand.nextInt(3) == 0) {
			npcItemsInv.setMeleeWeaponMounted(new ItemStack(GOT.westerosLance));
		} else {
			npcItemsInv.setMeleeWeaponMounted(npcItemsInv.getMeleeWeapon());
		}
		if (rand.nextInt(5) == 0) {
			npcItemsInv.setSpearBackup(npcItemsInv.getMeleeWeapon());
			npcItemsInv.setMeleeWeapon(new ItemStack(GOT.westerosSpear));
		}
		npcItemsInv.setIdleItem(npcItemsInv.getMeleeWeapon());
		npcItemsInv.setIdleItemMounted(npcItemsInv.getMeleeWeaponMounted());
		setCurrentItemOrArmor(1, new ItemStack(GOT.riverlandsBoots));
		setCurrentItemOrArmor(2, new ItemStack(GOT.riverlandsLeggings));
		setCurrentItemOrArmor(3, new ItemStack(GOT.riverlandsChestplate));
		setCurrentItemOrArmor(4, new ItemStack(GOT.riverlandsHelmet));
		return data;
	}

	@Override
	protected void onAttackModeChange(GOTEntityNPC.AttackMode mode, boolean mounted) {
		if (mode == GOTEntityNPC.AttackMode.IDLE) {
			if (mounted) {
				setCurrentItemOrArmor(0, npcItemsInv.getIdleItemMounted());
			} else {
				setCurrentItemOrArmor(0, npcItemsInv.getIdleItem());
			}
		} else if (mounted) {
			setCurrentItemOrArmor(0, npcItemsInv.getMeleeWeaponMounted());
		} else {
			setCurrentItemOrArmor(0, npcItemsInv.getMeleeWeapon());
		}
	}

	@Override
	public float getAlignmentBonus() {
		return 3.0f;
	}
}

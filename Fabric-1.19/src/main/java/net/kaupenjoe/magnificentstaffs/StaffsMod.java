package net.kaupenjoe.magnificentstaffs;

import net.fabricmc.api.ModInitializer;

import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.item.ModCreativeModeTab;
import net.kaupenjoe.magnificentstaffs.item.ModItems;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaffsMod implements ModInitializer {
	public static final String MOD_ID = "magnificentstaffs";
	public static final Logger LOGGER = LoggerFactory.getLogger("magnificentstaffs");

	@Override
	public void onInitialize() {
		ModCreativeModeTab.registerItemGroups();
		ModItems.registerModItems();

		ModParticles.register();
	}
}
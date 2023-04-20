package net.kaupenjoe.magnificentstaffs;

import com.mojang.logging.LogUtils;
import net.kaupenjoe.magnificentstaffs.block.ModBlocks;
import net.kaupenjoe.magnificentstaffs.entity.ModEntities;
import net.kaupenjoe.magnificentstaffs.entity.client.BlizzardProjectileRenderer;
import net.kaupenjoe.magnificentstaffs.entity.client.ClingerWallRenderer;
import net.kaupenjoe.magnificentstaffs.entity.client.MagicProjectileRenderer;
import net.kaupenjoe.magnificentstaffs.item.ModCreativeModeTab;
import net.kaupenjoe.magnificentstaffs.item.ModItems;
import net.kaupenjoe.magnificentstaffs.particles.ModParticles;
import net.kaupenjoe.magnificentstaffs.sound.ModSound;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(StaffsMod.MOD_ID)
public class StaffsMod {
    public static final String MOD_ID = "magnificentstaffs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public StaffsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModParticles.register(modEventBus);
        ModSound.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == ModCreativeModeTab.STAFF_TAB) {
            event.accept(ModItems.SAPPHIRE_STAFF);
            event.accept(ModItems.DIAMOND_STAFF);
            event.accept(ModItems.RUBY_STAFF);
            event.accept(ModItems.AMETHYST_STAFF);
            event.accept(ModItems.EMERALD_STAFF);

            event.accept(ModItems.BLIZZARD_STAFF);
            event.accept(ModItems.METEORITE_STAFF);
            event.accept(ModItems.SCULKBEAM_STAFF);
            event.accept(ModItems.STAFF_OF_EARTH);

            event.accept(ModItems.RADIATION_STAFF);
            event.accept(ModItems.VENOM_STAFF);

            event.accept(ModItems.CLINGER_STAFF);
            event.accept(ModItems.SPECTRE_STAFF);

            event.accept(ModItems.RUBY);
            event.accept(ModItems.SAPPHIRE);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.MAGIC_PROJECTILE.get(), MagicProjectileRenderer::new);
            EntityRenderers.register(ModEntities.BLIZZARD_PROJECTILE.get(), BlizzardProjectileRenderer::new);
            EntityRenderers.register(ModEntities.CLINGER_WALL_ENTITY.get(), ClingerWallRenderer::new);
        }
    }
}

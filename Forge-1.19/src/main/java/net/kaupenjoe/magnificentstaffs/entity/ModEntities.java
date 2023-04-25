package net.kaupenjoe.magnificentstaffs.entity;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.entity.custom.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StaffsMod.MOD_ID);

    public static final RegistryObject<EntityType<BasicMagicProjectileEntity>> MAGIC_PROJECTILE = ENTITY_TYPES.register("magic_projectile",
            () -> EntityType.Builder.of((EntityType.EntityFactory<BasicMagicProjectileEntity>)BasicMagicProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("magic_projectile"));

    public static final RegistryObject<EntityType<BlizzardMagicProjectileEntity>> BLIZZARD_PROJECTILE = ENTITY_TYPES.register("falling_magic_projectile",
            () -> EntityType.Builder.of((EntityType.EntityFactory<BlizzardMagicProjectileEntity>) BlizzardMagicProjectileEntity::new, MobCategory.MISC)
                    .sized(1.5F, 1F).build("falling_magic_projectile"));

    public static final RegistryObject<EntityType<ClingerWallEntity>> CLINGER_WALL_ENTITY = ENTITY_TYPES.register("clinger_wall_entity",
            () -> EntityType.Builder.of((EntityType.EntityFactory<ClingerWallEntity>) ClingerWallEntity::new, MobCategory.MISC)
                    .sized(1F, 1F).clientTrackingRange(6).updateInterval(2).build("clinger_wall_entity"));

    public static final RegistryObject<EntityType<BouncingProjectileEntity>> BOUNCING_PROJECTILE = ENTITY_TYPES.register("bouncing_projectile",
            () -> EntityType.Builder.of((EntityType.EntityFactory<BouncingProjectileEntity>)BouncingProjectileEntity::new, MobCategory.MISC)
                    .sized(0.75F, 0.75F).build("bouncing_projectile"));

    public static final RegistryObject<EntityType<BouncingMagicProjectileEntity>> BOUNCING_MAGIC_PROJECTILE = ENTITY_TYPES.register("bouncing_magic_projectile",
            () -> EntityType.Builder.of((EntityType.EntityFactory<BouncingMagicProjectileEntity>)BouncingMagicProjectileEntity::new, MobCategory.MISC)
                    .sized(0.55F, 0.55F).build("bouncing_magic_projectile"));

    public static final RegistryObject<EntityType<SeekingMagicProjectileEntity>> SEEKING_MAGIC_PROJECTILE = ENTITY_TYPES.register("seeking_magic_projectile",
            () -> EntityType.Builder.of((EntityType.EntityFactory<SeekingMagicProjectileEntity>)SeekingMagicProjectileEntity::new, MobCategory.MISC)
                    .sized(0.55F, 0.55F).build("seeking_magic_projectile"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}

package net.kaupenjoe.magnificentstaffs.entity;

import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static final EntityType<BasicMagicProjectileEntity> MAGIC_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,"magic_projectile",
            EntityType.Builder.create((EntityType.EntityFactory<BasicMagicProjectileEntity>)BasicMagicProjectileEntity::new, SpawnGroup.MISC).setDimensions(0.5F, 0.5F)
                    .build("magic_projectile"));
}

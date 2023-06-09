package net.kaupenjoe.magnificentstaffs.entity.layers;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MAGIC_PROJECTILE_LAYER = new ModelLayerLocation(
            new ResourceLocation(StaffsMod.MOD_ID,"magic_projectile"), "magic_projectile");

    public static final ModelLayerLocation BLIZZARD_PROJECTILE_LAYER = new ModelLayerLocation(
            new ResourceLocation(StaffsMod.MOD_ID,"blizzard_projectile"), "blizzard_projectile");

    public static final ModelLayerLocation CLINGER_WALL_LAYER = new ModelLayerLocation(
            new ResourceLocation(StaffsMod.MOD_ID,"clinger_wall_layer"), "clinger_wall_layer");

    public static final ModelLayerLocation BOUNCING_PROJECTILE = new ModelLayerLocation(
            new ResourceLocation(StaffsMod.MOD_ID,"bouncing_projectile"), "bouncing_projectile");
}

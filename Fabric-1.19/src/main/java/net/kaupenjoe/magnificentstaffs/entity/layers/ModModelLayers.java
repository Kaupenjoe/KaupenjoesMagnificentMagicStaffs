package net.kaupenjoe.magnificentstaffs.entity.layers;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer MAGIC_PROJECTILE_LAYER = new EntityModelLayer(
            new Identifier(StaffsMod.MOD_ID,"magic_projectile"), "magic_projectile");
}

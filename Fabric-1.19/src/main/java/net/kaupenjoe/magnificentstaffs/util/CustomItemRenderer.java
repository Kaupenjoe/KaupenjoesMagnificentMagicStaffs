package net.kaupenjoe.magnificentstaffs.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CustomItemRenderer extends ItemRenderer {
    public CustomItemRenderer(MinecraftClient client, TextureManager manager, BakedModelManager bakery, ItemColors colors, BuiltinModelItemRenderer builtinModelItemRenderer) {
        super(client, manager, bakery, colors, builtinModelItemRenderer);
    }

    @Override
    public BakedModel getModel(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed) {

        BakedModel bakedModel;
        if (stack.isOf(Items.TRIDENT)) {
            bakedModel = this.models.getModelManager().getModel(TRIDENT_IN_HAND);
        } else if (stack.isOf(Items.SPYGLASS)) {
            bakedModel = this.models.getModelManager().getModel(SPYGLASS_IN_HAND);
        } else {
            bakedModel = this.models.getModel(stack);
        }

        ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld)world : null;
        BakedModel bakedModel2 = bakedModel.getOverrides().apply(bakedModel, stack, clientWorld, entity, seed);
        return bakedModel2 == null ? this.models.getModelManager().getMissingModel() : bakedModel2;
        return super.getModel(stack, world, entity, seed);
    }
}

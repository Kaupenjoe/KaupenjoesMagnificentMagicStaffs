package net.kaupenjoe.magnificentstaffs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.entity.custom.BouncingMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.entity.custom.BouncingProjectileEntity;
import net.kaupenjoe.magnificentstaffs.entity.layers.ModModelLayers;
import net.kaupenjoe.magnificentstaffs.util.MagicColorUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Vector4f;

public class BouncingMagicProjectileRenderer extends EntityRenderer<BouncingMagicProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(StaffsMod.MOD_ID, "textures/entity/magic_projectile.png");
    protected MagicProjectileModel model;

    public BouncingMagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new MagicProjectileModel(context.bakeLayer(ModModelLayers.MAGIC_PROJECTILE_LAYER));
    }

    public void render(BouncingMagicProjectileEntity entity, float p_116112_, float p_116113_, PoseStack p_116114_, MultiBufferSource p_116115_, int p_116116_) {
        p_116114_.pushPose();
        p_116114_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_116113_, entity.yRotO, entity.getYRot()) - 90.0F));
        p_116114_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_116113_, entity.xRotO, entity.getXRot()) + 90.0F));
        p_116114_.scale(0.5f, 0.5f, 0.5f);
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(p_116115_, this.model.renderType(this.getTextureLocation(entity)), false, false);
        Vector4f colorVector = new Vector4f(0.75f, 0.35f, 0.8f, 0.8f);

        this.model.renderToBuffer(p_116114_, vertexconsumer, p_116116_, OverlayTexture.NO_OVERLAY, colorVector.x, colorVector.y, colorVector.z, colorVector.w);
        p_116114_.popPose();
        super.render(entity, p_116112_, p_116113_, p_116114_, p_116115_, p_116116_);
    }

    public ResourceLocation getTextureLocation(BouncingMagicProjectileEntity entity) {
        return TEXTURE;
    }
}

package net.kaupenjoe.magnificentstaffs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
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

public class MagicProjectileRenderer extends EntityRenderer<BasicMagicProjectileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(StaffsMod.MOD_ID, "textures/entity/magic_projectile.png");
    protected MagicProjectileModel model;

    public MagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new MagicProjectileModel(context.bakeLayer(ModModelLayers.MAGIC_PROJECTILE_LAYER));
    }

    public void render(BasicMagicProjectileEntity entity, float p_116112_, float p_116113_, PoseStack p_116114_, MultiBufferSource p_116115_, int p_116116_) {
        p_116114_.pushPose();
        p_116114_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_116113_, entity.yRotO, entity.getYRot()) - 90.0F));
        p_116114_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_116113_, entity.xRotO, entity.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(p_116115_, this.model.renderType(this.getTextureLocation(entity)), false, false);
        Vector4f colorVector = MagicColorUtils.PROJECTILE_VECTOR_BY_ORDINAL.get(entity.getProjectileType());

        this.model.renderToBuffer(p_116114_, vertexconsumer, p_116116_, OverlayTexture.NO_OVERLAY, colorVector.x, colorVector.y, colorVector.z, colorVector.w);
        p_116114_.popPose();
        super.render(entity, p_116112_, p_116113_, p_116114_, p_116115_, p_116116_);
    }

    public ResourceLocation getTextureLocation(BasicMagicProjectileEntity entity) {
        return TEXTURE;
    }
}

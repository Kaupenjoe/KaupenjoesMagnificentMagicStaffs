package net.kaupenjoe.magnificentstaffs.entity.client;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.entity.custom.BasicMagicProjectileEntity;
import net.kaupenjoe.magnificentstaffs.entity.layers.ModModelLayers;
import net.kaupenjoe.magnificentstaffs.util.MagicColorUtils;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector4f;

public class MagicProjectileRenderer extends EntityRenderer<BasicMagicProjectileEntity> {
    public static final Identifier TEXTURE = new Identifier(StaffsMod.MOD_ID, "textures/entity/magic_projectile.png");
    protected MagicProjectileModel model;

    public MagicProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new MagicProjectileModel(context.getPart(ModModelLayers.MAGIC_PROJECTILE_LAYER));
    }

    public void render(BasicMagicProjectileEntity entity, float p_116112_, float p_116113_, MatrixStack p_116114_, VertexConsumerProvider p_116115_, int p_116116_) {
        p_116114_.push();
        p_116114_.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(p_116113_, entity.prevYaw, entity.getProjectileType()) - 90.0F));
        p_116114_.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(p_116113_, entity.prevPitch, entity.getPitch()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(p_116115_, this.model.getLayer(this.getTexture(entity)), false, false);
        Vector4f colorVector = MagicColorUtils.PROJECTILE_VECTOR_BY_ORDINAL.get(entity.getProjectileType());

        this.model.render(p_116114_, vertexconsumer, p_116116_, OverlayTexture.DEFAULT_UV, colorVector.x, colorVector.y, colorVector.z, colorVector.w);
        p_116114_.pop();
        super.render(entity, p_116112_, p_116113_, p_116114_, p_116115_, p_116116_);
    }

    @Override
    public Identifier getTexture(BasicMagicProjectileEntity entity) {
        return TEXTURE;
    }
}

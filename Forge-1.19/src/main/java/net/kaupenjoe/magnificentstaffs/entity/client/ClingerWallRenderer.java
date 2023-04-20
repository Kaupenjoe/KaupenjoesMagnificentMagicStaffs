package net.kaupenjoe.magnificentstaffs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.kaupenjoe.magnificentstaffs.entity.custom.ClingerWallEntity;
import net.kaupenjoe.magnificentstaffs.entity.layers.ModModelLayers;
import net.minecraft.client.model.EvokerFangsModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;

public class ClingerWallRenderer extends EntityRenderer<ClingerWallEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("textures/entity/illager/evoker_fangs.png");
    protected ClingerWallModel<ClingerWallEntity> model;

    public ClingerWallRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new ClingerWallModel<>(context.bakeLayer(ModModelLayers.CLINGER_WALL_LAYER));
    }

    public void render(ClingerWallEntity clingerWallEntity, float p_114529_, float p_114530_, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_114533_) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F - clingerWallEntity.getYRot()));
        poseStack.translate(0.0D, 0.25f, 0.0D);
        poseStack.scale(0.5F, 0.5F, 0.5F);

        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(this.model.renderType(TEXTURE_LOCATION));
        this.model.renderToBuffer(poseStack, vertexconsumer, getLightLevel(clingerWallEntity.level, clingerWallEntity.blockPosition()),
                OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();

        super.render(clingerWallEntity, p_114529_, p_114530_, poseStack, multiBufferSource, getLightLevel(clingerWallEntity.level, clingerWallEntity.blockPosition()));
    }

    public @NotNull ResourceLocation getTextureLocation(ClingerWallEntity entity) {
        return TEXTURE_LOCATION;
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos.above());
        int sLight = level.getBrightness(LightLayer.SKY, pos.above());
        return LightTexture.pack(bLight, sLight);
    }
}

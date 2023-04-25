package net.kaupenjoe.magnificentstaffs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class BouncingProjectileModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart bb_main;

    public BouncingProjectileModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, -2.0F, -4.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(16, 16).addBox(-4.0F, -8.0F, -4.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(26, 14).addBox(-2.0F, -8.0F, -6.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(4.0F, -8.0F, -4.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 6).addBox(-2.0F, -8.0F, 2.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-2.0F, -10.0F, -4.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
package net.kaupenjoe.magnificentstaffs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kaupenjoe.magnificentstaffs.entity.custom.BlizzardMagicProjectileEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BlizzardMagicProjectileModel<T extends BlizzardMagicProjectileEntity> extends EntityModel<T> {
    private final ModelPart bb_main;

    public BlizzardMagicProjectileModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.25F, 0.0F, -0.25F, 0.5F, 1.0F, 0.5F, new CubeDeformation(0.0F))
                .texOffs(8, 9).addBox(-0.75F, -1.0F, 0.0F, 1.5F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(8, 4).addBox(-1.0F, -2.25F, 0.0F, 2.0F, 1.25F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(6, 5).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 3.75F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -5.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 0).addBox(-0.75F, -2.5F, -0.75F, 1.5F, 1.5F, 1.5F, new CubeDeformation(0.0F))
                .texOffs(6, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.9F, 0.0F, -3.1416F, 0.0F, 0.0F));

        PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(4, 9).addBox(-0.75F, -1.0F, 0.0F, 1.5F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 9).addBox(-1.0F, -2.25F, 0.0F, 2.0F, 1.25F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 3.75F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.9F, 0.0F, 0.0F, 1.5708F, -3.1416F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
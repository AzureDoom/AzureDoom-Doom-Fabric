package mod.azure.doom.client.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Imp - Batpixxler Created using Tabula 8.0.0
 */
public class ImpModel<T extends Entity> extends EntityModel<T> {

	public ModelPart thighs;
	public ModelPart torso;
	public ModelPart rLeg1;
	public ModelPart lLeg1;
	public ModelPart neck;
	public ModelPart rShoulder;
	public ModelPart lShoulder;
	public ModelPart backSpike1;
	public ModelPart backSpike2;
	public ModelPart rightPec;
	public ModelPart leftPec;
	public ModelPart head;
	public ModelPart chin;
	public ModelPart browMiddle;
	public ModelPart lBrow1;
	public ModelPart rBrow1;
	public ModelPart lBrow2;
	public ModelPart rBrow2;
	public ModelPart rArm1;
	public ModelPart rShoulderSpike;
	public ModelPart rArm2;
	public ModelPart rArmSpike;
	public ModelPart rElbowSpike;
	public ModelPart lArm1;
	public ModelPart lShoulderSpike;
	public ModelPart lArm2;
	public ModelPart lArmSpike;
	public ModelPart lElbowSpike;
	public ModelPart chestSpikeR;
	public ModelPart chestSpikeL;
	public ModelPart rLeg2;
	public ModelPart kneeSpikeR;
	public ModelPart lLeg2;
	public ModelPart kneeSpikeL;

	public ImpModel(float modelSize, boolean smallArmsIn) {
		this.textureWidth = 60;
		this.textureHeight = 80;
		this.backSpike2 = new ModelPart(this, 30, 5);
		this.backSpike2.setPivot(-3.3F, -5.0F, 0.0F);
		this.backSpike2.addCuboid(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(backSpike2, 0.956091342937205F, 0.0F, -0.500909508638178F);
		this.head = new ModelPart(this, 0, 58);
		this.head.setPivot(0.0F, -3.0F, -2.7F);
		this.head.addCuboid(-3.5F, -5.0F, -4.0F, 7.0F, 5.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(head, -0.0911061832922575F, 0.0F, 0.0F);
		this.rElbowSpike = new ModelPart(this, 30, 0);
		this.rElbowSpike.setPivot(0.0F, 0.5F, -0.6F);
		this.rElbowSpike.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rElbowSpike, 0.6373942508178124F, -0.3186971254089062F, 0.0F);
		this.rArm2 = new ModelPart(this, 16, 41);
		this.rArm2.mirror = true;
		this.rArm2.setPivot(0.0F, 5.0F, 2.0F);
		this.rArm2.addCuboid(-2.0F, 0.0F, -4.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rArm2, -0.13665927909957545F, 0.0F, 0.0F);
		this.rLeg1 = new ModelPart(this, 0, 21);
		this.rLeg1.mirror = true;
		this.rLeg1.setPivot(-1.9F, 5.0F, 0.0F);
		this.rLeg1.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rLeg1, -0.04555309164612875F, 0.0F, 0.04555309164612875F);
		this.lBrow1 = new ModelPart(this, 0, 58);
		this.lBrow1.setPivot(0.4F, 0.3F, 0.0F);
		this.lBrow1.addCuboid(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lBrow1, 0.0F, 0.0F, -0.7285004590772052F);
		this.rShoulder = new ModelPart(this, 16, 32);
		this.rShoulder.mirror = true;
		this.rShoulder.setPivot(-4.0F, -6.0F, -1.9F);
		this.rShoulder.addCuboid(-4.0F, -2.0F, -2.0F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rShoulder, 0.0F, 0.0F, 0.13665927909957545F);
		this.lArm2 = new ModelPart(this, 16, 41);
		this.lArm2.setPivot(0.0F, 5.0F, 2.0F);
		this.lArm2.addCuboid(-2.0F, 0.0F, -4.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lArm2, -0.13665927909957545F, 0.0F, 0.0F);
		this.lBrow2 = new ModelPart(this, 0, 58);
		this.lBrow2.setPivot(2.0F, 0.0F, 0.0F);
		this.lBrow2.addCuboid(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lBrow2, 0.0F, 0.0F, 1.092750655326294F);
		this.lLeg2 = new ModelPart(this, 16, 21);
		this.lLeg2.setPivot(0.0F, 6.8F, -2.0F);
		this.lLeg2.addCuboid(-2.0F, 0.0F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lLeg2, 0.0911061832922575F, 0.0F, 0.04555309164612875F);
		this.lShoulder = new ModelPart(this, 16, 32);
		this.lShoulder.setPivot(4.0F, -6.0F, -1.9F);
		this.lShoulder.addCuboid(-1.0F, -2.0F, -2.0F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lShoulder, 0.0F, 0.0F, -0.13665927909957545F);
		this.chin = new ModelPart(this, 21, 52);
		this.chin.setPivot(0.0F, -0.1F, -4.0F);
		this.chin.addCuboid(-3.0F, 0.0F, 0.0F, 6.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(chin, 0.13665927909957545F, 0.0F, 0.0F);
		this.rShoulderSpike = new ModelPart(this, 24, 13);
		this.rShoulderSpike.setPivot(-3.5F, 0.0F, 0.0F);
		this.rShoulderSpike.addCuboid(-3.0F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rShoulderSpike, 0.0F, 0.500909508638178F, 0.3642502295386026F);
		this.backSpike1 = new ModelPart(this, 30, 5);
		this.backSpike1.setPivot(2.3F, -5.5F, 0.0F);
		this.backSpike1.addCuboid(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(backSpike1, 0.956091342937205F, 0.0F, 0.500909508638178F);
		this.kneeSpikeL = new ModelPart(this, 22, 17);
		this.kneeSpikeL.setPivot(0.0F, 0.5F, 1.0F);
		this.kneeSpikeL.addCuboid(-0.5F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(kneeSpikeL, -0.6373942508178124F, 0.0F, 0.0F);
		this.neck = new ModelPart(this, 0, 49);
		this.neck.setPivot(0.0F, -7.0F, 1.0F);
		this.neck.addCuboid(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(neck, 0.27314402127920984F, 0.0F, 0.0F);
		this.rBrow1 = new ModelPart(this, 0, 58);
		this.rBrow1.setPivot(-0.4F, 0.3F, 0.0F);
		this.rBrow1.addCuboid(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rBrow1, 0.0F, 0.0F, 0.7285004590772052F);
		this.lElbowSpike = new ModelPart(this, 30, 0);
		this.lElbowSpike.setPivot(0.0F, 0.5F, -0.6F);
		this.lElbowSpike.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lElbowSpike, 0.6373942508178124F, 0.3186971254089062F, 0.0F);
		this.chestSpikeR = new ModelPart(this, 18, 12);
		this.chestSpikeR.setPivot(-1.0F, 0.2F, -2.7F);
		this.chestSpikeR.addCuboid(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(chestSpikeR, 0.3186971254089062F, 0.3642502295386026F, 0.0F);
		this.rArm1 = new ModelPart(this, 0, 40);
		this.rArm1.mirror = true;
		this.rArm1.setPivot(-1.5F, 2.0F, 0.5F);
		this.rArm1.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.lArm1 = new ModelPart(this, 0, 40);
		this.lArm1.setPivot(1.5F, 2.0F, 0.5F);
		this.lArm1.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.kneeSpikeR = new ModelPart(this, 22, 17);
		this.kneeSpikeR.setPivot(0.0F, 0.5F, 1.0F);
		this.kneeSpikeR.addCuboid(-0.5F, 0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(kneeSpikeR, -0.6373942508178124F, 0.0F, 0.0F);
		this.rBrow2 = new ModelPart(this, 0, 58);
		this.rBrow2.setPivot(-2.0F, 0.0F, 0.0F);
		this.rBrow2.addCuboid(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rBrow2, 0.0F, 0.0F, -1.092750655326294F);
		this.chestSpikeL = new ModelPart(this, 18, 12);
		this.chestSpikeL.setPivot(1.0F, 0.2F, -2.7F);
		this.chestSpikeL.addCuboid(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(chestSpikeL, 0.3186971254089062F, -0.3642502295386026F, 0.0F);
		this.rightPec = new ModelPart(this, 0, 32);
		this.rightPec.setPivot(-2.7F, -5.3F, -2.0F);
		this.rightPec.addCuboid(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rightPec, 0.0F, 0.0F, -0.08726646259971647F);
		this.torso = new ModelPart(this, 0, 0);
		this.torso.setPivot(0.0F, 0.0F, 1.3F);
		this.torso.addCuboid(-5.0F, -7.0F, -4.0F, 10.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.lShoulderSpike = new ModelPart(this, 24, 13);
		this.lShoulderSpike.setPivot(3.5F, 0.0F, 0.0F);
		this.lShoulderSpike.addCuboid(0.0F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lShoulderSpike, 0.0F, -0.500909508638178F, -0.3642502295386026F);
		this.lLeg1 = new ModelPart(this, 0, 21);
		this.lLeg1.setPivot(1.9F, 5.0F, 0.0F);
		this.lLeg1.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lLeg1, -0.04555309164612875F, 0.0F, -0.04555309164612875F);
		this.thighs = new ModelPart(this, 0, 12);
		this.thighs.setPivot(0.0F, 5.5F, 0.0F);
		this.thighs.addCuboid(-3.5F, 0.0F, -2.0F, 7.0F, 5.0F, 4.0F, 0.25F, 0.25F, 0.25F);
		this.browMiddle = new ModelPart(this, 0, 58);
		this.browMiddle.setPivot(0.0F, -2.4F, -4.5F);
		this.browMiddle.addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		this.rArmSpike = new ModelPart(this, 28, 20);
		this.rArmSpike.setPivot(-1.5F, 0.4F, 1.0F);
		this.rArmSpike.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rArmSpike, 0.0F, -0.5462880425584197F, 0.0F);
		this.rLeg2 = new ModelPart(this, 16, 21);
		this.rLeg2.mirror = true;
		this.rLeg2.setPivot(0.0F, 6.8F, -2.0F);
		this.rLeg2.addCuboid(-2.0F, 0.0F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(rLeg2, 0.0911061832922575F, 0.0F, -0.04555309164612875F);
		this.leftPec = new ModelPart(this, 0, 32);
		this.leftPec.setPivot(2.7F, -5.3F, -2.0F);
		this.leftPec.addCuboid(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(leftPec, 0.0F, 0.0F, 0.08726646259971647F);
		this.lArmSpike = new ModelPart(this, 28, 20);
		this.lArmSpike.setPivot(1.5F, 0.4F, 1.0F);
		this.lArmSpike.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(lArmSpike, 0.0F, 0.5462880425584197F, 0.0F);
		this.torso.addChild(this.backSpike2);
		this.neck.addChild(this.head);
		this.rArm2.addChild(this.rElbowSpike);
		this.rArm1.addChild(this.rArm2);
		this.thighs.addChild(this.rLeg1);
		this.browMiddle.addChild(this.lBrow1);
		this.torso.addChild(this.rShoulder);
		this.lArm1.addChild(this.lArm2);
		this.lBrow1.addChild(this.lBrow2);
		this.lLeg1.addChild(this.lLeg2);
		this.torso.addChild(this.lShoulder);
		this.head.addChild(this.chin);
		this.rShoulder.addChild(this.rShoulderSpike);
		this.torso.addChild(this.backSpike1);
		this.lLeg2.addChild(this.kneeSpikeL);
		this.torso.addChild(this.neck);
		this.browMiddle.addChild(this.rBrow1);
		this.lArm2.addChild(this.lElbowSpike);
		this.rightPec.addChild(this.chestSpikeR);
		this.rShoulder.addChild(this.rArm1);
		this.lShoulder.addChild(this.lArm1);
		this.rLeg2.addChild(this.kneeSpikeR);
		this.rBrow1.addChild(this.rBrow2);
		this.leftPec.addChild(this.chestSpikeL);
		this.torso.addChild(this.rightPec);
		this.thighs.addChild(this.torso);
		this.lShoulder.addChild(this.lShoulderSpike);
		this.thighs.addChild(this.lLeg1);
		this.head.addChild(this.browMiddle);
		this.rArm1.addChild(this.rArmSpike);
		this.rLeg1.addChild(this.rLeg2);
		this.torso.addChild(this.leftPec);
		this.lArm1.addChild(this.lArmSpike);
	}

	@Override
	public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.thighs).forEach((ModelPart) -> {
			ModelPart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.rShoulder.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 1.0F;
		this.lShoulder.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 1.0F;
		this.rLeg1.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 1.0F;
		this.lLeg1.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / 1.0F;
	}

	public void setRotateAngle(ModelPart ModelPart, float x, float y, float z) {
		ModelPart.pitch = x;
		ModelPart.yaw = y;
		ModelPart.roll = z;
	}
}
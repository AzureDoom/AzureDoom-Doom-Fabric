package mod.azure.doom.client.render;

import mod.azure.doom.client.DoomGeoEntityRenderer;
import mod.azure.doom.client.models.ImpModel;
import mod.azure.doom.entity.ImpEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ImpRender extends DoomGeoEntityRenderer<ImpEntity> {

	public ImpRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new ImpModel());
	}

	@Override
	public RenderLayer getRenderType(ImpEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}

	protected float getLyingAngle(ImpEntity entityLivingBaseIn) {
		return 0.0F;
	}

}
package mod.azure.doom.client.render;

import mod.azure.doom.client.models.ArachonotronEternalModel;
import mod.azure.doom.entity.tierheavy.ArachnotronEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class ArachonotronEternalRender extends GeoEntityRenderer<ArachnotronEntity> {

	public ArachonotronEternalRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new ArachonotronEternalModel());
		this.shadowRadius = 0.7F;
	}

	@Override
	public RenderLayer getRenderType(ArachnotronEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}

	protected float getLyingAngle(ArachnotronEntity entityLivingBaseIn) {
		return 0.0F;
	}

}
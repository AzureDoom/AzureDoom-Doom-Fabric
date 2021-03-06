package mod.azure.doom.client.render;

import mod.azure.doom.client.models.DreadknightModel;
import mod.azure.doom.entity.tierheavy.Hellknight2016Entity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class DreadKnightRender extends GeoEntityRenderer<Hellknight2016Entity> {

	public DreadKnightRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new DreadknightModel());
	}

	@Override
	public RenderLayer getRenderType(Hellknight2016Entity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}

}
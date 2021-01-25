package mod.azure.doom.client.render;

import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;
import mod.azure.doom.client.models.ImpNightmareModel;
import mod.azure.doom.entity.NightmareImpEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NightmareImpRender extends GeoEntityRenderer<NightmareImpEntity> {

	public NightmareImpRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new ImpNightmareModel());
	}

	@Override
	public RenderLayer getRenderType(NightmareImpEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}

	protected float getLyingAngle(NightmareImpEntity spiderEntity) {
		return 180.0F;
	}

}
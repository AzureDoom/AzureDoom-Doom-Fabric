package mod.azure.doom.client.render;

import mod.azure.doom.DoomMod;
import mod.azure.doom.client.models.MarauderModel;
import mod.azure.doom.entity.MarauderEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MarauderRender extends MobEntityRenderer<MarauderEntity, MarauderModel<MarauderEntity>> {

	protected static final Identifier TEXTURE = new Identifier(DoomMod.MODID, "textures/entity/marauder.png");

	public MarauderRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new MarauderModel<MarauderEntity>(0.5f, false), 0.5f);

	}

	@Override
	public Identifier getTexture(MarauderEntity entity) {
		return TEXTURE;
	}

	@Override
	protected void scale(MarauderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(1.0F, 1.0F, 1.0F);
	}

}
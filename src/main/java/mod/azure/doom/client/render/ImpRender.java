package mod.azure.doom.client.render;

import mod.azure.doom.DoomMod;
import mod.azure.doom.client.models.ImpModel;
import mod.azure.doom.entity.ImpEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ImpRender extends MobEntityRenderer<ImpEntity, ImpModel> {

	protected static final Identifier TEXTURE = new Identifier(DoomMod.MODID, "textures/entity/imp-texturemap.png");

	public ImpRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new ImpModel(), 0.5f);
	}

	@Override
	protected float getLyingAngle(ImpEntity entityLivingBaseIn) {
		return 0.0F;
	}

	@Override
	public Identifier getTexture(ImpEntity entity) {
		return TEXTURE;
	}

}
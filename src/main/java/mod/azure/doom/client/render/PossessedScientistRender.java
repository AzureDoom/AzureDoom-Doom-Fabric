package mod.azure.doom.client.render;

import mod.azure.doom.DoomMod;
import mod.azure.doom.client.models.PossessedScientistModel;
import mod.azure.doom.entity.PossessedScientistEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class PossessedScientistRender
		extends MobEntityRenderer<PossessedScientistEntity, PossessedScientistModel<PossessedScientistEntity>> {

	protected static final Identifier TEXTURE = new Identifier(DoomMod.MODID, "textures/entity/possessedscientist.png");

	public PossessedScientistRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new PossessedScientistModel<PossessedScientistEntity>(0.5f, false), 0.5f);

	}

	@Override
	public Identifier getTexture(PossessedScientistEntity entity) {
		return TEXTURE;
	}

}
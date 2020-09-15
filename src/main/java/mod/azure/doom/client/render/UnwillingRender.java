package mod.azure.doom.client.render;

import mod.azure.doom.DoomMod;
import mod.azure.doom.client.models.UnarmedModel;
import mod.azure.doom.entity.UnwillingEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class UnwillingRender extends MobEntityRenderer<UnwillingEntity, UnarmedModel<UnwillingEntity>> {

	protected static final Identifier TEXTURE = new Identifier(DoomMod.MODID, "textures/entity/unwilling.png");

	public UnwillingRender(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new UnarmedModel<UnwillingEntity>(0.5f, false), 0.5f);
	}

	@Override
	public Identifier getTexture(UnwillingEntity entity) {
		return TEXTURE;
	}

}
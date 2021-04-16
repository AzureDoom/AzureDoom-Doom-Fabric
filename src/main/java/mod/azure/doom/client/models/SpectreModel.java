package mod.azure.doom.client.models;

import mod.azure.doom.DoomMod;
import mod.azure.doom.entity.SpectreEntity;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SpectreModel extends AnimatedGeoModel<SpectreEntity> {

	public SpectreModel() {
	}

	@Override
	public Identifier getModelLocation(SpectreEntity object) {
		return new Identifier(DoomMod.MODID, "geo/pinky.geo.json");
	}

	@Override
	public Identifier getTextureLocation(SpectreEntity object) {
		return new Identifier(DoomMod.MODID, "textures/entity/pinky-texturemap.png");
	}

	@Override
	public Identifier getAnimationFileLocation(SpectreEntity object) {
		return new Identifier(DoomMod.MODID, "animations/pinky_animation.json");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setLivingAnimations(SpectreEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone head = this.getAnimationProcessor().getBone("neck");

		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		if (head != null) {
			head.setRotationX(Vector3f.POSITIVE_X
					.getRadialQuaternion((extraData.headPitch + 30) * ((float) Math.PI / 360F)).getX());
			head.setRotationY(
					Vector3f.POSITIVE_Y.getRadialQuaternion(extraData.netHeadYaw * ((float) Math.PI / 500F)).getY());
		}
	}
}
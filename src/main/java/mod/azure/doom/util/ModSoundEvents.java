package mod.azure.doom.util;

import mod.azure.doom.DoomMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {

	public static SoundEvent BFG_FIRING = of("doom.bfg_firing");

	public static SoundEvent BFG_HIT = of("doom.bfg_hit");

	public static SoundEvent PLASMA_FIRING = of("doom.plasmafire");
	public static SoundEvent PLASMA_HIT = of("doom.plasmahit");

	public static SoundEvent ROCKET_FIRING = of("doom.rocketfire");
	public static SoundEvent ROCKET_HIT = of("doom.rockethit");

	public static SoundEvent SHOOT1 = of("doom.shoot1");
	public static SoundEvent SHOOT2 = of("doom.shoot2");
	public static SoundEvent SHOOT3 = of("doom.shoot3");

	public static SoundEvent CHAINGUN_SHOOT = of("doom.chaingun_fire");

	public static SoundEvent PISTOL_HIT = of("doom.pistol_fire");

	public static SoundEvent SHOTGUN_SHOOT = of("doom.shotgun_fire");

	public static SoundEvent LOADING_START = of("doom.loading_start");
	public static SoundEvent LOADING_MIDDLE1 = of("doom.loading_middle1");
	public static SoundEvent LOADING_MIDDLE2 = of("doom.loading_middle2");
	public static SoundEvent LOADING_MIDDLE3 = of("doom.loading_middle3");
	public static SoundEvent LOADING_MIDDLE4 = of("doom.loading_middle4");
	public static SoundEvent LOADING_END = of("doom.loading_end");

	public static SoundEvent QUICK1_1 = of("doom.quick1_1");
	public static SoundEvent QUICK1_2 = of("doom.quick1_2");
	public static SoundEvent QUICK1_3 = of("doom.quick1_3");

	public static SoundEvent QUICK2_1 = of("doom.quick2_1");
	public static SoundEvent QUICK2_2 = of("doom.quick2_2");
	public static SoundEvent QUICK2_3 = of("doom.quick2_3");

	public static SoundEvent QUICK3_1 = of("doom.quick3_1");
	public static SoundEvent QUICK3_2 = of("doom.quick3_2");
	public static SoundEvent QUICK3_3 = of("doom.quick3_3");

	public static SoundEvent E1M1 = of("doom.e1m1");

	public static SoundEvent IMP_AMBIENT = of("doom.imp_ambient");
	public static SoundEvent IMP_DEATH = of("doom.imp_death");
	public static SoundEvent IMP_HURT = of("doom.imp_hurt");
	public static SoundEvent IMP_STEP = of("doom.imp_step");

	public static SoundEvent ARCHVILE_DEATH = of("doom.arch_vile_death");
	public static SoundEvent ARCHVILE_HURT = of("doom.arch_vile_hit");
	public static SoundEvent ARCHVILE_AMBIENT = of("doom.arch_vile_idle");
	public static SoundEvent ARCHVILE_PORTAL = of("doom.arch_vile_portal");
	public static SoundEvent ARCHVILE_SCREAM = of("doom.arch_vile_scream");
	public static SoundEvent ARCHVILE_STARE = of("doom.arch_vile_stare");

	public static SoundEvent BARON_AMBIENT = of("doom.baron_angry");
	public static SoundEvent BARON_DEATH = of("doom.baron_death");
	public static SoundEvent BARON_HURT = of("doom.baron_hurt");
	public static SoundEvent BARON_STEP = of("doom.baron_say");

	public static SoundEvent PINKY_AMBIENT = of("doom.pinky_idle");
	public static SoundEvent PINKY_DEATH = of("doom.pinky_death");
	public static SoundEvent PINKY_HURT = of("doom.pinky_hurt");
	public static SoundEvent PINKY_STEP = of("doom.pinky_step");

	public static SoundEvent LOST_SOUL_DEATH = of("doom.lost_soul_death");
	public static SoundEvent LOST_SOUL_AMBIENT = of("doom.lost_soul_say");

	public static SoundEvent CACODEMON_AMBIENT = of("doom.cacodemon_moan");
	public static SoundEvent CACODEMON_DEATH = of("doom.cacodemon_death");
	public static SoundEvent CACODEMON_HURT = of("doom.cacodemon_hit");
	public static SoundEvent CACODEMON_AFFECTIONATE_SCREAM = of("doom.cacodemon_affectionate_scream");
	public static SoundEvent CACODEMON_CHARGE = of("doom.cacodemon-charge");
	public static SoundEvent CACODEMON_FIREBALL = of("doom.cacodemon-fireball");
	public static SoundEvent CACODEMON_SCREAM = of("doom.cacodemon-scream");

	public static SoundEvent SPIDERDEMON_AMBIENT = of("doom.spiderdemon_step");
	public static SoundEvent SPIDERDEMON_DEATH = of("doom.spiderdemon_death");
	public static SoundEvent SPIDERDEMON_HURT = of("doom.spiderdemon_say");

	public static SoundEvent ZOMBIEMAN_AMBIENT = of("doom.zombieman_idle");
	public static SoundEvent ZOMBIEMAN_DEATH = of("doom.zombieman_death");
	public static SoundEvent ZOMBIEMAN_HURT = of("doom.zombieman_hurt");

	public static SoundEvent CYBERDEMON_AMBIENT = of("doom.cyberdemon_throw");
	public static SoundEvent CYBERDEMON_DEATH = of("doom.cyberdemon_death");
	public static SoundEvent CYBERDEMON_HURT = of("doom.cyberdemon_hit");
	public static SoundEvent CYBERDEMON_STEP = of("doom.cyberdemon_walk");

	public static SoundEvent MANCUBUS_AMBIENT = of("doom.mancubus_say");
	public static SoundEvent MANCUBUS_DEATH = of("doom.mancubus_death");
	public static SoundEvent MANCUBUS_HURT = of("doom.mancubus_hit");
	public static SoundEvent MANCUBUS_STEP = of("doom.mancubus_walk");

	public static SoundEvent REVENANT_AMBIENT = of("doom.revenants_say");
	public static SoundEvent REVENANT_DEATH = of("doom.revenant_death");
	public static SoundEvent REVENANT_HURT = of("doom.revenant_hit");

	public static SoundEvent HELLKNIGHT_AMBIENT = of("doom.hellknight_say");
	public static SoundEvent HELLKNIGHT_DEATH = of("doom.hellknight_death");
	public static SoundEvent HELLKNIGHT_HURT = of("doom.hellknight_hurt");

	public static SoundEvent PAIN_AMBIENT = of("doom.pain_say");
	public static SoundEvent PAIN_DEATH = of("doom.pain_death");
	public static SoundEvent PAIN_HURT = of("doom.pain_hurt");

	public static SoundEvent ICON_AMBIENT = of("doom.icon_ambient");
	public static SoundEvent ICON_DEATH = of("doom.icon_death");
	public static SoundEvent ICON_HURT = of("doom.icon_hurt");

	static SoundEvent of(String id) {
		SoundEvent sound = new SoundEvent(new Identifier(DoomMod.MODID, id));
		Registry.register(Registry.SOUND_EVENT, new Identifier(DoomMod.MODID, id), sound);
		return sound;
	}

}
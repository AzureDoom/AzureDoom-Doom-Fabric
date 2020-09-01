package mod.azure.doom.util.registry;

import mod.azure.doom.DoomMod;
import mod.azure.doom.item.ArgentEnergyItem;
import mod.azure.doom.item.DaisyItem;
import mod.azure.doom.item.E1M1MusicDisc;
import mod.azure.doom.item.UnopenedItem;
import mod.azure.doom.item.ammo.ArgentBolt;
import mod.azure.doom.item.ammo.BFGCell;
import mod.azure.doom.item.ammo.ChaingunAmmo;
import mod.azure.doom.item.ammo.ClipAmmo;
import mod.azure.doom.item.ammo.EnergyCell;
import mod.azure.doom.item.ammo.Rocket;
import mod.azure.doom.item.ammo.ShellAmmo;
import mod.azure.doom.item.ammo.UnmaykrBolt;
import mod.azure.doom.item.armor.AstroDoomArmor;
import mod.azure.doom.item.armor.ClassicDoomArmor;
import mod.azure.doom.item.armor.CrimsonDoomArmor;
import mod.azure.doom.item.armor.CultistDoomArmor;
import mod.azure.doom.item.armor.DemonicDoomArmor;
import mod.azure.doom.item.armor.DoomArmor;
import mod.azure.doom.item.armor.DoomicornDoomArmor;
import mod.azure.doom.item.armor.EmberDoomArmor;
import mod.azure.doom.item.armor.GoldDoomArmor;
import mod.azure.doom.item.armor.MaykrDoomArmor;
import mod.azure.doom.item.armor.MidnightDoomArmor;
import mod.azure.doom.item.armor.MulletDoomArmor;
import mod.azure.doom.item.armor.NightmareDoomArmor;
import mod.azure.doom.item.armor.PainterDoomArmor;
import mod.azure.doom.item.armor.PhobosDoomArmor;
import mod.azure.doom.item.armor.PraetorDoomArmor;
import mod.azure.doom.item.armor.PurplePonyDoomArmor;
import mod.azure.doom.item.armor.SentinelDoomArmor;
import mod.azure.doom.item.armor.ZombieDoomArmor;
import mod.azure.doom.item.eggs.DoomSpawnEgg;
import mod.azure.doom.item.entityweapons.BarenBlastItem;
import mod.azure.doom.item.entityweapons.CyberdemonAttackItem;
import mod.azure.doom.item.entityweapons.SpiderdemonAttackItem;
import mod.azure.doom.item.powerup.InmortalSphereItem;
import mod.azure.doom.item.powerup.InvisibleSphereItem;
import mod.azure.doom.item.powerup.MegaSphereItem;
import mod.azure.doom.item.powerup.PowerSphereItem;
import mod.azure.doom.item.powerup.SoulCubeItem;
import mod.azure.doom.item.tools.ArgentAxe;
import mod.azure.doom.item.tools.ArgentHoe;
import mod.azure.doom.item.tools.ArgentPaxel;
import mod.azure.doom.item.tools.ArgentPickaxe;
import mod.azure.doom.item.tools.ArgentShovel;
import mod.azure.doom.item.weapons.ArgentSword;
import mod.azure.doom.item.weapons.AxeMarauderItem;
import mod.azure.doom.item.weapons.BFG;
import mod.azure.doom.item.weapons.Ballista;
import mod.azure.doom.item.weapons.Chaingun;
import mod.azure.doom.item.weapons.Chainsaw;
import mod.azure.doom.item.weapons.PistolItem;
import mod.azure.doom.item.weapons.PlasmaGun;
import mod.azure.doom.item.weapons.RocketLauncher;
import mod.azure.doom.item.weapons.Shotgun;
import mod.azure.doom.item.weapons.SuperShotgun;
import mod.azure.doom.item.weapons.SwordCrucibleItem;
import mod.azure.doom.item.weapons.Unmaykr;
import mod.azure.doom.util.enums.DoomArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DoomItems {

	public static BFG BFG = item(new BFG(), "bfg9000");
	public static Shotgun SG = item(new Shotgun(), "shotgun");
	public static DaisyItem DAISY = item(new DaisyItem(), "daisy");
	public static Rocket ROCKET = item(new Rocket(2.2F), "rocket");
	public static Unmaykr UNMAKYR = item(new Unmaykr(), "unmaykr");
	public static Ballista BALLISTA = item(new Ballista(), "ballista");
	public static Chaingun CHAINGUN = item(new Chaingun(), "chaingun");
	public static Chainsaw CHAINSAW = item(new Chainsaw(), "chainsaw");
	public static PistolItem PISTOL = item(new PistolItem(), "pistol");
	public static ClipAmmo BULLETS = item(new ClipAmmo(1.2F), "bullets");
	public static BFGCell BFG_CELL = item(new BFGCell(10.2F), "bfg_cell");
	public static PlasmaGun PLASMAGUN = item(new PlasmaGun(), "plasmagun");
	public static ArgentAxe ARGENT_AXE = item(new ArgentAxe(), "argent_axe");
	public static ArgentHoe ARGENT_HOE = item(new ArgentHoe(), "argent_hoe");
	public static SuperShotgun SSG = item(new SuperShotgun(), "supershotgun");
	public static SoulCubeItem SOULCUBE = item(new SoulCubeItem(), "soulcube");
	public static MegaSphereItem MEGA = item(new MegaSphereItem(), "megasphere");
	public static ArgentBolt ARGENT_BOLT = item(new ArgentBolt(1.7F), "argent_bolt");
	public static ArgentPaxel ARGENT_PAXEL = item(new ArgentPaxel(), "argent_paxel");
	public static ArgentSword ARGENT_SWORD = item(new ArgentSword(), "argent_sword");
	public static PowerSphereItem POWER = item(new PowerSphereItem(), "powersphere");
	public static EnergyCell ENERGY_CELLS = item(new EnergyCell(2.2F), "energy_cells");
	public static ArgentShovel ARGENT_SHOVEL = item(new ArgentShovel(), "argent_shovel");
	public static ShellAmmo SHOTGUN_SHELLS = item(new ShellAmmo(2.0F), "shotgun_shells");
	public static UnmaykrBolt UNMAKRY_BOLT = item(new UnmaykrBolt(10.2F), "unmaykr_bolt");
	public static BarenBlastItem BARENBLAST = item(new BarenBlastItem(2.0F), "barenblast");
	public static UnopenedItem AXE_CLOSED = item(new UnopenedItem(), "axe_marauder_closed");
	public static ArgentPickaxe ARGENT_PICKAXE = item(new ArgentPickaxe(), "argent_pickaxe");
	public static AxeMarauderItem AXE_OPEN = item(new AxeMarauderItem(), "axe_marauder_open");
	public static UnopenedItem SWORD_CLOSED = item(new UnopenedItem(), "cruciblesword_closed");
	public static E1M1MusicDisc E1M1_MUSIC_DISC = item(new E1M1MusicDisc(), "e1m1_music_disc");
	public static RocketLauncher ROCKETLAUNCHER = item(new RocketLauncher(), "rocketlauncher");
	public static ArgentEnergyItem ARGENT_ENERGY = item(new ArgentEnergyItem(), "argent_energy");
	public static InmortalSphereItem INMORTAL = item(new InmortalSphereItem(), "inmortalsphere");
	public static ChaingunAmmo CHAINGUN_BULLETS = item(new ChaingunAmmo(10.0F), "chaingunbullets");
	public static SwordCrucibleItem CRUCIBLESWORD = item(new SwordCrucibleItem(), "cruciblesword");
	public static InvisibleSphereItem INVISIBLE = item(new InvisibleSphereItem(), "invisiblesphere");
	public static CyberdemonAttackItem CYBERDEMONATTACK = item(new CyberdemonAttackItem(), "cyberdemonattack");
	public static SpiderdemonAttackItem SPIDERDEMONATTACK = item(new SpiderdemonAttackItem(), "spiderdemonattack");
	
	// Spawn Eggs
	public static DoomSpawnEgg IMP_SPAWN_EGG = item(new DoomSpawnEgg(MobEntityRegister.IMP), "imp_spawn_egg");
	public static DoomSpawnEgg PINKY_SPAWN_EGG = item("pinky_spawn_egg", new DoomSpawnEgg(MobEntityRegister.PINKY));
	public static DoomSpawnEgg ARCHVILE_SPAWN_EGG = item("archvile_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.ARCHVILE));
	public static DoomSpawnEgg BARON_SPAWN_EGG = item("baron_spawn_egg", new DoomSpawnEgg(MobEntityRegister.BARON));
	public static DoomSpawnEgg CACODEMON_SPAWN_EGG = item("cacodemon_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.CACODEMON));
	public static DoomSpawnEgg MANCUBUS_SPAWN_EGG = item("mancubus_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.MANCUBUS));
	public static DoomSpawnEgg LOST_SOUL_SPAWN_EGG = item("lost_soul_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.LOST_SOUL));
	public static DoomSpawnEgg SPIDERDEMON_SPAWN_EGG = item("spiderdemon_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.SPIDERDEMON));
	public static DoomSpawnEgg ZOMBIEMAN_SPAWN_EGG = item("zombieman_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.ZOMBIEMAN));
	public static DoomSpawnEgg CHAINGUNNER_SPAWN_EGG = item("chaingunner_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.CHAINGUNNER));
	public static DoomSpawnEgg HELLKNIGHT_SPAWN_EGG = item("hellknight_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.HELLKNIGHT));
	public static DoomSpawnEgg IMP2016_SPAWN_EGG = item("imp2016_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.IMP2016));
	public static DoomSpawnEgg MARAUDER_SPAWN_EGG = item("marauder_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.MARAUDER));
	public static DoomSpawnEgg PAIN_SPAWN_EGG = item("pain_spawn_egg", new DoomSpawnEgg(MobEntityRegister.PAIN));
	public static DoomSpawnEgg REVENANT_SPAWN_EGG = item("revenant_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.REVENANT));
	public static DoomSpawnEgg SHOTGUNGUY_SPAWN_EGG = item("shotgunguy_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.SHOTGUNGUY));
	public static DoomSpawnEgg CYBERDEMON_SPAWN_EGG = item("cyberdemon_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.CYBERDEMON));
	public static DoomSpawnEgg CYBERDEMON2016_SPAWN_EGG = item("cyberdemon2016_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.CYBERDEMON2016));
	public static DoomSpawnEgg ICON_SPAWN_EGG = item("icon_of_sin_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.ICONOFSIN));
	public static DoomSpawnEgg UNWILLING_SPAWN_EGG = item("unwilling_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.UNWILLING));
	public static DoomSpawnEgg POSSESSED_SCIENTIST_SPAWN_EGG = item("possessed_scientist_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.POSSESSEDSCIENTIST));
	public static DoomSpawnEgg POSSESSED_SOLDIER_SPAWN_EGG = item("possessed_soldier_spawn_egg",
			new DoomSpawnEgg(MobEntityRegister.POSSESSEDSOLDIER));

	// Armor
	public static DoomArmor DOOM_HELMET = item(new DoomArmor(DoomArmorMaterial.DOOM_ARMOR, EquipmentSlot.HEAD),
			"doom_helmet");
	public static DoomArmor DOOM_CHESTPLATE = item(new DoomArmor(DoomArmorMaterial.DOOM_ARMOR, EquipmentSlot.CHEST),
			"doom_chestplate");
	public static DoomArmor DOOM_LEGGINGS = item(new DoomArmor(DoomArmorMaterial.DOOM_ARMOR, EquipmentSlot.LEGS),
			"doom_leggings");
	public static DoomArmor DOOM_BOOTS = item(new DoomArmor(DoomArmorMaterial.DOOM_ARMOR, EquipmentSlot.FEET),
			"doom_boots");
	public static PraetorDoomArmor PRAETOR_DOOM_HELMET = item("praetor_doom_helmet",
			new PraetorDoomArmor(DoomArmorMaterial.PRAETOR_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static PraetorDoomArmor PRAETOR_DOOM_CHESTPLATE = item("praetor_doom_chestplate",
			new PraetorDoomArmor(DoomArmorMaterial.PRAETOR_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static PraetorDoomArmor PRAETOR_DOOM_LEGGINGS = item("praetor_doom_leggings",
			new PraetorDoomArmor(DoomArmorMaterial.PRAETOR_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static PraetorDoomArmor PRAETOR_DOOM_BOOTS = item("praetor_doom_boots",
			new PraetorDoomArmor(DoomArmorMaterial.PRAETOR_DOOM_ARMOR, EquipmentSlot.FEET));
	public static AstroDoomArmor ASTRO_DOOM_HELMET = item("astro_doom_helmet",
			new AstroDoomArmor(DoomArmorMaterial.ASTRO_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static AstroDoomArmor ASTRO_DOOM_CHESTPLATE = item("astro_doom_chestplate",
			new AstroDoomArmor(DoomArmorMaterial.ASTRO_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static AstroDoomArmor ASTRO_DOOM_LEGGINGS = item("astro_doom_leggings",
			new AstroDoomArmor(DoomArmorMaterial.ASTRO_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static AstroDoomArmor ASTRO_DOOM_BOOTS = item("astro_doom_boots",
			new AstroDoomArmor(DoomArmorMaterial.ASTRO_DOOM_ARMOR, EquipmentSlot.FEET));
	public static CrimsonDoomArmor CRIMSON_DOOM_HELMET = item("crimson_doom_helmet",
			new CrimsonDoomArmor(DoomArmorMaterial.CRIMSON_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static CrimsonDoomArmor CRIMSON_DOOM_CHESTPLATE = item("crimson_doom_chestplate",
			new CrimsonDoomArmor(DoomArmorMaterial.CRIMSON_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static CrimsonDoomArmor CRIMSON_DOOM_LEGGINGS = item("crimson_doom_leggings",
			new CrimsonDoomArmor(DoomArmorMaterial.CRIMSON_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static CrimsonDoomArmor CRIMSON_DOOM_BOOTS = item("crimson_doom_boots",
			new CrimsonDoomArmor(DoomArmorMaterial.CRIMSON_DOOM_ARMOR, EquipmentSlot.FEET));
	public static MidnightDoomArmor MIDNIGHT_DOOM_HELMET = item("midnight_doom_helmet",
			new MidnightDoomArmor(DoomArmorMaterial.MIDNIGHT_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static MidnightDoomArmor MIDNIGHT_DOOM_CHESTPLATE = item("midnight_doom_chestplate",
			new MidnightDoomArmor(DoomArmorMaterial.MIDNIGHT_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static MidnightDoomArmor MIDNIGHT_DOOM_LEGGINGS = item("midnight_doom_leggings",
			new MidnightDoomArmor(DoomArmorMaterial.MIDNIGHT_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static MidnightDoomArmor MIDNIGHT_DOOM_BOOTS = item("midnight_doom_boots",
			new MidnightDoomArmor(DoomArmorMaterial.MIDNIGHT_DOOM_ARMOR, EquipmentSlot.FEET));
	public static DemonicDoomArmor DEMONIC_DOOM_HELMET = item("demonic_doom_helmet",
			new DemonicDoomArmor(DoomArmorMaterial.DEMONIC_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static DemonicDoomArmor DEMONIC_DOOM_CHESTPLATE = item("demonic_doom_chestplate",
			new DemonicDoomArmor(DoomArmorMaterial.DEMONIC_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static DemonicDoomArmor DEMONIC_DOOM_LEGGINGS = item("demonic_doom_leggings",
			new DemonicDoomArmor(DoomArmorMaterial.DEMONIC_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static DemonicDoomArmor DEMONIC_DOOM_BOOTS = item("demonic_doom_boots",
			new DemonicDoomArmor(DoomArmorMaterial.DEMONIC_DOOM_ARMOR, EquipmentSlot.FEET));
	public static SentinelDoomArmor SENTINEL_DOOM_HELMET = item("sentinel_doom_helmet",
			new SentinelDoomArmor(DoomArmorMaterial.SENTINEL_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static SentinelDoomArmor SENTINEL_DOOM_CHESTPLATE = item("sentinel_doom_chestplate",
			new SentinelDoomArmor(DoomArmorMaterial.SENTINEL_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static SentinelDoomArmor SENTINEL_DOOM_LEGGINGS = item("sentinel_doom_leggings",
			new SentinelDoomArmor(DoomArmorMaterial.SENTINEL_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static SentinelDoomArmor SENTINEL_DOOM_BOOTS = item("sentinel_doom_boots",
			new SentinelDoomArmor(DoomArmorMaterial.SENTINEL_DOOM_ARMOR, EquipmentSlot.FEET));
	public static EmberDoomArmor EMBER_DOOM_HELMET = item("ember_doom_helmet",
			new EmberDoomArmor(DoomArmorMaterial.EMBER_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static EmberDoomArmor EMBER_DOOM_CHESTPLATE = item("ember_doom_chestplate",
			new EmberDoomArmor(DoomArmorMaterial.EMBER_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static EmberDoomArmor EMBER_DOOM_LEGGINGS = item("ember_doom_leggings",
			new EmberDoomArmor(DoomArmorMaterial.EMBER_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static EmberDoomArmor EMBER_DOOM_BOOTS = item("ember_doom_boots",
			new EmberDoomArmor(DoomArmorMaterial.EMBER_DOOM_ARMOR, EquipmentSlot.FEET));
	public static ZombieDoomArmor ZOMBIE_DOOM_HELMET = item("zombie_doom_helmet",
			new ZombieDoomArmor(DoomArmorMaterial.ZOMBIE_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static ZombieDoomArmor ZOMBIE_DOOM_CHESTPLATE = item("zombie_doom_chestplate",
			new ZombieDoomArmor(DoomArmorMaterial.ZOMBIE_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static ZombieDoomArmor ZOMBIE_DOOM_LEGGINGS = item("zombie_doom_leggings",
			new ZombieDoomArmor(DoomArmorMaterial.ZOMBIE_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static ZombieDoomArmor ZOMBIE_DOOM_BOOTS = item("zombie_doom_boots",
			new ZombieDoomArmor(DoomArmorMaterial.ZOMBIE_DOOM_ARMOR, EquipmentSlot.FEET));
	public static PhobosDoomArmor PHOBOS_DOOM_HELMET = item("phobos_doom_helmet",
			new PhobosDoomArmor(DoomArmorMaterial.PHOBOS_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static PhobosDoomArmor PHOBOS_DOOM_CHESTPLATE = item("phobos_doom_chestplate",
			new PhobosDoomArmor(DoomArmorMaterial.PHOBOS_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static PhobosDoomArmor PHOBOS_DOOM_LEGGINGS = item("phobos_doom_leggings",
			new PhobosDoomArmor(DoomArmorMaterial.PHOBOS_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static PhobosDoomArmor PHOBOS_DOOM_BOOTS = item("phobos_doom_boots",
			new PhobosDoomArmor(DoomArmorMaterial.PHOBOS_DOOM_ARMOR, EquipmentSlot.FEET));
	public static NightmareDoomArmor NIGHTMARE_DOOM_HELMET = item("nightmare_doom_helmet",
			new NightmareDoomArmor(DoomArmorMaterial.NIGHTMARE_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static NightmareDoomArmor NIGHTMARE_DOOM_CHESTPLATE = item("nightmare_doom_chestplate",
			new NightmareDoomArmor(DoomArmorMaterial.NIGHTMARE_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static NightmareDoomArmor NIGHTMARE_DOOM_LEGGINGS = item("nightmare_doom_leggings",
			new NightmareDoomArmor(DoomArmorMaterial.NIGHTMARE_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static NightmareDoomArmor NIGHTMARE_DOOM_BOOTS = item("nightmare_doom_boots",
			new NightmareDoomArmor(DoomArmorMaterial.NIGHTMARE_DOOM_ARMOR, EquipmentSlot.FEET));
	public static PurplePonyDoomArmor PURPLEPONY_DOOM_HELMET = item("purplepony_doom_helmet",
			new PurplePonyDoomArmor(DoomArmorMaterial.PURPLEPONY_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static PurplePonyDoomArmor PURPLEPONY_DOOM_CHESTPLATE = item("purplepony_doom_chestplate",
			new PurplePonyDoomArmor(DoomArmorMaterial.PURPLEPONY_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static PurplePonyDoomArmor PURPLEPONY_DOOM_LEGGINGS = item("purplepony_doom_leggings",
			new PurplePonyDoomArmor(DoomArmorMaterial.PURPLEPONY_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static PurplePonyDoomArmor PURPLEPONY_DOOM_BOOTS = item("purplepony_doom_boots",
			new PurplePonyDoomArmor(DoomArmorMaterial.PURPLEPONY_DOOM_ARMOR, EquipmentSlot.FEET));
	public static DoomicornDoomArmor DOOMICORN_DOOM_HELMET = item("doomicorn_doom_helmet",
			new DoomicornDoomArmor(DoomArmorMaterial.DOOMICORN_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static DoomicornDoomArmor DOOMICORN_DOOM_CHESTPLATE = item("doomicorn_doom_chestplate",
			new DoomicornDoomArmor(DoomArmorMaterial.DOOMICORN_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static DoomicornDoomArmor DOOMICORN_DOOM_LEGGINGS = item("doomicorn_doom_leggings",
			new DoomicornDoomArmor(DoomArmorMaterial.DOOMICORN_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static DoomicornDoomArmor DOOMICORN_DOOM_BOOTS = item("doomicorn_doom_boots",
			new DoomicornDoomArmor(DoomArmorMaterial.DOOMICORN_DOOM_ARMOR, EquipmentSlot.FEET));
	public static GoldDoomArmor GOLD_DOOM_HELMET = item("gold_doom_helmet",
			new GoldDoomArmor(DoomArmorMaterial.GOLD_ARMOR, EquipmentSlot.HEAD));
	public static GoldDoomArmor GOLD_DOOM_CHESTPLATE = item("gold_doom_chestplate",
			new GoldDoomArmor(DoomArmorMaterial.GOLD_ARMOR, EquipmentSlot.CHEST));
	public static GoldDoomArmor GOLD_DOOM_LEGGINGS = item("gold_doom_leggings",
			new GoldDoomArmor(DoomArmorMaterial.GOLD_ARMOR, EquipmentSlot.LEGS));
	public static GoldDoomArmor GOLD_DOOM_BOOTS = item("gold_doom_boots",
			new GoldDoomArmor(DoomArmorMaterial.GOLD_ARMOR, EquipmentSlot.FEET));
	public static DoomArmor TWENTY_FIVE_DOOM_HELMET = item("twenty_five_helmet",
			new DoomArmor(DoomArmorMaterial.TWENTY_FIVE_ARMOR, EquipmentSlot.HEAD));
	public static DoomArmor TWENTY_FIVE_DOOM_CHESTPLATE = item("twenty_five_chestplate",
			new DoomArmor(DoomArmorMaterial.TWENTY_FIVE_ARMOR, EquipmentSlot.CHEST));
	public static DoomArmor TWENTY_FIVE_DOOM_LEGGINGS = item("twenty_five_leggings",
			new DoomArmor(DoomArmorMaterial.TWENTY_FIVE_ARMOR, EquipmentSlot.LEGS));
	public static DoomArmor TWENTY_FIVE_DOOM_BOOTS = item("twenty_five_boots",
			new DoomArmor(DoomArmorMaterial.TWENTY_FIVE_ARMOR, EquipmentSlot.FEET));
	public static ClassicDoomArmor CLASSIC_DOOM_HELMET = item("classic_doom_helmet",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static ClassicDoomArmor CLASSIC_DOOM_CHESTPLATE = item("classic_doom_chestplate",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static ClassicDoomArmor CLASSIC_DOOM_LEGGINGS = item("classic_doom_leggings",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static ClassicDoomArmor CLASSIC_RED_DOOM_CHESTPLATE = item("classic_red_chestplate",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_RED_ARMOR, EquipmentSlot.CHEST));
	public static ClassicDoomArmor CLASSIC_RED_DOOM_LEGGINGS = item("classic_red_leggings",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_RED_ARMOR, EquipmentSlot.LEGS));
	public static ClassicDoomArmor CLASSIC_INDIGO_DOOM_CHESTPLATE = item("classic_black_chestplate",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_INDIGO_ARMOR, EquipmentSlot.CHEST));
	public static ClassicDoomArmor CLASSIC_INDIGO_DOOM_LEGGINGS = item("classic_black_leggings",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_INDIGO_ARMOR, EquipmentSlot.LEGS));
	public static ClassicDoomArmor CLASSIC_BRONZE_DOOM_CHESTPLATE = item("classic_bronze_chestplate",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_BRONZE_ARMOR, EquipmentSlot.CHEST));
	public static ClassicDoomArmor CLASSIC_BRONZE_DOOM_LEGGINGS = item("classic_bronze_leggings",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_BRONZE_ARMOR, EquipmentSlot.LEGS));
	public static ClassicDoomArmor CLASSIC_DOOM_BOOTS = item("classic_doom_boots",
			new ClassicDoomArmor(DoomArmorMaterial.CLASSIC_DOOM_ARMOR, EquipmentSlot.FEET));
	public static DoomArmor BRONZE_DOOM_HELMET = item("bronze_doom_helmet",
			new DoomArmor(DoomArmorMaterial.BRONZE_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static DoomArmor BRONZE_DOOM_CHESTPLATE = item("bronze_doom_chestplate",
			new DoomArmor(DoomArmorMaterial.BRONZE_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static DoomArmor BRONZE_DOOM_LEGGINGS = item("bronze_doom_leggings",
			new DoomArmor(DoomArmorMaterial.BRONZE_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static DoomArmor BRONZE_DOOM_BOOTS = item("bronze_doom_boots",
			new DoomArmor(DoomArmorMaterial.BRONZE_DOOM_ARMOR, EquipmentSlot.FEET));
	public static CultistDoomArmor CULTIST_DOOM_HELMET = item("cultist_doom_helmet",
			new CultistDoomArmor(DoomArmorMaterial.CULTIST_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static CultistDoomArmor CULTIST_DOOM_CHESTPLATE = item("cultist_doom_chestplate",
			new CultistDoomArmor(DoomArmorMaterial.CULTIST_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static CultistDoomArmor CULTIST_DOOM_LEGGINGS = item("cultist_doom_leggings",
			new CultistDoomArmor(DoomArmorMaterial.CULTIST_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static CultistDoomArmor CULTIST_DOOM_BOOTS = item("cultist_doom_boots",
			new CultistDoomArmor(DoomArmorMaterial.CULTIST_DOOM_ARMOR, EquipmentSlot.FEET));
	public static MaykrDoomArmor MAYKR_DOOM_HELMET = item("maykr_doom_helmet",
			new MaykrDoomArmor(DoomArmorMaterial.MAYKR_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static MaykrDoomArmor MAYKR_DOOM_CHESTPLATE = item("maykr_doom_chestplate",
			new MaykrDoomArmor(DoomArmorMaterial.MAYKR_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static MaykrDoomArmor MAYKR_DOOM_LEGGINGS = item("maykr_doom_leggings",
			new MaykrDoomArmor(DoomArmorMaterial.MAYKR_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static MaykrDoomArmor MAYKR_DOOM_BOOTS = item("maykr_doom_boots",
			new MaykrDoomArmor(DoomArmorMaterial.MAYKR_DOOM_ARMOR, EquipmentSlot.FEET));
	public static PainterDoomArmor PAINTER_DOOM_HELMET = item("painter_doom_helmet",
			new PainterDoomArmor(DoomArmorMaterial.PAINTER_DOOM_ARMOR, EquipmentSlot.HEAD));
	public static PainterDoomArmor PAINTER_DOOM_CHESTPLATE = item("painter_doom_chestplate",
			new PainterDoomArmor(DoomArmorMaterial.PAINTER_DOOM_ARMOR, EquipmentSlot.CHEST));
	public static PainterDoomArmor PAINTER_DOOM_LEGGINGS = item("painter_doom_leggings",
			new PainterDoomArmor(DoomArmorMaterial.PAINTER_DOOM_ARMOR, EquipmentSlot.LEGS));
	public static PainterDoomArmor PAINTER_DOOM_BOOTS = item("painter_doom_boots",
			new PainterDoomArmor(DoomArmorMaterial.PAINTER_DOOM_ARMOR, EquipmentSlot.FEET));
	public static MulletDoomArmor MULLET_DOOM_HELMET1 = item("redneck_doom1_helmet",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK1_ARMOR, EquipmentSlot.HEAD));
	public static MulletDoomArmor MULLET_DOOM_HELMET2 = item("redneck_doom2_helmet",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK2_ARMOR, EquipmentSlot.HEAD));
	public static MulletDoomArmor MULLET_DOOM_HELMET3 = item("redneck_doom3_helmet",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK3_ARMOR, EquipmentSlot.HEAD));
	public static MulletDoomArmor MULLET_DOOM_CHESTPLATE1 = item("redneck_doom1_chestplate",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK1_ARMOR, EquipmentSlot.CHEST));
	public static MulletDoomArmor MULLET_DOOM_CHESTPLATE2 = item("redneck_doom2_chestplate",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK2_ARMOR, EquipmentSlot.CHEST));
	public static MulletDoomArmor MULLET_DOOM_CHESTPLATE3 = item("redneck_doom3_chestplate",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK3_ARMOR, EquipmentSlot.CHEST));
	public static MulletDoomArmor MULLET_DOOM_LEGGINGS1 = item("redneck_doom1_leggings",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK1_ARMOR, EquipmentSlot.LEGS));
	public static MulletDoomArmor MULLET_DOOM_LEGGINGS2 = item("redneck_doom2_leggings",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK2_ARMOR, EquipmentSlot.LEGS));
	public static MulletDoomArmor MULLET_DOOM_LEGGINGS3 = item("redneck_doom3_leggings",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK3_ARMOR, EquipmentSlot.LEGS));
	public static MulletDoomArmor MULLET_DOOM_BOOTS1 = item("redneck_doom1_boots",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK1_ARMOR, EquipmentSlot.FEET));
	public static MulletDoomArmor MULLET_DOOM_BOOTS2 = item("redneck_doom2_boots",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK2_ARMOR, EquipmentSlot.FEET));
	public static MulletDoomArmor MULLET_DOOM_BOOTS3 = item("redneck_doom3_boots",
			new MulletDoomArmor(DoomArmorMaterial.REDNECK3_ARMOR, EquipmentSlot.FEET));

	static <T extends Item> T item(T c, String id) {
		Registry.register(Registry.ITEM, new Identifier(DoomMod.MODID, id), c);
		return c;
	}

	static <T extends Item> T item(String id, T c) {
		Registry.register(Registry.ITEM, new Identifier(DoomMod.MODID, id), c);
		return c;
	}
}
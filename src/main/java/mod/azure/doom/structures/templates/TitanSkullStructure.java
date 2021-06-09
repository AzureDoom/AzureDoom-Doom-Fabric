package mod.azure.doom.structures.templates;

import mod.azure.doom.structures.generator.TitanSkullGenerator;
import mod.azure.doom.util.registry.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class TitanSkullStructure extends StructureFeature<StructurePoolFeatureConfig> {

	public TitanSkullStructure() {
		super(StructurePoolFeatureConfig.CODEC);
	}

	@Override
	public StructureStartFactory<StructurePoolFeatureConfig> getStructureStartFactory() {
		return TitanSkullStructure.Start::new;
	}

	private static final Pool<SpawnEntry> STRUCTURE_MONSTERS = Pool
			.of(new SpawnSettings.SpawnEntry(ModEntityTypes.LOST_SOUL, 100, 4, 9));

	@Override
	public Pool<SpawnEntry> getMonsterSpawns() {
		return STRUCTURE_MONSTERS;
	}

	@Override
	protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long worldSeed,
			ChunkRandom random, ChunkPos pos, Biome biome, ChunkPos chunkPos, StructurePoolFeatureConfig config,
			HeightLimitView world) {
		BlockPos centerOfChunk = new BlockPos(pos.x, 0, pos.z);
		int landHeight = chunkGenerator.getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(),
				Heightmap.Type.WORLD_SURFACE_WG, world);
		VerticalBlockSample columnOfBlocks = chunkGenerator.getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ(),
				world);
		BlockState topBlock = columnOfBlocks.getState(centerOfChunk.up(landHeight));
		return topBlock.getFluidState().isEmpty();
	}

	public static class Start extends MarginedStructureStart<StructurePoolFeatureConfig> {
		public Start(StructureFeature<StructurePoolFeatureConfig> structureFeature, ChunkPos chunkPos, int i, long l) {
			super(structureFeature, chunkPos, i, l);
		}

		@Override
		public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator,
				StructureManager manager, ChunkPos pos, Biome biome, StructurePoolFeatureConfig config,
				HeightLimitView world) {
			TitanSkullGenerator.init();
			StructurePoolBasedGenerator.method_30419(registryManager, config, PoolStructurePiece::new, chunkGenerator,
					manager, new BlockPos.Mutable(pos.x, 0, pos.z), this, this.random, false,
					true, world);
			this.children.forEach(piece -> piece.translate(0, 0, 0));
			this.children.forEach(piece -> piece.getBoundingBox().minY -= 1);
			this.setBoundingBoxFromChildren();
		}

	}
}
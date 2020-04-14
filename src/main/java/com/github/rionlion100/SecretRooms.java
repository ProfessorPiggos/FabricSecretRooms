package com.github.rionlion100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SecretRooms implements ModInitializer {
	public static Map<Block, OneWayGlassBlock> ONE_WAY_GLASS_MAP = new HashMap<Block, OneWayGlassBlock>();
	public static List<Block> ONE_WAY_GLASS_SOURCE_BLOCKS = new ArrayList<Block>();

	private void registerOneWayGlassBlocks() {
		for (int i = 0; i < ONE_WAY_GLASS_SOURCE_BLOCKS.size(); i++){
			Block block = ONE_WAY_GLASS_SOURCE_BLOCKS.get(i);
			ONE_WAY_GLASS_MAP.put(block, new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque().build()));
			Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_"+block.getTranslationKey().replace("block.minecraft.", "")), ONE_WAY_GLASS_MAP.get(block));
			Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_"+block.getTranslationKey().replace("block.minecraft.", "")), new BlockItem(ONE_WAY_GLASS_MAP.get(block), new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		}
	}
	
	public static final Item CAMO_PASTE = new Item(new Item.Settings().group(SecretRooms.MAIN_GROUP).recipeRemainder(Items.BUCKET).maxCount(16));
	public static final TorchLeverBlock TORCH_LEVER_BLOCK = new TorchLeverBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).lightLevel(createLightLevelFromBlockState(15)).noCollision());
	public static final TorchLeverBlock SOUL_FIRE_TORCH_LEVER_BLOCK = new SoulTorchLeverBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).lightLevel(createLightLevelFromBlockState(10)).noCollision());
	public static final SolidAirBlock SOLID_AIR_BLOCK = new SolidAirBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).nonOpaque().build());
	
	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(
		new Identifier("secretrooms", "general"))
		.icon(() -> new ItemStack(SecretRooms.CAMO_PASTE))
		.appendItems(stacks ->
		{
			stacks.add(new ItemStack(SecretRooms.CAMO_PASTE));
			stacks.add(new ItemStack(SecretRooms.TORCH_LEVER_BLOCK));
			stacks.add(new ItemStack(SecretRooms.SOUL_FIRE_TORCH_LEVER_BLOCK));
			stacks.add(new ItemStack(SecretRooms.SOLID_AIR_BLOCK));
			for (int i = 0; i < ONE_WAY_GLASS_SOURCE_BLOCKS.size(); i++){
				Block block = ONE_WAY_GLASS_SOURCE_BLOCKS.get(i);
				OneWayGlassBlock oneWayGlassBlock = ONE_WAY_GLASS_MAP.get(block);
				stacks.add(new ItemStack(oneWayGlassBlock));
			}
		}).build();

	private static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel) {
		return (blockState) -> {
			return !(Boolean)blockState.get(Properties.POWERED) ? litLevel : 0;
		};
		}
				
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "camo_paste"), CAMO_PASTE);
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "torch_lever"), TORCH_LEVER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "torch_lever"), new BlockItem(TORCH_LEVER_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "soul_fire_torch_lever"), SOUL_FIRE_TORCH_LEVER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "soul_fire_torch_lever"), new BlockItem(SOUL_FIRE_TORCH_LEVER_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "solid_air"), SOLID_AIR_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "solid_air"), new BlockItem(SOLID_AIR_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));

		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.OAK_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.ACACIA_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.DARK_OAK_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.JUNGLE_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.BIRCH_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.SPRUCE_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.CRIMSON_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.WARPED_PLANKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.DIRT);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.STONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.SMOOTH_STONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.STONE_BRICKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.CRACKED_STONE_BRICKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.COBBLESTONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.GRASS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.SAND);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.GRAVEL);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.CLAY);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.BLACKSTONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.POLISHED_BLACKSTONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.GILDED_BLACKSTONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.POLISHED_BLACKSTONE_BRICKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.NETHERRACK);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.END_STONE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.PURPUR_BLOCK);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.PURPUR_PILLAR);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.ANDESITE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.POLISHED_ANDESITE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.DIORITE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.POLISHED_DIORITE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.GRANITE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.POLISHED_GRANITE);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.BRICKS);
		ONE_WAY_GLASS_SOURCE_BLOCKS.add(Blocks.NETHER_BRICKS);

		registerOneWayGlassBlocks();
	}
}	

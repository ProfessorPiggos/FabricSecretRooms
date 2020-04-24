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
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SecretRooms implements ModInitializer {
	public static Map<Block, OneWayGlassBlock> glassCopyBlockMap = new HashMap<Block, OneWayGlassBlock>();
	public static Map<Block, CamoDoorBlock> doorCopyBlockMap = new HashMap<Block, CamoDoorBlock>();
	public static List<Block> copyBlockList = new ArrayList<Block>();
	public static final Item CAMO_PASTE = new Item(new Item.Settings().group(SecretRooms.MAIN_GROUP).recipeRemainder(Items.BUCKET).maxCount(16));
	public static final TorchLeverBlock TORCH_LEVER_BLOCK = new TorchLeverBlock(AbstractBlock.Settings.copy(Blocks.TORCH).lightLevel(createLightLevelFromBlockState(10)));
	public static final TorchLeverBlock SOUL_TORCH_LEVER_BLOCK = new SoulTorchLeverBlock(AbstractBlock.Settings.copy(Blocks.SOUL_TORCH).lightLevel(createLightLevelFromBlockState(10)));
	public static final SolidAirBlock SOLID_AIR_BLOCK = new SolidAirBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).nonOpaque().build());
	public static final LanternButtonBlock LANTERN_BUTTON_BLOCK = new LanternButtonBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).lightLevel(createLightLevelFromBlockState(15)));
	public static final LanternButtonBlock SOUL_LANTERN_BUTTON_BLOCK = new LanternButtonBlock(AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN).lightLevel(createLightLevelFromBlockState(10)));
	private static final String MOD_ID = "secretrooms";

	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "general"))
		.icon(() -> new ItemStack(SecretRooms.CAMO_PASTE))
		.appendItems(stacks ->
		{
			stacks.add(new ItemStack(SecretRooms.CAMO_PASTE));
			stacks.add(new ItemStack(SecretRooms.SOLID_AIR_BLOCK));
			stacks.add(new ItemStack(SecretRooms.TORCH_LEVER_BLOCK));
			stacks.add(new ItemStack(SecretRooms.SOUL_TORCH_LEVER_BLOCK));
			stacks.add(new ItemStack(SecretRooms.LANTERN_BUTTON_BLOCK));
			stacks.add(new ItemStack(SecretRooms.SOUL_LANTERN_BUTTON_BLOCK));
			for (int i = 0; i < copyBlockList.size(); i++){
				Block block = copyBlockList.get(i);
				OneWayGlassBlock oneWayGlassBlock = glassCopyBlockMap.get(block);
				stacks.add(new ItemStack(oneWayGlassBlock));
			}
			for (int i = 0; i < copyBlockList.size(); i++){
				Block block = copyBlockList.get(i);
				CamoDoorBlock camoDoorBlock = doorCopyBlockMap.get(block);
				stacks.add(new ItemStack(camoDoorBlock));
				}

		}).build();

	private void registerOneWayGlassBlocks() {
		for (int i = 0; i < copyBlockList.size(); i++){
			Block block = copyBlockList.get(i);
			glassCopyBlockMap.put(block, new OneWayGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).build()));
			Registry.register(Registry.BLOCK, new Identifier(MOD_ID , "one_way_glass_"+block.getTranslationKey().replace("block.minecraft.", "")), glassCopyBlockMap.get(block));
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, "one_way_glass_"+block.getTranslationKey().replace("block.minecraft.", "")), new BlockItem(glassCopyBlockMap.get(block), new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		}
	}

	private void registerCamoDoorBlocks() {
		for (int i = 0; i < copyBlockList.size(); i++){
			Block block = copyBlockList.get(i);
			doorCopyBlockMap.put(block, new CamoDoorBlock(FabricBlockSettings.copy(block).build(), block.getSoundGroup(block.getDefaultState())));
			Registry.register(Registry.BLOCK, new Identifier(MOD_ID , block.getTranslationKey().replace("block.minecraft.", "")+"_camo_door"), doorCopyBlockMap.get(block));
			Registry.register(Registry.ITEM, new Identifier(MOD_ID, block.getTranslationKey().replace("block.minecraft.", "")+"_camo_door"), new BlockItem(doorCopyBlockMap.get(block), new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		}
	}

	private static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel) {
		return (blockState) -> {
			return !(Boolean)blockState.get(Properties.POWERED) ? litLevel : 0;
		};
		}
				
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "camo_paste"), CAMO_PASTE);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID , "torch_lever"), TORCH_LEVER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "torch_lever"), new BlockItem(TORCH_LEVER_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID , "soul_torch_lever"), SOUL_TORCH_LEVER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "soul_torch_lever"), new BlockItem(SOUL_TORCH_LEVER_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID , "solid_air"), SOLID_AIR_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "solid_air"), new BlockItem(SOLID_AIR_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID , "lantern_button"), LANTERN_BUTTON_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lantern_button"), new BlockItem(LANTERN_BUTTON_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID , "soul_lantern_button"), SOUL_LANTERN_BUTTON_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "soul_lantern_button"), new BlockItem(SOUL_LANTERN_BUTTON_BLOCK, new Item.Settings().group(SecretRooms.MAIN_GROUP)));

		copyBlockList.add(Blocks.OAK_PLANKS);
		copyBlockList.add(Blocks.ACACIA_PLANKS);
		copyBlockList.add(Blocks.DARK_OAK_PLANKS);
		copyBlockList.add(Blocks.JUNGLE_PLANKS);
		copyBlockList.add(Blocks.BIRCH_PLANKS);
		copyBlockList.add(Blocks.SPRUCE_PLANKS);
		copyBlockList.add(Blocks.CRIMSON_PLANKS);
		copyBlockList.add(Blocks.WARPED_PLANKS);
		copyBlockList.add(Blocks.DIRT);
		copyBlockList.add(Blocks.STONE);
		copyBlockList.add(Blocks.SMOOTH_STONE);
		copyBlockList.add(Blocks.STONE_BRICKS);
		copyBlockList.add(Blocks.CRACKED_STONE_BRICKS);
		copyBlockList.add(Blocks.COBBLESTONE);
		copyBlockList.add(Blocks.SAND);
		copyBlockList.add(Blocks.GRAVEL);
		copyBlockList.add(Blocks.CLAY);
		copyBlockList.add(Blocks.BLACKSTONE);
		copyBlockList.add(Blocks.POLISHED_BLACKSTONE);
		copyBlockList.add(Blocks.GILDED_BLACKSTONE);
		copyBlockList.add(Blocks.POLISHED_BLACKSTONE_BRICKS);
		copyBlockList.add(Blocks.NETHERRACK);
		copyBlockList.add(Blocks.END_STONE);
		copyBlockList.add(Blocks.PURPUR_BLOCK);
		copyBlockList.add(Blocks.PURPUR_PILLAR);
		copyBlockList.add(Blocks.ANDESITE);
		copyBlockList.add(Blocks.POLISHED_ANDESITE);
		copyBlockList.add(Blocks.DIORITE);
		copyBlockList.add(Blocks.POLISHED_DIORITE);
		copyBlockList.add(Blocks.GRANITE);
		copyBlockList.add(Blocks.POLISHED_GRANITE);
		copyBlockList.add(Blocks.BRICKS);
		copyBlockList.add(Blocks.NETHER_BRICKS);

		registerOneWayGlassBlocks();
		registerCamoDoorBlocks();
	}
}	

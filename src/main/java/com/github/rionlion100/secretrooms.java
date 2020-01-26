package com.github.rionlion100;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class secretrooms implements ModInitializer {
	public static final Item camo_paste = new Item(new Item.Settings().group(secretrooms.MAIN_GROUP));
	public static final TorchLeverBlock torch_lever = new TorchLeverBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).lightLevel(0).build());
	public static final SolidAirBlock solid_air = new SolidAirBlock(FabricBlockSettings.of(Material.AIR).hardness(.45f).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_oak = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_spruce = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_jungle = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_acacia = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_dark_oak = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_birch = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_cobble = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_stone = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass_dirt = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).hardness(.45f).sounds(BlockSoundGroup.GLASS).nonOpaque().build());

	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(
		new Identifier("secretrooms", "general"))
		.icon(() -> new ItemStack(secretrooms.camo_paste))
		.appendItems(stacks ->
		{
			stacks.add(new ItemStack(secretrooms.camo_paste));
			stacks.add(new ItemStack(secretrooms.torch_lever));
			stacks.add(new ItemStack(secretrooms.solid_air));
			stacks.add(new ItemStack(secretrooms.one_way_glass_oak));
			stacks.add(new ItemStack(secretrooms.one_way_glass_birch));
			stacks.add(new ItemStack(secretrooms.one_way_glass_spruce));
			stacks.add(new ItemStack(secretrooms.one_way_glass_dark_oak));
			stacks.add(new ItemStack(secretrooms.one_way_glass_acacia));
			stacks.add(new ItemStack(secretrooms.one_way_glass_jungle));
			stacks.add(new ItemStack(secretrooms.one_way_glass_cobble));
			stacks.add(new ItemStack(secretrooms.one_way_glass_stone));
			stacks.add(new ItemStack(secretrooms.one_way_glass_dirt));
					}).build();

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "camo_paste"), camo_paste);
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "torch_lever"), torch_lever);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "torch_lever"), new BlockItem(torch_lever, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "solid_air"), solid_air);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "solid_air"), new BlockItem(solid_air, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_oak"), one_way_glass_oak);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_oak"), new BlockItem(one_way_glass_oak, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_birch"), one_way_glass_birch);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_birch"), new BlockItem(one_way_glass_birch, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_spruce"), one_way_glass_spruce);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_spruce"), new BlockItem(one_way_glass_spruce, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_dark_oak"), one_way_glass_dark_oak);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_dark_oak"), new BlockItem(one_way_glass_dark_oak, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_acacia"), one_way_glass_acacia);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_acacia"), new BlockItem(one_way_glass_acacia, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_jungle"), one_way_glass_jungle);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_jungle"), new BlockItem(one_way_glass_jungle, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_cobble"), one_way_glass_cobble);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_cobble"), new BlockItem(one_way_glass_cobble, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_stone"), one_way_glass_stone);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_stone"), new BlockItem(one_way_glass_stone, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass_dirt"), one_way_glass_dirt);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass_dirt"), new BlockItem(one_way_glass_dirt, new Item.Settings().group(secretrooms.MAIN_GROUP)));
	}
}

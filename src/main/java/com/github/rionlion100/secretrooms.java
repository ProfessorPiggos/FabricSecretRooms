package com.github.rionlion100;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class secretrooms implements ModInitializer {
	public static final Item camo_paste = new Item(new Item.Settings().group(secretrooms.MAIN_GROUP));
	public static final TorchLeverBlock torch_lever = new TorchLeverBlock(FabricBlockSettings.of(Material.WOOD).lightLevel(0).build());
	public static final SolidAirBlock solid_air = new SolidAirBlock(FabricBlockSettings.of(Material.AIR).hardness(.45f).nonOpaque().build());
	public static final OneWayGlassBlock one_way_glass = new OneWayGlassBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().build());

	public static final ItemGroup MAIN_GROUP = FabricItemGroupBuilder.create(
		new Identifier("secretrooms", "general"))
		.icon(() -> new ItemStack(secretrooms.camo_paste))
		.appendItems(stacks ->
		{
			stacks.add(new ItemStack(secretrooms.camo_paste));
			stacks.add(new ItemStack(secretrooms.torch_lever));
			stacks.add(new ItemStack(secretrooms.solid_air));
			stacks.add(new ItemStack(secretrooms.one_way_glass));
		}).build();

	@Override
	public void onInitialize() {
		BlockRenderLayerMap.INSTANCE.putBlock(torch_lever, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(solid_air, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(one_way_glass, RenderLayer.getCutoutMipped());
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "camo_paste"), camo_paste);
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "torch_lever"), torch_lever);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "torch_lever"), new BlockItem(torch_lever, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "solid_air"), solid_air);
		Registry.register(Registry.ITEM, new Identifier("secretrooms", "solid_air"), new BlockItem(solid_air, new Item.Settings().group(secretrooms.MAIN_GROUP)));
		Registry.register(Registry.BLOCK, new Identifier("secretrooms" , "one_way_glass"), one_way_glass);
        Registry.register(Registry.ITEM, new Identifier("secretrooms", "one_way_glass"), new BlockItem(one_way_glass, new Item.Settings().group(secretrooms.MAIN_GROUP)));
	}
}

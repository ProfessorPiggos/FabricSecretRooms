package com.github.rionlion100;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import com.github.rionlion100.secretrooms;

public class secretroomsclient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.torch_lever, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.solid_air, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_oak, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_birch, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_spruce, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_dark_oak, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_acacia, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_jungle, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_cobble, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_stone, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(secretrooms.one_way_glass_dirt, RenderLayer.getCutoutMipped());
	}
}
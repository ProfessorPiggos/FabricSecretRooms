package com.github.rionlion100;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class SecretRoomsClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(SecretRooms.TORCH_LEVER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SecretRooms.SOUL_TORCH_LEVER_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SecretRooms.SOLID_AIR_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SecretRooms.LANTERN_BUTTON_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SecretRooms.SOUL_LANTERN_BUTTON_BLOCK, RenderLayer.getCutout());
		for (int i = 0; i < SecretRooms.ONE_WAY_GLASS_SOURCE_BLOCKS.size(); i++){
			BlockRenderLayerMap.INSTANCE.putBlock(SecretRooms.ONE_WAY_GLASS_MAP.get(SecretRooms.ONE_WAY_GLASS_SOURCE_BLOCKS.get(i)), RenderLayer.getCutout());
		}
	}
}

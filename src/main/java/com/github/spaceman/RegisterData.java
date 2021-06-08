package com.github.spaceman;

import com.swordglowsblue.artifice.api.Artifice;

import net.minecraft.util.Identifier;

public class RegisterData {
    public static void register() {
        Artifice.registerDataPack(new Identifier(SecretRooms.MOD_ID,"server_pack"), pack -> {
            pack.setDisplayName("Secret Rooms Data");
            pack.setDescription("Secret Rooms Data Generator");
            for (int i = 0; i < SecretRooms.copyBlockList.size(); i++){
				String glassTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_glass"; 
                String camoDoorTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_camo_door"; 
                String ghostBlockTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_ghost_block"; 
                String camoTrapdoorTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_camo_trapdoor";
                String baseTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "");
                pack.addShapelessRecipe(new Identifier(SecretRooms.MOD_ID,glassTranslationKey), recipe -> recipe
                    .ingredientItem(new Identifier("minecraft",baseTranslationKey))
                    .ingredientItem(new Identifier("minecraft","glass"))
                    .ingredientItem(new Identifier(SecretRooms.MOD_ID,"camo_paste"))
                    .result(new Identifier(SecretRooms.MOD_ID, glassTranslationKey), 1)
                );
                pack.addShapelessRecipe(new Identifier(SecretRooms.MOD_ID,camoDoorTranslationKey), recipe -> recipe
                    .ingredientItem(new Identifier("minecraft",baseTranslationKey))
                    .multiIngredient(list -> list
                        .item(new Identifier("minecraft","oak_door"))
                        .item(new Identifier("minecraft","birch_door"))
                        .item(new Identifier("minecraft","spruce_door"))
                        .item(new Identifier("minecraft","jungle_door"))
                        .item(new Identifier("minecraft","acacia_door"))
                        .item(new Identifier("minecraft","dark_oak_door"))
                        .item(new Identifier("minecraft","crimson_door"))
                        .item(new Identifier("minecraft","warped_door"))
                    )
                    .ingredientItem(new Identifier(SecretRooms.MOD_ID,"camo_paste"))
                    .result(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey), 1)
                );
                pack.addShapelessRecipe(new Identifier(SecretRooms.MOD_ID,camoTrapdoorTranslationKey), recipe -> recipe
                    .ingredientItem(new Identifier("minecraft",baseTranslationKey))
                    .multiIngredient(list -> list
                        .item(new Identifier("minecraft","oak_trapdoor"))
                        .item(new Identifier("minecraft","birch_trapdoor"))
                        .item(new Identifier("minecraft","spruce_trapdoor"))
                        .item(new Identifier("minecraft","jungle_trapdoor"))
                        .item(new Identifier("minecraft","acacia_trapdoor"))
                        .item(new Identifier("minecraft","dark_oak_trapdoor"))
                        .item(new Identifier("minecraft","crimson_trapdoor"))
                        .item(new Identifier("minecraft","warped_trapdoor"))
                    )
                    .ingredientItem(new Identifier(SecretRooms.MOD_ID,"camo_paste"))
                    .result(new Identifier(SecretRooms.MOD_ID, camoTrapdoorTranslationKey), 1)
                );
                pack.addShapelessRecipe(new Identifier(SecretRooms.MOD_ID,ghostBlockTranslationKey), recipe -> recipe
                    .ingredientItem(new Identifier("minecraft",baseTranslationKey))
                    .ingredientItem(new Identifier("minecraft","scaffolding"))
                    .ingredientItem(new Identifier(SecretRooms.MOD_ID,"camo_paste"))
                    .result(new Identifier(SecretRooms.MOD_ID, ghostBlockTranslationKey), 1)
                );
                pack.addLootTable(new Identifier(SecretRooms.MOD_ID, "blocks/"+ghostBlockTranslationKey), table -> table
                    .type(new Identifier("minecraft","block"))
                    .pool(pool -> pool
                        .rolls(1)
                        .entry(entry -> entry
                            .type(new Identifier("minecraft","item"))
                            .name(new Identifier(SecretRooms.MOD_ID,ghostBlockTranslationKey))
                        )
                        .condition(new Identifier("minecraft","survives_explosion"), condition -> condition
                            .build()
                        )
                    )
                );
                pack.addLootTable(new Identifier(SecretRooms.MOD_ID, "blocks/"+camoTrapdoorTranslationKey), table -> table
                    .type(new Identifier("minecraft","block"))
                    .pool(pool -> pool
                        .rolls(1)
                        .entry(entry -> entry
                            .type(new Identifier("minecraft","item"))
                            .name(new Identifier(SecretRooms.MOD_ID,camoTrapdoorTranslationKey))
                        )
                        .condition(new Identifier("minecraft","survives_explosion"), condition -> condition
                            .build()
                        )
                    )
                );
            }
        });
    }
}
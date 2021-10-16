package com.github.spaceman;

import com.swordglowsblue.artifice.api.Artifice;

import net.minecraft.util.Identifier;

public class RegisterAssets {
    public static void register() {
        Artifice.registerAssetPack(new Identifier(SecretRooms.MOD_ID, "client_pack"), pack -> {
            //One way glass blockstate
            for (int i = 0; i < SecretRooms.copyBlockList.size(); i++){
                String glassTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_glass"; 
                String camoDoorTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_camo_door"; 
                String ghostBlockTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_ghost_block"; 
                String camoTrapdoorTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "")+"_camo_trapdoor";
                String textureTranslationKey = SecretRooms.copyBlockList.get(i).getTranslationKey().replaceAll("block\\.minecraft\\.", "");

                pack.addBlockState(new Identifier(SecretRooms.MOD_ID, glassTranslationKey), state -> state
                    .variant("facing=up", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                        .rotationX(270)
                        .uvlock(true)
                    )
                    .variant("facing=down", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                        .rotationX(90)
                        .uvlock(true)
                    )
                    .variant("facing=north", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                        .uvlock(true)
                    )
                    .variant("facing=east", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=south", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=west", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                        .rotationY(270)
                        .uvlock(true)
                    )
                );
                //Camo door blockstate
                pack.addBlockState(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey), state -> state
                    .variant("facing=east,half=lower,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .uvlock(true)
                    )
                    .variant("facing=east,half=lower,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=lower,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .uvlock(true)
                    )
                    .variant("facing=east,half=lower,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=upper,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .uvlock(true)
                    )
                    .variant("facing=east,half=upper,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=upper,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .uvlock(true)
                    )
                    .variant("facing=east,half=upper,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=lower,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=lower,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .uvlock(true)
                    )
                    .variant("facing=north,half=lower,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=lower,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=upper,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=upper,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .uvlock(true)
                    )
                    .variant("facing=north,half=upper,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=upper,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=lower,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=lower,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=lower,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=lower,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .uvlock(true)
                    )
                    .variant("facing=south,half=upper,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=upper,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=upper,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=upper,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .uvlock(true)
                    )
                    .variant("facing=west,half=lower,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=lower,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=lower,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom_hinge"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=lower,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_bottom"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=upper,hinge=left,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=upper,hinge=left,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=upper,hinge=right,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top_hinge"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=upper,hinge=right,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoDoorTranslationKey+"_top"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                );
                //Camo trapdoor blockstate
                pack.addBlockState(new Identifier(SecretRooms.MOD_ID, camoTrapdoorTranslationKey), state -> state
                    .variant("facing=north,half=bottom,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_bottom"))
                        .uvlock(true)
                    )
                    .variant("facing=south,half=bottom,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_bottom"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=bottom,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_bottom"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=bottom,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_bottom"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=top,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_top"))
                        .uvlock(true)
                    )
                    .variant("facing=south,half=top,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_top"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=top,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_top"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=top,open=false", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_top"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=bottom,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .uvlock(true)
                    )
                    .variant("facing=south,half=bottom,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=bottom,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationY(90)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=bottom,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=north,half=top,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationX(180)
                        .rotationY(180)
                        .uvlock(true)
                    )
                    .variant("facing=south,half=top,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationX(180)
                        .rotationY(0)
                        .uvlock(true)
                    )
                    .variant("facing=east,half=top,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationX(180)
                        .rotationY(270)
                        .uvlock(true)
                    )
                    .variant("facing=west,half=top,open=true", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_open"))
                        .rotationX(180)
                        .rotationY(90)
                        .uvlock(true)
                    )
                );
                //Ghost block blockstate 
                pack.addBlockState(new Identifier(SecretRooms.MOD_ID, ghostBlockTranslationKey), state -> state
                    .variant("", variant -> variant
                        .model(new Identifier(SecretRooms.MOD_ID, "block/"+ghostBlockTranslationKey))
                    )
                );
                //One way glass block model 
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, glassTranslationKey), model -> model
                    .parent(new Identifier("block/cube"))
                    .texture("particle", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("south", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("north", new Identifier("minecraft:block/glass"))
                    .texture("east", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("down", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("up", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("west", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                //Camo door block model
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey+"_bottom"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/door_bottom"))
                    .texture("top", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("bottom", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey+"_bottom_hinge"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/door_bottom_hinge"))
                    .texture("top", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("bottom", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey+"_top"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/door_top"))
                    .texture("top", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("bottom", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey+"_top_hinge"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/door_top_hinge"))
                    .texture("top", new Identifier("minecraft:block/"+textureTranslationKey))
                    .texture("bottom", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                //camo trapdoor block model
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoTrapdoorTranslationKey+"_bottom"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/template_orientable_trapdoor_bottom"))
                    .texture("texture", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoTrapdoorTranslationKey+"_top"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/template_orientable_trapdoor_top"))
                    .texture("texture", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, camoTrapdoorTranslationKey+"_open"), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/template_orientable_trapdoor_open"))
                    .texture("texture", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                //Ghost block block model
                pack.addBlockModel(new Identifier(SecretRooms.MOD_ID, ghostBlockTranslationKey), model -> model
                    .parent(new Identifier("block/cube_all"))
                    .texture("all", new Identifier("minecraft:block/"+textureTranslationKey))
                );
                //Glass item model
                pack.addItemModel(new Identifier(SecretRooms.MOD_ID, glassTranslationKey), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/"+glassTranslationKey))
                );
                //Camo door item model
                pack.addItemModel(new Identifier(SecretRooms.MOD_ID, camoDoorTranslationKey), model -> model
                    .parent(new Identifier("minecraft:item/generated"))
                    .texture("layer0", new Identifier(SecretRooms.MOD_ID, "item/"+camoDoorTranslationKey))
                );
                //camo trapdoor item model
                pack.addItemModel(new Identifier(SecretRooms.MOD_ID, camoTrapdoorTranslationKey), model -> model
                    .parent(new Identifier(SecretRooms.MOD_ID, "block/"+camoTrapdoorTranslationKey+"_bottom"))
                );
                //Ghost block item model
                pack.addItemModel(new Identifier(SecretRooms.MOD_ID, ghostBlockTranslationKey), model -> model
                    .parent(new Identifier("minecraft:item/generated"))
                    .texture("layer0", new Identifier(SecretRooms.MOD_ID, "item/"+ghostBlockTranslationKey))
                );       
                //lang file 
                pack.addTranslations(new Identifier(SecretRooms.MOD_ID+"_"+textureTranslationKey, "en_us"), state -> state 
                    .entry("block."+SecretRooms.MOD_ID+"."+glassTranslationKey, stringClean(glassTranslationKey))
                    .entry("block."+SecretRooms.MOD_ID+"."+camoDoorTranslationKey, stringClean(camoDoorTranslationKey))
                    .entry("block."+SecretRooms.MOD_ID+"."+camoTrapdoorTranslationKey, stringClean(camoTrapdoorTranslationKey))  
                    .entry("block."+SecretRooms.MOD_ID+"."+ghostBlockTranslationKey, stringClean(ghostBlockTranslationKey))  
                );     
            }
        });
    }
    static String stringClean(String input){
        String output = "";
        for (int i = 0; i < input.split("_").length; i++){
            output+=(Character.toUpperCase(input.split("_")[i].charAt(0))+input.split("_")[i].substring(1));
            if (i != input.split("_").length - 1){
                output+=" ";
            }
        }
        return output;
    }
}
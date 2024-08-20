package net.krishd.tutorialmod.util;

import net.krishd.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> RADIOACTIVE_BLOCKS = tag("radioactive_blocks");

        public static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TutorialMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TutorialMod.MOD_ID, name));
        }
    }
}

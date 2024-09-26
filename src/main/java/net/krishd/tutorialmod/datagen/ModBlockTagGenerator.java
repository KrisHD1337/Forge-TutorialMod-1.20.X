package net.krishd.tutorialmod.datagen;

import net.krishd.tutorialmod.TutorialMod;
import net.krishd.tutorialmod.block.ModBlocks;
import net.krishd.tutorialmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.RADIOACTIVE_BLOCKS)
                .add(ModBlocks.URANIUM_ORE.get()).addTag(Tags.Blocks.ORES)
                .add(ModBlocks.NUCLEAR_WASTE_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.URANIUM_BLOCK.get(),
                        ModBlocks.RAW_URANIUM_BLOCK.get(),
                        ModBlocks.URANIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                        ModBlocks.NETHER_URANIUM_ORE.get(),
                        ModBlocks.NUCLEAR_WASTE_BLOCK.get()
                );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.END_URANIUM_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.URANIUM_BLOCK.get(),
                        ModBlocks.RAW_URANIUM_BLOCK.get(),
                        ModBlocks.URANIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                        ModBlocks.NETHER_URANIUM_ORE.get(),
                        ModBlocks.END_URANIUM_ORE.get(),
                        ModBlocks.NUCLEAR_WASTE_BLOCK.get()
                );
    }
}

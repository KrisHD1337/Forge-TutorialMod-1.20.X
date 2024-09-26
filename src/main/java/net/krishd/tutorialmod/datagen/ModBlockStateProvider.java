package net.krishd.tutorialmod.datagen;

import net.krishd.tutorialmod.TutorialMod;
import net.krishd.tutorialmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.URANIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_URANIUM_BLOCK);

        blockWithItem(ModBlocks.URANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_URANIUM_ORE);
        blockWithItem(ModBlocks.NETHER_URANIUM_ORE);
        blockWithItem(ModBlocks.END_URANIUM_ORE);

        blockWithItem(ModBlocks.NUCLEAR_WASTE_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

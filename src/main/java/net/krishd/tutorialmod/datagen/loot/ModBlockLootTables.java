package net.krishd.tutorialmod.datagen.loot;

import net.krishd.tutorialmod.block.ModBlocks;
import net.krishd.tutorialmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.URANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_URANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.NUCLEAR_WASTE_BLOCK.get());

        this.add(ModBlocks.URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
        this.add(ModBlocks.NETHER_URANIUM_ORE.get(),
                block -> createEndUraniumOreDrops(ModBlocks.NETHER_URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
        this.add(ModBlocks.END_URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.END_URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
    }

    protected LootTable.Builder createEndUraniumOreDrops(Block pBlock, Item item) {
      return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
              LootItem.lootTableItem(item)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F)))
                      .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
   }

   protected LootTable.Builder createNetherUraniumOreDrops(Block pBlock, Item item) {
      return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
              LootItem.lootTableItem(item)
                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                      .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
   }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

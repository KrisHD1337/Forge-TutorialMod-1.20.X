package net.krishd.tutorialmod.datagen;

import net.krishd.tutorialmod.TutorialMod;
import net.krishd.tutorialmod.block.ModBlocks;
import net.krishd.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> URANIUM_SMELTABLES = List.of(ModItems.RAW_URANIUM.get(),
            ModBlocks.URANIUM_BLOCK.get(), ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModBlocks.NETHER_URANIUM_ORE.get(),
            ModBlocks.END_URANIUM_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        oreBlasting(pRecipeOutput, URANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.URANIUM_INGOT.get(), 0.25f, 100, "uranium_ingot");
        oreSmelting(pRecipeOutput, URANIUM_SMELTABLES, RecipeCategory.MISC, ModItems.URANIUM_INGOT.get(), 0.25f, 200, "uranium_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.URANIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.URANIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.URANIUM_INGOT.get()), has(ModItems.URANIUM_INGOT.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.URANIUM_INGOT.get())
                .requires(ModBlocks.URANIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.URANIUM_BLOCK.get()), has(ModBlocks.URANIUM_BLOCK.get()))
                .save(pRecipeOutput);
    }
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
      oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
   }

   protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
      oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
   }

   private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {
      for(ItemLike itemlike : pIngredients) {
         SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                 pExperience, pCookingTime, pSerializer, pRecipeFactory)
                 .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                 .save(pRecipeOutput, TutorialMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
      }

   }
}

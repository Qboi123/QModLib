package com.qsoftware.modlib.silentlib.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.qsoftware.modlib.QModLib;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class TestRecipeProvider extends RecipeProvider {
    public TestRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        QModLib.LOGGER.warn("Running test recipe provider! These files should NOT be included in release!");

        DamageItemRecipeBuilder.builder(Items.DIAMOND, 9)
                .damageToItems(3)
                .addIngredient(Items.DIAMOND_PICKAXE)
                .addIngredient(Blocks.DIAMOND_BLOCK)
                .addCriterion("has_item", hasItem(Blocks.DIAMOND_BLOCK))
                .build(consumer, QModLib.getId("damage_item_test1"));
        DamageItemRecipeBuilder.builder(Items.EMERALD, 9)
                .damageToItems(3)
                .addExtraData(json -> json.addProperty("test", "This is a test!"))
                .addIngredient(Items.DIAMOND_PICKAXE)
                .addIngredient(Blocks.EMERALD_BLOCK)
                .build(consumer, QModLib.getId("damage_item_test2"));
        ExtendedShapelessRecipeBuilder.vanillaBuilder(Blocks.DIRT, 10)
                .addIngredient(Tags.Items.GEMS_EMERALD)
                .addExtraData(json -> json.addProperty("test2", "Can you hear me now?"))
                .build(consumer, QModLib.getId("extended_shapeless_test1"));

        ExtendedShapedRecipeBuilder.vanillaBuilder(Items.DIAMOND_SWORD)
                .patternLine("  #")
                .patternLine(" # ")
                .patternLine("/  ")
                .key('#', Tags.Items.GEMS_DIAMOND)
                .key('/', Tags.Items.RODS_WOODEN)
                .addExtraData(json -> addLore(json, "Diagonal sword!", "<3 data generators"))
                .build(consumer, QModLib.getId("extended_shaped_test1"));
    }

    private void addLore(JsonObject json, String... lore) {
        JsonObject result = JSONUtils.getJsonObject(json, "result");
        JsonObject display = new JsonObject();
        JsonObject nbt = new JsonObject();
        JsonArray array = new JsonArray();
        for (String line : lore) {
            array.add("\"" + line + "\"");
        }
        display.add("Lore", array);
        nbt.add("display", display);
        result.add("nbt", nbt);
    }
}

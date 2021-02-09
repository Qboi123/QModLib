package com.qsoftware.modlib.utils.builder;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Item tier builder.
 *
 * @author Qboi123
 */
public class ItemTierBuilder {
    private int harvestLevel = 0;
    private int maxUses = -1;
    private float efficiency;
    private float attackDamage;
    private int enchantability;
    private Ingredient repairMaterial;

    public ItemTierBuilder() {
        
    }
    
    public ItemTierBuilder harvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;
        return this;
    }

    public ItemTierBuilder maxUses(int maxUses) {
        this.maxUses = maxUses;
        return this;
    }

    public ItemTierBuilder efficiency(float efficiency) {
        this.efficiency = efficiency;
        return this;
    }

    public ItemTierBuilder attackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    public ItemTierBuilder enchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ItemTierBuilder repairMaterial(ITag<Item> tag) {
        this.repairMaterial = Ingredient.fromTag(tag);
        return this;
    }

    public ItemTierBuilder repairMaterial(ItemStack... stacks) {
        this.repairMaterial = Ingredient.fromStacks(stacks);
        return this;
    }

    public ItemTierBuilder repairMaterial(Stream<ItemStack> stacks) {
        this.repairMaterial = Ingredient.fromStacks(stacks);
        return this;
    }

    public ItemTierBuilder repairMaterial(Collection<ItemStack> stacks) {
        this.repairMaterial = Ingredient.fromStacks(stacks.stream());
        return this;
    }

    public ItemTierBuilder repairMaterial(Iterable<ItemStack> stacks) {
        List<ItemStack> itemStackList = new ArrayList<>();
        for (ItemStack stack : stacks) {
            itemStackList.add(stack);
        }
        return repairMaterial(itemStackList);
    }

    public ItemTierBuilder repairMaterial(IItemProvider... items) {
        this.repairMaterial = Ingredient.fromItems(items);
        return this;
    }

    public ItemTierBuilder repairMaterial(Ingredient ingredient) {
        this.repairMaterial = ingredient;
        return this;
    }

    public IItemTier build() {
        return new IItemTier() {
            @Override
            public int getMaxUses() {
                return maxUses;
            }

            @Override
            public float getEfficiency() {
                return efficiency;
            }

            @Override
            public float getAttackDamage() {
                return attackDamage;
            }

            @Override
            public int getHarvestLevel() {
                return harvestLevel;
            }

            @Override
            public int getEnchantability() {
                return enchantability;
            }

            @Override
            public Ingredient getRepairMaterial() {
                return repairMaterial;
            }
        };
    }
}

package com.qsoftware.modlib.utils.builder;

import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@Deprecated
@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemTierBuilderOld implements IItemTier {
    protected int tier;
    protected int maxUses;
    protected float efficiency;
    protected float attackDamage;
    protected int enchantability;
    protected LazyValue<Ingredient> repairMaterial;

    public ItemTierBuilderOld(int tier, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.tier = tier;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

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
        return tier;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }
}

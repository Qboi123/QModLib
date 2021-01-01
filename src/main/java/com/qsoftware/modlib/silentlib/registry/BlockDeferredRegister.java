package com.qsoftware.modlib.silentlib.registry;

import com.qsoftware.modlib.api.providers.IBlockProvider;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlockDeferredRegister extends DeferredRegisterWrapper<Block> {

    private final List<IBlockProvider> allBlocks = new ArrayList<>();

    public BlockDeferredRegister(String modid) {
        super(modid, ForgeRegistries.BLOCKS);
    }

    public <BLOCK extends Block> BlockRegistryObject<BLOCK> register(String name, Supplier<? extends BLOCK> sup) {
        BlockRegistryObject<BLOCK> registeredBlock = register(name, sup, BlockRegistryObject::new);
        allBlocks.add((IBlockProvider) registeredBlock);
        return registeredBlock;
    }

    public List<IBlockProvider> getAllBlocks() {
        return allBlocks;
    }
}
package com.qsoftware.modlib.silentlib.registry;

import com.qsoftware.modlib.api.providers.IBlockProvider;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public class BlockRegistryObject<T extends Block> extends RegistryObjectWrapper<T> implements IBlockProvider {
    public BlockRegistryObject(RegistryObject<T> block) {
        super(block);
    }

    @Override
    public Block asBlock() {
        return registryObject.get();
    }
}

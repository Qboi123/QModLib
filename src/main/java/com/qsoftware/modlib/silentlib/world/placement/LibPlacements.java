package com.qsoftware.modlib.silentlib.world.placement;

import com.qsoftware.modlib.QModLib;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = QModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LibPlacements {
    public static final Placement<DimensionFilterConfig> DIMENSION_FILTER = new DimensionFilterPlacement(DimensionFilterConfig.CODEC);

    private LibPlacements() {
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Placement<?>> event) {
        event.getRegistry().register(DIMENSION_FILTER.setRegistryName(QModLib.getId("dimension_filter")));
    }
}

package com.qsoftware.modlib;

import com.mojang.brigadier.CommandDispatcher;
import com.qsoftware.modlib.api.annotations.FieldsAreNonnullByDefault;
import com.qsoftware.modlib.silentlib.advancements.LibTriggers;
import com.qsoftware.modlib.silentlib.command.internal.DisplayNBTCommand;
import com.qsoftware.modlib.silentlib.command.internal.TeleportCommand;
import com.qsoftware.modlib.silentlib.crafting.recipe.DamageItemRecipe;
import com.qsoftware.modlib.silentlib.data.TestRecipeProvider;
import com.qsoftware.modlib.silentlib.item.ILeftClickItem;
import com.qsoftware.modlib.silentlib.network.internal.QModLibNetwork;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandSource;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.ParametersAreNonnullByDefault;

@FieldsAreNonnullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class SideProxy {
    SideProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::imcEnqueue);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::imcProcess);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(IRecipeSerializer.class, this::registerRecipeSerializers);

        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);

        QModLibNetwork.init();
        LibTriggers.init();
        ILeftClickItem.EventHandler.init();
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        gen.addProvider(new TestRecipeProvider(gen));
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void imcEnqueue(InterModEnqueueEvent event) {
    }

    private void imcProcess(InterModProcessEvent event) {
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        DisplayNBTCommand.register(dispatcher);
        TeleportCommand.register(dispatcher);
    }

    private void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().register(DamageItemRecipe.SERIALIZER.setRegistryName(QModLib.getId("damage_item")));
    }

    static class Client extends SideProxy {
        Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        }

        private void clientSetup(FMLClientSetupEvent event) {
        }
    }

    static class Server extends SideProxy {
        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        }

        private void serverSetup(FMLDedicatedServerSetupEvent event) {
        }
    }
}

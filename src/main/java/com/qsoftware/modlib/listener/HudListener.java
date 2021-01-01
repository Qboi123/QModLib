package com.qsoftware.modlib.listener;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qsoftware.modlib.QModLib;
import com.qsoftware.modlib.common.IRenderable;
import com.qsoftware.modlib.graphics.MCGraphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = QModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HudListener {
    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
            return;
        }

        MatrixStack matrixStack = event.getMatrixStack();

        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player != null) {
            ItemStack heldItem = player.getHeldItem(Hand.MAIN_HAND);
            Item item = heldItem.getItem();
            if (item instanceof IRenderable) {
                ((IRenderable) item).render(new MCGraphics(matrixStack, mc.fontRenderer));
            }
        }
    }
}

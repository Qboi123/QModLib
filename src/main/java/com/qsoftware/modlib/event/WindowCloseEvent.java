package com.qsoftware.modlib.event;

import com.qsoftware.modlib.QModLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.List;

@Cancelable
@Mod.EventBusSubscriber(modid = QModLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class WindowCloseEvent extends Event {
    private static boolean initialized;
    private final Source source;

    public WindowCloseEvent(Source source) {
        this.source = source;
    }

    public Source getSource() {
        return source;
    }

    public enum Source {
        QUIT_BUTTON,
        GENERIC
    }

    @SubscribeEvent
    public static void onOptionsScreenInit(GuiScreenEvent.InitGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Screen gui = event.getGui();
        if (gui instanceof MainMenuScreen) {
            if (!initialized) {
                long handle = mc.getMainWindow().getHandle();
                GLFW.glfwSetWindowCloseCallback(handle, window -> {
                    boolean flag = MinecraftForge.EVENT_BUS.post(new WindowCloseEvent(Source.GENERIC));
                    if (flag) {
                        GLFW.glfwSetWindowShouldClose(window, false);
                    }
                });
            }
            MainMenuScreen mainMenu = (MainMenuScreen) gui;
            List<Widget> buttons = mainMenu.buttons;
            Button widget = (Button) buttons.get(buttons.size() - 2);
            widget.onPress = (button) -> {
                boolean flag = MinecraftForge.EVENT_BUS.post(new WindowCloseEvent(Source.QUIT_BUTTON));
                if (!flag) {
                    mc.close();
                }
            };
            initialized = true;
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}

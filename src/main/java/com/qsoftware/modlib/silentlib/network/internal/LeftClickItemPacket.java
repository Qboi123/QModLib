package com.qsoftware.modlib.silentlib.network.internal;

import com.qsoftware.modlib.QModLib;
import com.qsoftware.modlib.silentlib.item.ILeftClickItem;
import com.qsoftware.modlib.silentutils.EnumUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftClickItemPacket {
    private ILeftClickItem.ClickType clickType;
    private Hand hand;

    public LeftClickItemPacket() {
        this.clickType = ILeftClickItem.ClickType.EMPTY;
        this.hand = Hand.MAIN_HAND;
    }

    public LeftClickItemPacket(ILeftClickItem.ClickType clickType, Hand hand) {
        this.clickType = clickType;
        this.hand = hand;
    }

    public ILeftClickItem.ClickType getClickType() {
        return clickType;
    }

    public Hand getHand() {
        return hand;
    }

    public static LeftClickItemPacket fromBytes(PacketBuffer buffer) {
        LeftClickItemPacket packet = new LeftClickItemPacket();
        packet.clickType = EnumUtils.byOrdinal(buffer.readByte(), ILeftClickItem.ClickType.EMPTY);
        packet.hand = buffer.readBoolean() ? Hand.MAIN_HAND : Hand.OFF_HAND;
        return packet;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeByte(this.clickType.ordinal());
        buffer.writeBoolean(this.hand == Hand.MAIN_HAND);
    }

    public static void handle(LeftClickItemPacket packet, Supplier<NetworkEvent.Context> context) {
        ServerPlayerEntity player = context.get().getSender();

        if (player != null) {
            ItemStack heldItem = player.getHeldItem(packet.hand);

            if (!heldItem.isEmpty() && heldItem.getItem() instanceof ILeftClickItem) {
                ILeftClickItem item = (ILeftClickItem) heldItem.getItem();

                if (packet.clickType == ILeftClickItem.ClickType.EMPTY) {
                    item.onItemLeftClickSL(player.world, player, packet.hand);
                } else if (packet.clickType == ILeftClickItem.ClickType.BLOCK) {
                    item.onItemLeftClickBlockSL(player.world, player, packet.hand);
                } else {
                    QModLib.LOGGER.error("Unknown ILeftClickItem.ClickType: {}", packet.clickType);
                }
            }
        }

        context.get().setPacketHandled(true);
    }
}

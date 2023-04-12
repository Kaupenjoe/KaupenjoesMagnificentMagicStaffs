package net.kaupenjoe.magnificentstaffs.item;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StaffsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab STAFF_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        STAFF_TAB = event.registerCreativeModeTab(new ResourceLocation(StaffsMod.MOD_ID, "staffs_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.AMETHYST_STAFF.get()))
                        .title(Component.translatable("creativemodetab.staffs_tab")));
    }
}
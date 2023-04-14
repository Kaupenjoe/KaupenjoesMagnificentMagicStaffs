package net.kaupenjoe.magnificentstaffs.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModCreativeModeTab {
    public static ItemGroup STAFFS;

    public static void registerItemGroups() {
        STAFFS = FabricItemGroup.builder(new Identifier(StaffsMod.MOD_ID, "staffs"))
                .displayName(Text.translatable("itemgroup.staffs"))
                .icon(() -> new ItemStack(ModItems.AMETHYST_STAFF)).build();
    }
}
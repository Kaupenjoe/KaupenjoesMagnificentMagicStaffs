package net.kaupenjoe.magnificentstaffs.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.item.custom.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item AMETHYST_STAFF = registerItem("amethyst_staff", new AmethystStaff(new FabricItemSettings().maxCount(1)));
    public static final Item DIAMOND_STAFF = registerItem("diamond_staff", new DiamondStaff(new FabricItemSettings().maxCount(1)));
    public static final Item EMERALD_STAFF = registerItem("emerald_staff", new EmeraldStaff(new FabricItemSettings().maxCount(1)));
    public static final Item RUBY_STAFF = registerItem("ruby_staff", new RubyStaff(new FabricItemSettings().maxCount(1)));
    public static final Item SAPPHIRE_STAFF = registerItem("sapphire_staff", new SapphireStaff(new FabricItemSettings().maxCount(1)));

    public static final Item BLIZZARD_STAFF = registerItem("blizzard_staff", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item METEORITE_STAFF = registerItem("meteorite_staff", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SCULKBEAM_STAFF = registerItem("sculkbeam_staff", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item STAFF_OF_EARTH = registerItem("staff_of_earth", new Item(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(StaffsMod.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.INGREDIENTS, AMETHYST_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, DIAMOND_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, EMERALD_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, RUBY_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, SAPPHIRE_STAFF);

        addToItemGroup(ItemGroups.INGREDIENTS, BLIZZARD_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, METEORITE_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, SCULKBEAM_STAFF);
        addToItemGroup(ItemGroups.INGREDIENTS, STAFF_OF_EARTH);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        StaffsMod.LOGGER.info("Registering Mod Items for " + StaffsMod.MOD_ID);

        addItemsToItemGroup();
    }
}

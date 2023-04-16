package net.kaupenjoe.magnificentstaffs.item;

import net.kaupenjoe.magnificentstaffs.StaffsMod;
import net.kaupenjoe.magnificentstaffs.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, StaffsMod.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_STAFF = ITEMS.register("amethyst_staff",
            () -> new AmethystStaff(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> DIAMOND_STAFF = ITEMS.register("diamond_staff",
            () -> new DiamondStaff(new Item.Properties().durability(1024)));
    public static final RegistryObject<Item> EMERALD_STAFF = ITEMS.register("emerald_staff",
            () -> new EmeraldStaff(new Item.Properties().durability(512)));
    public static final RegistryObject<Item> RUBY_STAFF = ITEMS.register("ruby_staff",
            () -> new RubyStaff(new Item.Properties().durability(512)));
    public static final RegistryObject<Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff",
            () -> new SapphireStaff(new Item.Properties().durability(256)));

    public static final RegistryObject<Item> BLIZZARD_STAFF = ITEMS.register("blizzard_staff",
            () -> new Item(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> METEORITE_STAFF = ITEMS.register("meteorite_staff",
            () -> new Item(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> SCULKBEAM_STAFF = ITEMS.register("sculkbeam_staff",
            () -> new Item(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> STAFF_OF_EARTH = ITEMS.register("staff_of_earth",
            () -> new Item(new Item.Properties().durability(128)));

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

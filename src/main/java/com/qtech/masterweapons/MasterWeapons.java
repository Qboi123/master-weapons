package com.qtech.masterweapons;

import com.qtech.masterweapons.worldgen.WorldGeneration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Master Weapons main mod class.
 *
 * @author Qboi123
 */
@Mod(MasterWeapons.MOD_ID)
public class MasterWeapons {
    public static final String MOD_ID = "masterweapons";
    public static final String MOD_NAME = "Master Weapons";
    public static final ItemGroup ITEM_GROUP = new MWItemGroup();
    private static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public MasterWeapons() {
        LOGGER.info("Just loaded Master Weapons Mod initializer.");

        // Registering event handlers to mod eventbus.
        LOGGER.info("Registering event handlers to mod eventbus.");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::serverSetup);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        ModItems.REGISTER.register(modEventBus);
        ModBlocks.REGISTER.register(modEventBus);

        // Registering event handlers to forge eventbus.
        LOGGER.info("Registering event handlers to forge eventbus.");
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        forgeEventBus.register(this);
    }

    /**
     * Server setup handler.
     * Used for initializing non-graphical side.
     *
     * @param event an {@link FMLDedicatedServerSetupEvent} object.
     */
    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
        LOGGER.info("Master Weapons mod on dedicated server setup.");
    }

    /**
     * Common setup handler.
     * Used for dual sided initialization. (Client & server.)
     *
     * @param event an {@link FMLCommonSetupEvent} object.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Master Weapons mod on generic setup.");
        WorldGeneration.generate();
    }

    /**
     * Client setup handler.
     * Used for initializing graphical side.
     *
     * @param event an {@link FMLClientSetupEvent} object.
     */
    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Master Weapons mod on client setup.");
    }

    /**
     * Server starting handler.
     *
     * @param event an {@link FMLServerStartingEvent} object.
     */
    @SubscribeEvent
    public void serverStart(FMLServerStartingEvent event) {
        LOGGER.info("Server starting with Master Weapons!");
    }

    /**
     * The Master Weapons mod item group (tab).
     *
     * @author Qboi123
     */
    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    private static class MWItemGroup extends ItemGroup {
        public MWItemGroup() {
            super("master_weapons");
        }

        /**
         * Create the icon for the item group.
         *
         * @return an item stack for the icon.
         */
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.MASTER_SWORD.get());
        }

//        /**
//         * This method fills in the item stacks for the item group.
//         *
//         * @param items an list to fill in the item stacks.
//         */
//        @Override
//        public void fill(NonNullList<ItemStack> items) {
//            items.add(new ItemStack(ModItems.MASTER_SWORD.get()));
//            items.add(new ItemStack(ModItems.MASTER_AXE.get()));
//            items.add(new ItemStack(ModItems.MASTER_PICKAXE.get()));
//            items.add(new ItemStack(ModItems.MASTER_SHOVEL.get()));
//            items.add(new ItemStack(ModItems.MASTER_HOE.get()));
//            items.add(new ItemStack(ModItems.MASTER_HELMET.get()));
//            items.add(new ItemStack(ModItems.MASTER_CHESTPLATE.get()));
//            items.add(new ItemStack(ModItems.MASTER_LEGGINGS.get()));
//            items.add(new ItemStack(ModItems.MASTER_INGOT.get()));
//            items.add(new ItemStack(ModItems.MASTER_NUGGET.get()));
//            items.add(new ItemStack(ModBlocks.MASTER_BLOCK.get().asItem()));
//            items.add(new ItemStack(ModBlocks.MASTER_ORE.get().asItem()));
//        }
    }
}
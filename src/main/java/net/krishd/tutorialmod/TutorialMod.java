package net.krishd.tutorialmod;

import com.mojang.logging.LogUtils;
import net.krishd.tutorialmod.block.ModBlocks;
import net.krishd.tutorialmod.item.ModCreativeModTabs;
import net.krishd.tutorialmod.item.ModItems;
import net.krishd.tutorialmod.item.custom.GeigerCounterItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Common setup code here
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.URANIUM_INGOT);
            event.accept(ModItems.RAW_URANIUM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server starting logic here
    }

    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");

            // Register the custom item properties (textures based on distance)
            ItemProperties.register(ModItems.GEIGER_COUNTER.get(),
                    new ResourceLocation(TutorialMod.MOD_ID, "texture"),
                    (stack, level, entity, seed) -> {
                        if (entity != null && level != null) {
                            Vec3 playerPos = entity.position();

                            // Assuming the item is the Geiger Counter, cast and call the custom getTexture method
                            if (stack.getItem() instanceof GeigerCounterItem geigerCounterItem) {
                                return geigerCounterItem.getTexture(level, playerPos);
                            }
                        }
                        return 0.0f; // Default texture
                    }
            );
        }
    }
}

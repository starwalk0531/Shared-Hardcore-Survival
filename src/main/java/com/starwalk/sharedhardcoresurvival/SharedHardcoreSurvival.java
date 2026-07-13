package com.starwalk.sharedhardcoresurvival;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafxmod.FXModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("sharedhardcoresurvival")
public class SharedHardcoreSurvival {
    public static final String MOD_ID = "sharedhardcoresurvival";
    public static final Logger LOGGER = LoggerFactory.getLogger("Shared Hardcore Survival");

    public SharedHardcoreSurvival() {
        IEventBus modEventBus = FXModLoadingContext.getInstance().getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Shared Hardcore Survival MOD loaded!");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientSetup {
        @net.minecraftforge.api.distmarker.OnlyIn(Dist.CLIENT)
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup for Shared Hardcore Survival");
        }
    }
}

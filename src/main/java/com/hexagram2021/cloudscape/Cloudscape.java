package com.hexagram2021.cloudscape;

import com.hexagram2021.cloudscape.common.config.CSCommonConfig;
import com.hexagram2021.cloudscape.common.utils.CSLogger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;

@Mod(Cloudscape.MODID)
public class Cloudscape {
    public static final String MODID = "cloudscape";

    public Cloudscape() {
        CSLogger.logger = LogManager.getLogger(MODID);

        CSCommonConfig.registerConfig();
    }
}

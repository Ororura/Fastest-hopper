package com.ororura.fastesthopper.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final Path configPath = FMLPaths.CONFIGDIR.get();

    // Путь к конфигурационному файлу с разделителем директорий
    private static final String CONFIG_FILE = configPath.resolve("hopper_config.json").toString();

    private int coolDownValue = 8;

    public static Config loadConfig() {
        Gson gson = new Gson();
        File configFile = new File(CONFIG_FILE);
        try (FileReader reader = new FileReader(configFile)) {
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            logger.error("Error loading config file. Creating new config.", e);
            return new Config();
        }
    }

    public static void createConfigFile() {
        File configFile = new File(CONFIG_FILE);
        try {
            if (configFile.createNewFile()) {
                Config defaultConfig = new Config();
                defaultConfig.saveConfig();
                logger.info("Created new config file: {}", configFile.getAbsolutePath());
            } else {
                logger.info("Config file already exists: {}", configFile.getAbsolutePath());
            }
        } catch (IOException e) {
            logger.error("Error creating config file.", e);
        }
    }

    public void saveConfig() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(this, writer);
            logger.info("Saved config to file: {}", CONFIG_FILE);
        } catch (IOException e) {
            logger.error("Error saving config file.", e);
        }
    }

    public int getCooldownValue() {
        return coolDownValue;
    }

    // Для тестирования
    public void setCooldownValue(int coolDownValue) {
        this.coolDownValue = coolDownValue;
    }
}

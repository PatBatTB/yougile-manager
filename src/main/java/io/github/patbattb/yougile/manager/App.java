package io.github.patbattb.yougile.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.github.patbattb.plugins.manager.exception.PluginNotLoadedException;
import io.github.patbattb.plugins.manager.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Path PLUGINS_FOLDER = Path.of("plugins");
    private static final Path CONFIG_FILE = Path.of("manager.config");

    public static void main(String[] args) {
        Parameters parameters = new Parameters(CONFIG_FILE);
        init();
        PluginLoader loader = new JarPluginLoader(PLUGINS_FOLDER);
        PluginManager manager;
        try {
            manager = new PluginManager(loader);
        } catch (PluginNotLoadedException e) {
            throw new RuntimeException(e);
        }
        try (PluginScheduler scheduler = new PluginScheduler(manager,
                new PluginExecutor(parameters.getThreadPool()), parameters.getCycleTimeout())) {
            scheduler.run();
        }
    }

    private static void init() {
        createPluginsFolder();
        initProperties();
    }

    private static void createPluginsFolder() {
        if (!Files.exists(PLUGINS_FOLDER)) {
            try {
                Files.createDirectory(PLUGINS_FOLDER);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void initProperties() {
        if (!Files.exists(CONFIG_FILE)) {
            throw new RuntimeException("The config file " + CONFIG_FILE + " doesn't found.");
        }
    }
}
package io.github.patbattb.yougile.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.github.patbattb.plugins.manager.exception.PluginNotLoadedException;
import io.github.patbattb.plugins.manager.service.*;

public class App {

    private static final Path PLUGINS_FOLDER = Path.of("plugins");

    public static void main(String[] args) {
        init();
        PluginLoader loader = new JarPluginLoader(PLUGINS_FOLDER);
        PluginManager manager;
        try {
            manager = new PluginManager(loader);
        } catch (PluginNotLoadedException e) {
            throw new RuntimeException(e);
        }
        try (PluginScheduler scheduler = new PluginScheduler(manager, new PluginExecutor(30), 30)) {
            scheduler.run();
        }
    }

    private static void init() {
        createPluginsFolder();
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
}
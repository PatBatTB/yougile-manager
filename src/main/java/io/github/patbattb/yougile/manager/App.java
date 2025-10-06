package io.github.patbattb.yougile.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.patbattb.plugins.manager.exception.PluginNotLoadedException;
import io.github.patbattb.plugins.manager.service.*;
import io.github.patbattb.plugins.manager.smtp.MailClient;
import io.github.patbattb.plugins.manager.smtp.Sender;
import io.github.patbattb.yougile.manager.params.Parameters;

public class App {

    private static final Path PLUGINS_FOLDER = Path.of("plugins");
    private static final Path CONFIG_FILE = Path.of("manager.config.json");


    public static void main(String[] args) {
        Parameters parameters = readParameters(CONFIG_FILE);
        createPluginsFolder(PLUGINS_FOLDER);
        PluginLoader loader = new JarPluginLoader(PLUGINS_FOLDER);
        MailClient mailClient = getMailClient(parameters);
        PluginManager manager;
        try {
            manager = new PluginManager(loader, mailClient,
                    parameters.mail().report().interrupted(), parameters.mail().report().critical());
        } catch (PluginNotLoadedException e) {
            throw new RuntimeException(e);
        }
        PluginExecutor executor = new PluginExecutor(parameters.manager().threads());
        executor.setTerminationTimeout(parameters.manager().terminationTimeout());
        try (PluginScheduler scheduler = new PluginScheduler(manager, executor, parameters.manager().cycle())) {
            scheduler.run();
            System.exit(scheduler.getExitCode());
        }
    }

    private static Parameters readParameters(Path configFile) {
        JsonMapper mapper = new JsonMapper();
        try {
            return mapper.readValue(configFile.toFile(), Parameters.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static MailClient getMailClient(Parameters parameters) {
        return new MailClient(
                parameters.mail().smtp().host(),
                parameters.mail().smtp().port(),
                parameters.mail().smtp().username(),
                parameters.mail().smtp().password(),
                parameters.mail().smtp().ssl(),
                new Sender(parameters.mail().from().name(), parameters.mail().from().email()),
                parameters.mail().recipients()
        );
    }

    private static void createPluginsFolder(Path pluginsFolder) {
        if (!Files.exists(pluginsFolder)) {
            try {
                Files.createDirectory(pluginsFolder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
package name.richardson.james.dynamicmotd;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import name.richardson.james.dynamicmotd.random.RandomMessageList;
import name.richardson.james.dynamicmotd.rotation.RotatingMessageList;
import name.richardson.james.dynamicmotd.util.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class DynamicMOTD extends JavaPlugin {
  
  public static int maximiumMOTDLength = 35;
  public static int ellipsesStart = maximiumMOTDLength - 3;
  
  private PluginManager pluginManager;
  private PluginDescriptionFile description;
  private YamlConfiguration configuration;
  private MessageList messageList;
  
  private List<String> defaultMessages = Arrays.asList("Hello", "Bonjour");
  private ServerListener serverListener;
  
  public void onDisable() {
    Logger.info(description.getName() + " is now disabled.");
  }

  @Override
  public void onEnable() {
    pluginManager = this.getServer().getPluginManager();
    description = this.getDescription();
    
    try {
      configuration = loadConfiguration();
      messageList = loadMessageList();
    } catch (IOException exception) {
      Logger.severe("Unable to load a configuration file!");
      this.pluginManager.disablePlugin(this);
    } catch (IllegalArgumentException exception) {
      Logger.severe("Invalid message mode specified!");
      this.pluginManager.disablePlugin(this);
    } finally {
      if (!this.pluginManager.isPluginEnabled(this)) {
        return;
      }
    }
    
    registerEvents();
    Logger.info(description.getFullName() + " is now enabled.");
  }
  
  private YamlConfiguration loadConfiguration() throws IOException {
    Logger.info("Loading configuration: config.yml.");
    File configurationFile = getFile("config.yml");
    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configurationFile);
    configuration.addDefault("mode", "rotation");
    configuration.options().copyDefaults(true);
    configuration.save(configurationFile);
    return configuration;
  }
  
  private MessageList loadMessageList() throws IOException {
    Logger.info("Loading messages: messages.yml.");
    File messageListFile = getFile("messages.yml");
    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(messageListFile);
    configuration.addDefault("messages", defaultMessages);
    configuration.options().copyDefaults(true);
    configuration.save(messageListFile);
    Logger.info(String.format("%d message(s) loaded.", configuration.getStringList("messages").size()));
    if (this.configuration.getString("mode").equalsIgnoreCase("rotation")) {
      Logger.info("Choosing messages through rotation.");
      return new RotatingMessageList(configuration);
    } else if (this.configuration.getString("mode").equalsIgnoreCase("random")) {
      Logger.info("Choosing messages randomly.");
      return new RandomMessageList(configuration);
    } else {
      throw new IllegalArgumentException();
    }
  }

  private void registerEvents() {
    serverListener = new ServerListener(messageList);
    pluginManager.registerEvent(Event.Type.SERVER_LIST_PING, serverListener, Event.Priority.Low, this);
  }
  
  private File getFile(String name) {
    return new File(this.getDataFolder() + File.separator + name);
  }
  

  
}

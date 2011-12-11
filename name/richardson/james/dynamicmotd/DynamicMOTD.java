/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * DynamicMOTD.java is part of DynamicMOTD.
 * 
 * DynamicMOTD is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 * 
 * DynamicMOTD is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with DynamicMOTD.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
  
  private static final Logger logger = new Logger(DynamicMOTD.class);
  
  private PluginManager pluginManager;
  private PluginDescriptionFile description;
  private YamlConfiguration configuration;
  private MessageList messageList;
  
  private List<String> defaultMessages = Arrays.asList("Hello", "Bonjour");
  private ServerListener serverListener;
  
  public void onDisable() {
    logger.info(description.getName() + " is now disabled.");
  }

  @Override
  public void onEnable() {
    pluginManager = this.getServer().getPluginManager();
    description = this.getDescription();
    
    try {
      configuration = loadConfiguration();
      messageList = loadMessageList();
      registerEvents();
    } catch (IOException exception) {
      logger.severe("Unable to load a configuration file!");
      this.pluginManager.disablePlugin(this);
    } catch (IllegalArgumentException exception) {
      logger.severe("Invalid message mode specified!");
      this.pluginManager.disablePlugin(this);
    } finally {
      if (!this.pluginManager.isPluginEnabled(this)) {
        return;
      }
    }
    
    logger.info(description.getFullName() + " is now enabled.");
  }
  
  private YamlConfiguration loadConfiguration() throws IOException {
    logger.info("Loading configuration: config.yml.");
    File configurationFile = getFile("config.yml");
    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configurationFile);
    configuration.addDefault("mode", "rotation");
    configuration.options().copyDefaults(true);
    configuration.save(configurationFile);
    return configuration;
  }
  
  private MessageList loadMessageList() throws IOException {
    logger.info("Loading messages: messages.yml.");
    File messageListFile = getFile("messages.yml");
    YamlConfiguration configuration = YamlConfiguration.loadConfiguration(messageListFile);
    configuration.addDefault("messages", defaultMessages);
    configuration.options().copyDefaults(true);
    configuration.save(messageListFile);
    logger.info(String.format("%d message(s) loaded.", configuration.getStringList("messages").size()));
    if (this.configuration.getString("mode").equalsIgnoreCase("rotation")) {
      logger.info("Choosing messages through rotation.");
      return new RotatingMessageList(configuration);
    } else if (this.configuration.getString("mode").equalsIgnoreCase("random")) {
      logger.info("Choosing messages randomly.");
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

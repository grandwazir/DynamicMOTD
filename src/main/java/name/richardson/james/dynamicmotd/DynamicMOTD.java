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
 * DynamicMOTD is distributed in the hope that it will be useful, but WITHOUT
 * ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DynamicMOTD. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package name.richardson.james.dynamicmotd;

import java.io.IOException;
import java.io.InputStream;

import name.richardson.james.dynamicmotd.random.RandomMessageList;
import name.richardson.james.dynamicmotd.rotation.RotatingMessageList;
import name.richardson.james.dynamicmotd.util.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicMOTD extends JavaPlugin {

  private static final Logger logger = new Logger(DynamicMOTD.class);
  private static DynamicMOTD instance;

  private PluginManager pluginManager;
  private PluginDescriptionFile description;
  private MessagesList messageList;
  private ServerListPingListener serverListener;

  public enum Modes {
    ROTATION,
    RANDOM
  }

  public DynamicMOTD() {
    instance = this;
  }

  public void onDisable() {
    logger.info(description.getName() + " is now disabled.");
  }

  @Override
  public void onEnable() {
    pluginManager = this.getServer().getPluginManager();
    description = this.getDescription();

    try {
      loadConfiguration();
      messageList = loadMessageList();
      registerEvents();
    } catch (IOException exception) {
      logger.severe("Unable to load a configuration file!");
      this.pluginManager.disablePlugin(this);
    } catch (IllegalArgumentException exception) {
      logger.severe("Invalid message mode specified!");
      this.pluginManager.disablePlugin(this);
    } catch (IllegalStateException exception) {
      logger.severe(exception.getMessage());
      this.pluginManager.disablePlugin(this);
    } finally {
      if (!this.pluginManager.isPluginEnabled(this)) { return; }
    }

    logger.info(description.getFullName() + " is now enabled.");
  }

  private void loadConfiguration() throws IOException {
    final InputStream defaults = getResource("config.yml");
    DynamicMOTDConfiguration configuration = new DynamicMOTDConfiguration(defaults);
    if (configuration.isDebugging()) {
      Logger.enableDebugging();
      configuration.logValues();
    }
  }

  private MessagesList loadMessageList() throws IOException {
    final InputStream defaults = getResource("messages.yml");
    switch (DynamicMOTDConfiguration.getInstance().getMode()) {
      case ROTATION:
        logger.info("Choosing messages through rotation.");
        return new RotatingMessageList(defaults);
      case RANDOM:
        logger.info("Choosing messages randomly.");
        return new RandomMessageList(defaults);
    }
    throw new IllegalArgumentException();
  }

  private void registerEvents() {
    serverListener = new ServerListPingListener(messageList);
    pluginManager.registerEvent(Event.Type.SERVER_LIST_PING, serverListener, Event.Priority.Low, this);
  }

  public static DynamicMOTD getInstance() {
    return instance;
  }

}

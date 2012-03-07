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

import name.richardson.james.bukkit.utilities.internals.Logger;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;
import name.richardson.james.dynamicmotd.random.RandomMessageList;
import name.richardson.james.dynamicmotd.rotation.RotatingMessageList;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicMOTD extends SimplePlugin {

  private MessagesListConfiguration messageList;
  private DynamicMOTDConfiguration configuration;

  public enum Modes {
    ROTATION,
    RANDOM
  }

  public void onDisable() {
    logger.info(this.getDescription().getName() + " is now disabled.");
  }

  @Override
  public void onEnable() {

    try {
      this.setResourceBundle();
      this.loadConfiguration();
      this.loadMessageList();
      this.registerEvents();
    } catch (IOException exception) {
      logger.severe("Unable to load a configuration file!");
      this.setEnabled(false);
    } catch (IllegalArgumentException exception) {
      logger.severe("Invalid message mode specified!");
      this.setEnabled(false);
    } catch (IllegalStateException exception) {
      logger.severe(exception.getMessage());
      this.setEnabled(false);
    } finally {
      if (!this.isEnabled()) { return; }
    }

    logger.info(this.getDescription().getFullName() + " is now enabled.");
  }

  private void loadConfiguration() throws IOException {
    configuration = new DynamicMOTDConfiguration(this);
    if (configuration.isDebugging()) {
      Logger.setDebugging(this, true);
      configuration.logValues();
    }
  }

  private MessagesListConfiguration loadMessageList() throws IOException {
    switch (configuration.getMode()) {
      case ROTATION:
        logger.info("Choosing messages through rotation.");
        return new RotatingMessageList(this);
      case RANDOM:
        logger.info("Choosing messages randomly.");
        return new RandomMessageList(this);
    }
    throw new IllegalArgumentException();
  }
  
  public MessagesListConfiguration getMessagesList() {
    return this.messageList;
  }

  private void registerEvents() {
    this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
  }

}

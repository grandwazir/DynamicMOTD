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

import name.richardson.james.bukkit.utilities.internals.Logger;
import name.richardson.james.bukkit.utilities.plugin.SimplePlugin;
import name.richardson.james.dynamicmotd.random.RandomMessageList;
import name.richardson.james.dynamicmotd.rotation.RotatingMessageList;

public class DynamicMOTD extends SimplePlugin {

  public enum Modes {
    ROTATION,
    RANDOM
  }

  private MessagesListConfiguration messageList;

  private DynamicMOTDConfiguration configuration;

  public MessagesListConfiguration getMessagesList() {
    return this.messageList;
  }

  @Override
  public void onDisable() {
    this.logger.info(this.getDescription().getName() + " is now disabled.");
  }

  @Override
  public void onEnable() {

    try {
      // this.setResourceBundle(); TODO: localise plugin
      this.logger.setPrefix("[DynamicMOTD] ");
      this.loadConfiguration();
      this.loadMessageList();
      this.registerEvents();
    } catch (final IOException exception) {
      this.logger.severe("Unable to load a configuration file!");
      this.setEnabled(false);
    } catch (final IllegalArgumentException exception) {
      this.logger.severe("Invalid message mode specified!");
      this.setEnabled(false);
    } catch (final IllegalStateException exception) {
      this.logger.severe(exception.getMessage());
      this.setEnabled(false);
    } finally {
      if (!this.isEnabled()) {
        return;
      }
    }

    this.logger.info(this.getDescription().getFullName() + " is now enabled.");
  }

  private void loadConfiguration() throws IOException {
    this.configuration = new DynamicMOTDConfiguration(this);
    if (this.configuration.isDebugging()) {
      Logger.setDebugging(this, true);
      this.configuration.logValues();
    }
  }

  private void loadMessageList() throws IOException {
    switch (this.configuration.getMode()) {
    case ROTATION:
      this.logger.info("Choosing messages through rotation.");
      this.messageList = new RotatingMessageList(this);
      break;
    case RANDOM:
      this.logger.info("Choosing messages randomly.");
      this.messageList = new RandomMessageList(this);
      break;
    default:
      throw new IllegalArgumentException();
    }
  }

  private void registerEvents() {
    this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
  }

}

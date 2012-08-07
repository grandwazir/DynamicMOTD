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

package name.richardson.james.bukkit.dynamicmotd;

import java.io.IOException;

import name.richardson.james.bukkit.dynamicmotd.random.RandomMessageList;
import name.richardson.james.bukkit.dynamicmotd.rotation.RotatingMessageList;
import name.richardson.james.bukkit.utilities.plugin.AbstractPlugin;

public class DynamicMOTD extends AbstractPlugin {

  public enum Modes {
    ROTATION,
    RANDOM
  }

  private MessagesListConfiguration messageList;

  private DynamicMOTDConfiguration configuration;

  public String getArtifactID() {
    return "dynamic-motd";
  }

  public MessagesListConfiguration getMessagesList() {
    return this.messageList;
  }

  private void loadMessageList() throws IOException {
    switch (this.configuration.getMode()) {
    case ROTATION:
      this.getCustomLogger().debug(this, "rotation-mode");
      this.messageList = new RotatingMessageList(this);
      break;
    case RANDOM:
      this.getCustomLogger().debug(this, "random-mode");
      this.messageList = new RandomMessageList(this);
      break;
    default:
      throw new IllegalArgumentException();
    }
  }

  protected void loadConfiguration() throws IOException {
    this.configuration = new DynamicMOTDConfiguration(this);
    this.loadMessageList();
  }

  protected void registerEvents() {
    this.getServer().getPluginManager().registerEvents(new ServerListPingListener(this), this);
  }

}

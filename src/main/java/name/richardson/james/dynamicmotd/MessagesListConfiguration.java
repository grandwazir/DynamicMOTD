/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * MessageList.java is part of DynamicMOTD.
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
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import name.richardson.james.bukkit.utilities.configuration.AbstractConfiguration;

public abstract class MessagesListConfiguration extends AbstractConfiguration {

  protected final List<?> messages;

  public MessagesListConfiguration(final DynamicMOTD plugin) throws IOException {
    super(plugin, "messages.yml");
    this.messages = this.configuration.getStringList("messages");
  }
  
  @Override
  public void setDefaults() throws IOException {
    logger.debug(String.format("Apply default configuration."));
    final org.bukkit.configuration.file.YamlConfiguration defaults = getDefaults();
    configuration.setDefaults(defaults);
    configuration.options().copyDefaults(true);
    // set example messages if necessary
    if (!configuration.isList("messages")) {
      logger.debug("Creating example messages.");
      List<String> messages = Arrays.asList("Testing...", "1", "2", "3");
      configuration.set("messages", messages);
    }
    save();
  }

  public int size () {
    return messages.size();
  }
  
  abstract public String getMOTD();

}

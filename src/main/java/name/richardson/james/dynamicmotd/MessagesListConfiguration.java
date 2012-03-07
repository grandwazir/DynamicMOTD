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
import java.util.List;

import name.richardson.james.bukkit.utilities.configuration.AbstractConfiguration;

public abstract class MessagesListConfiguration extends AbstractConfiguration {

  protected final List<?> messages;

  public MessagesListConfiguration(final DynamicMOTD plugin) throws IOException {
    super(plugin, "messages.yml");
    this.messages = this.configuration.getStringList("messages");
    this.logger.info(String.format("%d message(s) loaded.", this.messages.size()));
  }

  abstract public String getMOTD();

}

/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * ServerListener.java is part of DynamicMOTD.
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

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import name.richardson.james.bukkit.utilities.internals.Logger;

public class ServerListPingListener implements Listener {

  public static int maximiumMOTDLength = 35;
  public static int ellipsesStart = maximiumMOTDLength - 3;

  private final static Logger logger = new Logger(ServerListPingListener.class);
  
  final private MessagesListConfiguration messageList;

  public ServerListPingListener(MessagesListConfiguration messageList) {
    this.messageList = messageList;
  }

  @EventHandler(priority = EventPriority.NORMAL)
  public void onServerListPing(ServerListPingEvent event) {
    String message = trimMessage(messageList.getMOTD());
    logger.debug("Recieved ping request, setting MOTD: " + message);
    event.setMotd(message);
  }

  private String trimMessage(String message) {
    if (message.length() > maximiumMOTDLength) {
      message = message.substring(0, maximiumMOTDLength);
      message = message.substring(0, ellipsesStart) + "...";
    }
    return message;
  }

}

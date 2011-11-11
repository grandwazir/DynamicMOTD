package name.richardson.james.dynamicmotd;

import name.richardson.james.dynamicmotd.util.Logger;

import org.bukkit.event.server.ServerListPingEvent;

public class ServerListener extends org.bukkit.event.server.ServerListener {
  
  final private MessageList messageList;
  
  
  public ServerListener(MessageList messageList) {
    this.messageList = messageList;
  }
  
  public void onServerListPing(ServerListPingEvent event) {
    String message = trimMessage(messageList.getMOTD());
    Logger.debug("Recieved ping request, setting MOTD: " + message);
    event.setMotd(message);
  }
  
  private String trimMessage(String message) {
    if (message.length() > DynamicMOTD.maximiumMOTDLength) {
      message = message.substring(0, DynamicMOTD.maximiumMOTDLength);
      message = message.substring(0, DynamicMOTD.ellipsesStart) + "...";
    }
    return message;
  }
  
}

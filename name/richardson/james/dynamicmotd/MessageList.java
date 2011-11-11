package name.richardson.james.dynamicmotd;

import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;


public abstract class MessageList {
 
  protected List<?> messages;
  
  public MessageList(YamlConfiguration configuration) {
    super();
    messages = configuration.getStringList("messages");
  }
  
  abstract public String getMOTD();
  
  
}
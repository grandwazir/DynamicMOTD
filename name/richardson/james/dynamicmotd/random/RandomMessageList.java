package name.richardson.james.dynamicmotd.random;

import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;

import name.richardson.james.dynamicmotd.*;


public class RandomMessageList extends MessageList {
  
  public RandomMessageList(YamlConfiguration configuration) {
    super(configuration);
  } 

  @Override
  public String getMOTD() {
    Integer random = new Random().nextInt(messages.size());
    return messages.get(random).toString();
  }

}

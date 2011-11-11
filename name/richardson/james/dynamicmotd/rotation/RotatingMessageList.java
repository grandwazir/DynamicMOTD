package name.richardson.james.dynamicmotd.rotation;

import java.util.Iterator;

import org.bukkit.configuration.file.YamlConfiguration;

import name.richardson.james.dynamicmotd.*;


public class RotatingMessageList extends MessageList {

  private Iterator<?> iterator;
  
  public RotatingMessageList(YamlConfiguration configuration) {
    super(configuration);
    refreshIterator();
  }

  @Override
  public String getMOTD() {
    // if we have reached the end of the list, start at the top again
    if (!iterator.hasNext()) {
      refreshIterator();
    }
    return iterator.next().toString();
  }

  private void refreshIterator() {
    iterator = messages.iterator();
  }
  
}

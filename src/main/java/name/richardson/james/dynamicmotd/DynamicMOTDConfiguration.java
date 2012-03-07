
package name.richardson.james.dynamicmotd;

import java.io.IOException;

import name.richardson.james.bukkit.utilities.configuration.AbstractConfiguration;
import name.richardson.james.bukkit.utilities.internals.Logger;

public class DynamicMOTDConfiguration extends AbstractConfiguration {

  protected final static Logger logger = new Logger(DynamicMOTDConfiguration.class);
  protected final static String fileName = "config.yml";

  public DynamicMOTDConfiguration(DynamicMOTD plugin) throws IOException {
    super(plugin, "config.yml");
  }

  public DynamicMOTD.Modes getMode() {
    final String mode = configuration.getString("mode").toUpperCase();
    return DynamicMOTD.Modes.valueOf(DynamicMOTD.Modes.class, mode);
  }

  public boolean isDebugging() {
    return configuration.getBoolean("debugging");
  }
  
  public void logValues() {
    logger.config(String.format("debugging: %b", isDebugging()));
    logger.config(String.format("mode: %s", getMode().toString()));
  }

}

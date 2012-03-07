package name.richardson.james.dynamicmotd;

import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;

import name.richardson.james.bukkit.utilities.configuration.AbstractConfiguration;
import name.richardson.james.bukkit.utilities.internals.Logger;

public class DynamicMOTDConfiguration extends AbstractConfiguration {

  protected final static Logger logger = new Logger(DynamicMOTDConfiguration.class);
  protected final static String fileName = "config.yml";

  public DynamicMOTDConfiguration(final DynamicMOTD plugin) throws IOException {
    super(plugin, "config.yml");
  }

  public DynamicMOTD.Modes getMode() {
    final String mode = this.configuration.getString("mode").toUpperCase();
    return Enum.valueOf(DynamicMOTD.Modes.class, mode);
  }

  public boolean isDebugging() {
    return this.configuration.getBoolean("debugging");
  }
  
  public void logValues() {
    logger.config(String.format("debugging: %b", this.isDebugging()));
    logger.config(String.format("mode: %s", this.getMode().toString()));
  }

}

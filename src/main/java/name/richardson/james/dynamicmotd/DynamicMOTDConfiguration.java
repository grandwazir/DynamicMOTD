
package name.richardson.james.dynamicmotd;

import java.io.IOException;
import java.io.InputStream;

import name.richardson.james.dynamicmotd.util.Configuration;
import name.richardson.james.dynamicmotd.util.Logger;

public class DynamicMOTDConfiguration extends Configuration {

  protected final static Logger logger = new Logger(DynamicMOTDConfiguration.class);
  protected final static String fileName = "config.yml";

  public DynamicMOTDConfiguration(InputStream defaults) throws IOException {
    super(fileName, defaults);
  }

  public static DynamicMOTDConfiguration getInstance() {
    return (DynamicMOTDConfiguration) instance;
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

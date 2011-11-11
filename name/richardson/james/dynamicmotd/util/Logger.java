package name.richardson.james.dynamicmotd.util;

import java.util.logging.Level;

public class Logger {
  
  static private java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Minecraft");
  static private String prefix = "[DynamicMOTD] ";
  static private Level debugLevel = Level.FINE;
  
  public static void info(String message) {
    logger.info(prefix + message);
  }
  
  public static void warning(String message) {
    logger.warning(prefix + message);
  }
  
  public static void severe(String message) {
    logger.severe(prefix + message);
  }
  
  public static void debug(String message) {
    logger.fine(prefix + message);
  }
  
  public static boolean isDebugging() {
    return logger.isLoggable(debugLevel);
  }
  
  public static void setDebugging(Boolean value) {
    logger.setLevel(debugLevel);
  }
  
}

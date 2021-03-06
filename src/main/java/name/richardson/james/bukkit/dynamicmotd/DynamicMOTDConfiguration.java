package name.richardson.james.bukkit.dynamicmotd;

import java.io.IOException;

import name.richardson.james.bukkit.utilities.configuration.PluginConfiguration;

public class DynamicMOTDConfiguration extends PluginConfiguration {

  public DynamicMOTDConfiguration(final DynamicMOTD plugin) throws IOException {
    super(plugin);
  }

  public DynamicMOTD.Modes getMode() {
    final String mode = this.getConfiguration().getString("mode").toUpperCase();
    return Enum.valueOf(DynamicMOTD.Modes.class, mode);
  }
  
}

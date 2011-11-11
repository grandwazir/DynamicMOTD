/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * RotatingMessageList.java is part of DynamicMOTD.
 * 
 * DynamicMOTD is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 * 
 * DynamicMOTD is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with DynamicMOTD.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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

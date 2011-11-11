/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * RandomMessageList.java is part of DynamicMOTD.
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
package name.richardson.james.dynamicmotd.random;

import java.util.Random;

import name.richardson.james.dynamicmotd.MessageList;

import org.bukkit.configuration.file.YamlConfiguration;

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

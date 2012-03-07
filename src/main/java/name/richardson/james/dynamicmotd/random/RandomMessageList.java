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
 * DynamicMOTD is distributed in the hope that it will be useful, but WITHOUT
 * ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DynamicMOTD. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package name.richardson.james.dynamicmotd.random;

import java.io.IOException;
import java.util.Random;

import name.richardson.james.dynamicmotd.DynamicMOTD;
import name.richardson.james.dynamicmotd.MessagesListConfiguration;

public class RandomMessageList extends MessagesListConfiguration {

  public RandomMessageList(final DynamicMOTD plugin) throws IOException {
    super(plugin);
  }

  @Override
  public String getMOTD() {
    final Integer random = new Random().nextInt(this.messages.size());
    return this.messages.get(random).toString();
  }

}

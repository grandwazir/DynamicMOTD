/*******************************************************************************
 * Copyright (c) 2011 James Richardson.
 * 
 * MessageList.java is part of DynamicMOTD.
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

package name.richardson.james.dynamicmotd;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import name.richardson.james.dynamicmotd.util.Configuration;
import name.richardson.james.dynamicmotd.util.Logger;

public abstract class MessagesList extends Configuration {

  protected final static Logger logger = new Logger(MessagesList.class);
  protected final static String fileName = "messages.yml";
  protected final List<?> messages;

  public MessagesList(InputStream defaults) throws IOException {
    super(fileName, defaults);
    messages = configuration.getStringList("messages");
    logger.info(String.format("%d message(s) loaded.", messages.size()));
  }

  abstract public String getMOTD();

}

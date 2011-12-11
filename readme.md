DynamicMOTD - change your server's MOTD dynamically.
====================================

## Description

DynamicMOTD is plugin for the Minecraft wrapper [Bukkit](http://bukkit.org/) that provides a way for administrators to change the MOTD for their server automatically when a player pings your server.

There are currently two supported modes:

- Rotation
- Random

## Installation

### Ensure you are using the latest recommended build.

Before you installing DynamicMOTD, you need to make sure you are running at least the latest [recommended build](http://ci.bukkit.org/job/dev-CraftBukkit/Recommended/) for Bukkit. 

*SPECIAL NOTE*

Recommended build 1337 will not work with this plugin. Before you installing DynamicMOTD, you need to make sure you are running Bukkit version 1.8.1-RC5 or above.

### Getting DynamicMOTD

The best way to install DynamicMOTD is to use the direct download link to the latest version:

    http://downloads.james.richardson.name/public/binaries/dynamicmotd/DynamicMOTD.jar
    
Older versions are available as well, however they are not supported.

    http://downloads.james.richardson.name/public/binaries/dynamicmotd/

### Configuration

Then you choose your mode in config.yml The configuration file can be found in the datafolder for the plugin which is usually: 

    plugins/DynamicMOTD/config.yml
    
Then simply add your messages to messages.yml. For example:

    messages:
      - Hello there
      - Another message

Further [instructions](https://github.com/grandwazir/DynamicMOTD/wiki/instructions) are available on wiki.
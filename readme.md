DynamicMOTD - change your server's MOTD dynamically.
====================================

DynamicMOTD is plugin for the Minecraft wrapper [Bukkit](http://bukkit.org/) that provides a way for administrators to change the MOTD for their server automatically when a player pings your server.

## Features

There are currently two supported modes:

- Rotation
- Random

## Installation

### Ensure you are using the latest recommended build.

Before installing, you need to make sure you are running at least the latest [recommended build](http://dl.bukkit.org/latest-rb/craftbukkit.jar) for Bukkit. Support is only given for problems when using a recommended build. This does not mean that the plugin will not work on other versions of Bukkit, the likelihood is it will, but it is not supported.

### Getting DynamicMOTD

The best way to install DynamicMOTD is to use the [symbolic link](http://repository.james.richardson.name/symbolic/DynamicMOTD.jar) to the latest version. This link always points to the latest version of DynamicMOTD, so is safe to use in scripts or update plugins. Additionally you can to use the [RSS feed](http://dev.bukkit.org/server-mods/DynamicMOTD/files.rss) provided by BukkitDev as this also includes a version changelog.
    
Alternatively [older versions](http://repository.james.richardson.name/releases/name/richardson/james/bukkit/dynamic-motd/) are available as well, however they are not supported. If you are forced to use an older version for whatever reason, please let me know why by [opening a issue](https://github.com/grandwazir/DynamicMOTD/issues/new) on GitHub.

## Configuration

Then you choose your mode in config.yml The configuration file can be found in the datafolder for the plugin which is usually: 

    plugins/DynamicMOTD/config.yml
    
Then simply add your messages to messages.yml. For example:

    messages:
      - Hello there
      - Another message

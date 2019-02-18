package ml.nextuniverse.rglobby;

/**
 * Created by TheDiamondPicks on 21/07/2017.
 */

import org.bukkit.Bukkit;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by TheDiamondPicks on 20/07/2017.
 */
public class Subscriber extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        if (channel.equals("RandomGame")) {
            if (message.startsWith("LobbyShutdown")) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
            }
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}



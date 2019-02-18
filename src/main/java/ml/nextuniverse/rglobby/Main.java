package ml.nextuniverse.rglobby;

import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by TheDiamondPicks on 20/07/2017.
 */
public class Main extends JavaPlugin {
    JedisPoolConfig poolConfig;
    JedisPool jedisPool;
    Jedis subscriberJedis;
    Subscriber subscriber;

    @Override
    public void onEnable() {
        poolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(poolConfig, "localhost", 6379, 0);
        subscriberJedis = jedisPool.getResource();
        subscriber = new Subscriber();

        new Thread(new Runnable() {
            public void run() {
                try {
                    getLogger().info("Subscribing to \"RandomGame\". This thread will be blocked.");
                    subscriberJedis.subscribe(subscriber, "RandomGame");
                    getLogger().info("Subscription ended.");
                } catch (Exception e) {
                    getLogger().severe("Subscribing failed." + e);
                }
            }
        }).start();
    }
}

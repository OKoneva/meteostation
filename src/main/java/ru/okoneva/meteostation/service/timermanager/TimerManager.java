package ru.okoneva.meteostation.service.timermanager;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Olga Koneva
 * @version 1.0 03 Mar 2014
 */
public class TimerManager {

    private static final Logger LOG = Logger.getLogger(TimerManager.class);

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    });

    private final Map<String, ScheduledFuture> tasks = new ConcurrentHashMap<String, ScheduledFuture>();

    private final AtomicBoolean isCancelled = new AtomicBoolean(false);

    public void execute(final Runnable task) {
        executor.execute(task);
    }

    public void scheduleWithFixedRate(
        final Runnable task,
        final long period,
        final String cityName
    ) {
        final ScheduledFuture newTask = executor.scheduleAtFixedRate(task, 0, period, TimeUnit.SECONDS);
        final ScheduledFuture oldTask = tasks.put(cityName, newTask);
        if (oldTask != null) {
            oldTask.cancel(false);
            LOG.info(cityName + " is cancelled.");
        }
        LOG.info(cityName + " is started with period " + period);
    }

    public void cancelTask(final String cityName) {
        ScheduledFuture oldTask = tasks.remove(cityName);
        if (oldTask != null) {
            oldTask.cancel(false);
            LOG.info(cityName + " is cancelled.");
        }
    }

    public void stop() {
        if (isCancelled.compareAndSet(false, true)) {
            executor.shutdown();
            LOG.info("TimerManager is stopped.");
        } else {
            LOG.info("TimerManager is already stopped.");
        }
    }
}

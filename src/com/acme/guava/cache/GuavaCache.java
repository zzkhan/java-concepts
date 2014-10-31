package com.acme.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class GuavaCache {
    static Map<String, String> repository = new HashMap<String, String>();

    static {
        repository.put("zahid","Khan");
        repository.put("nayel","Khan");
        repository.put("asma","Khan");
        repository.put("minni","Khan");
        repository.put("aisha","Khan");
    }

    private LoadingCache<String, String> cache;
    private final Loader loader = new Loader();

    public GuavaCache() {
        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(loader);
        cache.asMap().putAll(repository);
    }

    public String get(String key){
        return cache.getUnchecked(key);
    }

    public static class Loader extends CacheLoader<String,String>{
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        @Override
        public String load(String key) throws Exception {
            System.out.println(String.format("getting from origin for key %s", key));
            return String.valueOf(key + System.currentTimeMillis());
        }

        @Override
        public ListenableFuture<String> reload(final String key, String oldValue) throws Exception {

            ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
                public String call() {
                    System.out.println(String.format("Thread %s reloading from origin for key %s", Thread.currentThread().getId(), key));
                    return String.valueOf(key + System.currentTimeMillis());
                }
            });
            executorService.execute(task);
            return task;
        }

        @Override
        public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
            return super.loadAll(keys);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuavaCache guavaCache = new GuavaCache();

        guavaCache.get("zahid");
        Thread.sleep(3000);
        guavaCache.get("zahid");

        for (int i = 0; i < 10000000; i++) {
            String value = guavaCache.get("zahid");
            System.out.println(String.format("value retrieved: %s",value));
            Thread.sleep(1000);
        }
    }
}

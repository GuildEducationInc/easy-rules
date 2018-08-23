package org.jeasy.rules.mvel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MVELCache {
    private static class Holder {
        static MVELCache instance = new MVELCache();
    }

    private ConcurrentHashMap<String, Object> cache;

    private MVELCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public MVELAction getAction(String expression) {
        cache.putIfAbsent(expression, new MVELAction(expression));
        return (MVELAction) cache.get(expression);
    }

    @SuppressWarnings("unchecked")
    public MVELCondition getCondition(String expression) {
        cache.putIfAbsent(expression, new MVELCondition(expression));
        return (MVELCondition) cache.get(expression);
    }

    public static MVELCache getCache() {
        return Holder.instance;
    }
}

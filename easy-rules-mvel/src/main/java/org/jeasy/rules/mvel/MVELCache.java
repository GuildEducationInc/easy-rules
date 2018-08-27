package org.jeasy.rules.mvel;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class MVELCache {
    private static class Holder {
        static MVELCache instance = new MVELCache();
    }

    private ConcurrentHashMap<String, Object> cache;

    private MVELCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public MVELAction getAction(final String expression) {
        cache.computeIfAbsent(expression, (Function<String, MVELAction>) MVELAction::new);
        return (MVELAction) cache.get(expression);
    }

    @SuppressWarnings("unchecked")
    public MVELCondition getCondition(String expression) {
        cache.computeIfAbsent(expression, (Function<String, MVELCondition>) MVELCondition::new);
        return (MVELCondition) cache.get(expression);
    }

    public static MVELCache getCache() {
        return Holder.instance;
    }
}

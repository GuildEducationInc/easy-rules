package org.jeasy.rules.mvel;

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
        return (MVELAction) cache.computeIfAbsent(expression, (Function<String, MVELAction>) MVELAction::new);
    }

    @SuppressWarnings("unchecked")
    public MVELCondition getCondition(String expression) {
        return (MVELCondition) cache.computeIfAbsent(expression, (Function<String, MVELCondition>) MVELCondition::new);
    }

    public static MVELCache getCache() {
        return Holder.instance;
    }
}

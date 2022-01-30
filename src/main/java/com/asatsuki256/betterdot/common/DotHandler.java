package com.asatsuki256.betterdot.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DotHandler implements IDotHandler {

    public Map<String, Integer> iTicks = new HashMap<>();

    @Override
    public void tick() {
        Iterator<String> it = iTicks.keySet().iterator(); // イテレーション中に変更を加えるため、拡張for文ではなくIteratorで書く
        while (it.hasNext()) {
            String key = it.next();
            int duration = iTicks.get(key) - 1;
            if (duration > 0) {
                iTicks.put(key, duration);
            } else {
                it.remove();
                //iTicks.remove(key);
            }
        }
    }
}

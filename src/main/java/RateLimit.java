package main.java;

import java.util.HashMap;
import java.util.Map;

public class RateLimit {
    Map<String, ApiLimit> map;

    public RateLimit(String id, ApiLimit apiLimit) {
        this.map = new HashMap<>();
        this.map.put(id, apiLimit);
    }

    public void accessApplication(String id) {
        if (map.get(id).grantAccess()) {
            System.out.println("Access Granted for: " + Thread.currentThread().getName());
        } else {
            System.out.println("Too many request: " + Thread.currentThread().getName());
        }
    }
}

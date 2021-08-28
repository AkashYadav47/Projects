package com.redis.Entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyValue {
    String key;
    private HashMap<String,Attribute> attributes;

    public KeyValue(String key, List<Attribute> attributes) {
        this.key = key;
        this.attributes = new HashMap<>();
        for(Attribute attr : attributes) {
            this.attributes.put(attr.getKey(),attr);
        }
    }

    public void updateAttributes (List<Attribute> attrs) {
        this.attributes = new HashMap<>();
        for(Attribute attr : attrs) {
            this.attributes.put(attr.getKey(),attr);
        }
    }

    public Attribute getAttr (String attrKey) {
        if(attributes.containsKey(attrKey)) {
            return attributes.get(attrKey);
        }
        return null;
    }
    public String toString () {
        String s = "";
        for(Map.Entry<String,Attribute> attr : attributes.entrySet()) {
            s = s.concat(attr.getKey() + " : " + attr.getValue().getValue() + ", ");
        }
        return s;
    }
}

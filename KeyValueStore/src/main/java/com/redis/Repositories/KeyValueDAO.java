package com.redis.Repositories;

import com.redis.Entities.Attribute;
import com.redis.Entities.KeyValue;
import com.redis.Exceptions.KeyValueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyValueDAO {
    HashMap<String,KeyValue> keyValueStore;
    HashMap<String,DataType> dataTypes;

    private Boolean flag;

    public enum DataType {
        STRING,
        INTEGER,
        DOUBLE,
        BOOLEAN
    }

    public KeyValueDAO() {
        this.keyValueStore = new HashMap<>();
        this.dataTypes = new HashMap<>();
        flag = false;
    }


    public KeyValue get(String key) throws KeyValueException {
        if(keyValueStore.containsKey(key)) {
            return keyValueStore.get(key);
        } else {
            throw new KeyValueException("No Entry Found for "+key);
        }
    }

    synchronized public void put (String key, String attrList) throws KeyValueException {
        try {
            while (flag) {
                wait();
            }
            flag = true;

            String[] args = attrList.split(" ");
            List<Attribute> attrs = new ArrayList<>();

            for (int i=0; i<args.length; i += 2) {
                DataType type = getDataType(args[i+1]);
                if(!validateDataType(args[i],type)) {
                    throw new KeyValueException("Data Type Error");
                }
                Attribute attr = new Attribute(args[i],args[i+1]);
                attrs.add(attr);
            }

            if (keyValueStore.containsKey(key)) {
                KeyValue keyValue = keyValueStore.get(key);
                keyValue.updateAttributes(attrs);
            } else {
                KeyValue keyValue = new KeyValue(key, attrs);
                keyValueStore.put(key, keyValue);
            }
            flag = false;
            notify();
        } catch (InterruptedException e) {
            throw new KeyValueException("ERROR: Thread Interrupted. ");
        } catch (KeyValueException e) {
            throw e;
        } finally {
            flag = false;
        }
    }

    synchronized public void delete (String key) throws KeyValueException {
        try {
            while (flag) {
                wait();
            }
            flag = true;
            if (keyValueStore.containsKey(key)) {
                keyValueStore.remove(key);
            } else {
                throw new KeyValueException("No Entry Found for " + key);
            }
            flag = false;
            notify();
        } catch (InterruptedException e) {
            throw new KeyValueException("ERROR: Thread Interrupted. ");
        } catch (KeyValueException e) {
            throw e;
        } finally {
            flag = false;
        }
    }

    public List<String> search (String attrKey, String attrVal) {
        List<String> result = new ArrayList<>();

        for(Map.Entry<String,KeyValue> entry : keyValueStore.entrySet()) {
            Attribute a = entry.getValue().getAttr(attrKey);
            if(a != null && a.getValue().equals(attrVal)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public List<String> keys () {
        List<String> keyList = new ArrayList<>();
        for(Map.Entry<String,KeyValue> entry : keyValueStore.entrySet()) {
            keyList.add(entry.getKey());
        }
        return keyList;
    }

    private DataType getDataType (String attrValue) {
        DataType type = DataType.STRING;
        try {
            Integer i = Integer.parseInt(attrValue);
            return DataType.INTEGER;
        } catch (Exception e){}
        try {
            Boolean b = Boolean.parseBoolean(attrValue);
            return DataType.BOOLEAN;
        } catch (Exception e){}
        try {
            Double d = Double.parseDouble(attrValue);
            return DataType.DOUBLE;
        } catch (Exception e){}
        return DataType.STRING;
    }

    private boolean validateDataType (String key, DataType type) {
        if(dataTypes.containsKey(key)) {
            return (dataTypes.get(key).equals(type));
        } else {
            dataTypes.put(key,type);
        }
        return true;
    }
}

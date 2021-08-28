package com.redis.Resources;

import com.redis.Entities.KeyValue;
import com.redis.Exceptions.KeyValueException;
import com.redis.Repositories.KeyValueDAO;

import java.util.List;

public class KeyValueResource {
    KeyValueDAO keyValueDAO = new KeyValueDAO();

    public KeyValue get(String key) throws KeyValueException {
        return keyValueDAO.get(key);
    }

    public void put (String key, String attrs) throws KeyValueException {
        keyValueDAO.put(key,attrs);
    }

    public void delete (String key) throws KeyValueException {
        keyValueDAO.delete(key);
    }

    public List<String> search (String s){
        String[] args = s.split(" ");
        return keyValueDAO.search(args[0],args[1]);
    }

    public List<String> keys () {
        return keyValueDAO.keys();
    }
}

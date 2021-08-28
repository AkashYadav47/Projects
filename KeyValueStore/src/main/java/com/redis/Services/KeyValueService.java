package com.redis.Services;

import com.redis.Entities.KeyValue;
import com.redis.Exceptions.KeyValueException;
import com.redis.Resources.KeyValueResource;

import java.io.BufferedReader;
import java.io.FileReader;

public class KeyValueService {
    static final KeyValueResource keyValueResource = new KeyValueResource();

    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\akashyad\\IdeaProjects\\KeyValueStore\\src\\test\\KeyValueTest.txt"));
            String s;
            while ( (s = bf.readLine())!= null ) {
                String[] input = s.split(" ", 2);
                if(input.length == 1 ) {
                    requestBuilder(input[0], null);
                } else {
                    requestBuilder(input[0], input[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid Input " + e.getMessage());
        }
    }

    private static void requestBuilder (String type, String s){
        switch (type){
            case "put":
                try {
                    String[] args = s.split(" ",2);
                    keyValueResource.put(args[0], args[1]);
                    System.out.println( "PUT") ;
                } catch (KeyValueException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "get":
                try {
                    KeyValue keyValue = keyValueResource.get(s);
                    System.out.println(keyValue);
                } catch (KeyValueException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    keyValueResource.delete(s);
                    System.out.println("DELETED");
                } catch (KeyValueException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "search":
                System.out.println( keyValueResource.search(s) );
                break;
            case "keys":
                System.out.println( keyValueResource.keys() );
                break;
            case "exit":
                System.exit(0);
        }
    }
}

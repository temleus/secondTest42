package com.task.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * @author Leus Artem
 * @since 15.06.13
 */
public class GsonHelper {

    public static List<Friend> deserialize(String json){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Friend>>(){}.getType();
        return gson.fromJson(json, collectionType);
    }

    public static String serialize(Collection<Friend> friends){
        Gson gson = new Gson();
        return gson.toJson(friends, new TypeToken<List<Friend>>(){}.getType());
    }
}

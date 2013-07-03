package com.task;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.Session;
import com.facebook.model.GraphLocation;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.task.json.Friend;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Leus Artem
 * @since 04.06.13
 */
public class Utils {

    /** got here in android.util.Patterns sources (supported only API level > 8)
     * http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/2.2_r1.1/android/util/Patterns.java#Patterns.0EMAIL_ADDRESS    * */
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

//    public static Collection<GraphUser> sortByPriority(Map<String, Friend> prioritizedFriends, List<GraphUser> fbFriends){
//        Map<Integer, GraphUser> treeMap = new TreeMap<Integer, GraphUser>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer l, Integer r) {
//                return (l < r ) ? -1 : 1 ;
//            }
//        });
//        for(GraphUser fbFriend: fbFriends){
//            Friend friend = prioritizedFriends.get(fbFriend.getId());
//            treeMap.put(friend.getPriority(), fbFriend);
//        }
//        return treeMap.values();
//    }

    /** 0 - the most prioritized user */
    public static int getPriorityByString(Context context, String s){
        String[] prioritiesArray = context.getResources().getStringArray(R.array.priorities_array);
        for(int n = 0; n<prioritiesArray.length; n++){
            if(prioritiesArray[n].equals(s)){
                return n;
            }
        }
        return -1;
    }

    public static boolean isValidEmail(String email){
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static Date convertStringToDate(String dateStr){
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date date){
        return dateFormat.format(date);
    }

    public static boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean isFbAuthenticated(){
        Session currentSession = Session.getActiveSession();
        return currentSession != null && currentSession.getState().isOpened();
    }
}

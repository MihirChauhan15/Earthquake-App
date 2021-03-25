package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.DateFormat;
import  java.util.Date;

public final class QueryUtils {

    public QueryUtils() {

    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public  ArrayList<Earthquake> extractEarthquakes(String str) {


        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        int i=0;
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject rootJSONObject = new JSONObject(str);
            JSONArray featureJSONArray = rootJSONObject.getJSONArray("features");

            for(i=0;i<featureJSONArray.length();i++){
                JSONObject earthquakeJSONObject = featureJSONArray.getJSONObject(i);
                JSONObject propertiesJSONobject = earthquakeJSONObject.getJSONObject("properties");

                Double magnitude=propertiesJSONobject.getDouble("mag");
                String place = propertiesJSONobject.getString("place");
                long date = propertiesJSONobject.getLong("time");
                String url =propertiesJSONobject.getString("url");

                earthquakes.add(new Earthquake(magnitude,place,date,url));
            }




        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}


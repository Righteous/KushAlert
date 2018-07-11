/*
 * I dont care what you do with my code.
 * Feel free to copy it, spit on it, or whatever makes you happy.
 * Github: https://www.github.com/Righteous
 */
package com.badkush.kushtrayicon;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BadKushInfo {

    private static String apiUrl = "http://api.badkush.com/";
    static String userAgent = RandomUserAgent.getRandomUserAgent();
    public static String getBossEventTime() {
        System.out.println("Debug:[URL]->" + apiUrl + "event");
        return getUrlContents(apiUrl + "event");
    }

    public static String getGameUptime() { return getUrlContents(apiUrl + "uptime"); }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection();
            
            urlConnection.setRequestProperty("User-Agent", userAgent);
            urlConnection.setRequestProperty("Referer", apiUrl);
            urlConnection.setRequestProperty("Accept", "text/plain");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (IOException io) {
            System.out.println("User-Agent: " + userAgent);
            System.out.println("Exception: " + io.getMessage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
    

}


package com.nyn.movieapptest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class MediaFireConnect {
    static String BaseURL = "https://media-fire-api.herokuapp.com/?url=";
    public static String getFileLink (String videoLink){
        String query = URLEncoder.encode(videoLink);
        String url = BaseURL + query;
        try {
            URL myurl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)myurl.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-type","application/json");
            httpURLConnection.setRequestProperty("Accept","application/json:charset=utf-8");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            StringBuilder sb = new StringBuilder();
            while((line = bufferedReader.readLine())!= null){
                sb.append(line);
            }
            JSONArray result = new JSONArray(sb.toString());
            JSONObject obj = result.getJSONObject(0);
            String fileLink = obj.getString("file");

            String[] filepart = fileLink.split(":");
            fileLink = filepart[0]+"s:"+filepart[1];
            return fileLink;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

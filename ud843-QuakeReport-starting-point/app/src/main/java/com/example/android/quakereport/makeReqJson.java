package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class makeReqJson extends AsyncTaskLoader<String> {
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    String reqURL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=8&limit=30";

    public makeReqJson(Context context,String REQ_URL) {
        super(context);
        reqURL = REQ_URL;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        HttpURLConnection knect = null;
        InputStream inputStream = null;
        String reqResponse = "";
        try {
            URL url = new URL(reqURL);
            knect = (HttpURLConnection) url.openConnection();
            knect.setRequestMethod("GET");
            knect.setReadTimeout(10000 /* milliseconds */);
            knect.setConnectTimeout(15000 /* milliseconds */);
            knect.connect();
            if (knect.getResponseCode() == 200) {
                inputStream = knect.getInputStream();
                reqResponse = readFromStream(inputStream);
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {

        } finally {
            if (knect != null) {
                knect.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }

            return reqResponse;
        }
    }
}
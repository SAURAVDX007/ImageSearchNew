package com.example.saurav.imagesearch;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

enum DownloadStatus {IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK}
public class GetRawData {
    private String LOG_TAG = GetRawData.class.getSimpleName();
    private String rawUrl;
    private String data;
    private DownloadStatus downloadStatus;

    public GetRawData(String rawUrl) {
        this.rawUrl = rawUrl;
        downloadStatus = DownloadStatus.IDLE;
    }
    public void reset(){
        downloadStatus = DownloadStatus.IDLE;
        rawUrl = null;
        data = null;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public String getData() {
        return data;
    }

    public DownloadStatus getDownloadStatus() {
        return downloadStatus;
    }
    public void execute(){
        downloadStatus = DownloadStatus.PROCESSING;
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(rawUrl);
    }
    public class DownloadRawData extends AsyncTask<String, Void, String>{

        @Override
        protected void onPostExecute(String webData) {
            data = webData;
            Log.v(LOG_TAG,"Data Returned was "+data);
            if (data == null){
                if (rawUrl==null){
                    downloadStatus = DownloadStatus.NOT_INITIALISED;
                }
                else {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
                }
            }else {
                downloadStatus = DownloadStatus.OK;
            }
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            if (params == null) {
                return null;
            }
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                if (inputStream == null){
                    return null;
                }
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine())!=null){
                    buffer.append(line + "\n");
                }
                return buffer.toString();
            }catch (IOException e){
                Log.e(LOG_TAG,"error",e);
                return null;
            }
        }
    }
}

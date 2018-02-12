package com.example.poojajoshi.assignment_16_0_1;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.StringWriter;

public class FileReadWrite extends AsyncTask <String, Integer, String >{

    File file;
    String uText;
    TextView fileTextVIew;

    public FileReadWrite(File file, String userText, TextView textView) {
        super();
        this.file = file;
        this.uText = userText;
        this.fileTextVIew = textView;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String doInBackground(String... str) {
        String name = "\n";
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, true);
            writer.append(str[0].toString());
            writer.append(name);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    /*
    @Override
    protected String doInBackground(String... str) {
        String enter = "\n";
        FileWriter writer = null;
        try {
            writer = new FileWriter(file,true);
            writer.append(str[0].toString());
            writer.append(enter);
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;

    }
    */

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        String s = "";
        StringBuilder sb = new StringBuilder();
        FileReader reader = null;

        try{
            reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            while ( (s=bReader.readLine()) != null ) {
                sb.append(s);
            }
            bReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileTextVIew.setText(sb.toString());
    }

    /*
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String name = "";
        StringBuilder sb = new StringBuilder();
        FileReader fr = null;

        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((name = br.readLine()) != null) {
                sb.append(name);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // content.setText(sb.toString());
    }
    */
}

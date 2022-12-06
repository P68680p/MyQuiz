package com.example.myquiz;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageManager {
    String fileName = "Scores.txt";
    int attempts;
    int averageScore;
    String stringFromStorage;

    //save data to file storage
    public void saveData(Activity activity, String scores) {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = activity.openFileOutput(fileName, Context.MODE_APPEND);
            fileOutput.write(scores.getBytes());
            System.out.println("---> scores = " + scores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //get data from local storage
    public String getData(Activity activity) {
        String s = "Data not found";
        FileInputStream inputStream;
        StringBuffer stringBuffer = new StringBuffer();
        int read;
        try {
            inputStream = activity.openFileInput(fileName);
            while ((read = inputStream.read()) != -1) {
                stringBuffer.append((char) read);
                stringFromStorage = stringBuffer.toString();
            }
            return stringFromStorage;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public int countAttempts() {
        attempts = 0;
        for (int i = 0; i < stringFromStorage.length(); i++) {
            if (stringFromStorage.toCharArray()[i] == '#') {
                attempts++;
            }
        }
        return attempts;
    }

    public int countAverageScore() {
        int startIndex = -1;
        int endIndex = 0;
        averageScore = 0;
        for (int i = 0; i < stringFromStorage.length(); i++) {
            endIndex = stringFromStorage.indexOf("/", startIndex);
            if (endIndex == -1) {
                break;
            }
            averageScore = averageScore + Integer.parseInt(stringFromStorage.substring(startIndex + 1, endIndex));
            startIndex = stringFromStorage.indexOf("#", endIndex);
        }
        return averageScore;
    }

    //reset data
    public void resetData(Activity activity) {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = activity.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutput.write("".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
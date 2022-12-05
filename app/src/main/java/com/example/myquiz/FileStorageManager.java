package com.example.myquiz;

import android.app.Activity;
import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageManager {
    String fileName = "Scores.txt";
    int attempts = 0;
    int averageScore;
    String dataFromStorage;

    //save data to file storage
    public void saveData(Activity activity, String scores) {
        FileOutputStream fileOutput = null;
        try {
            fileOutput = activity.openFileOutput(fileName, Context.MODE_APPEND);
            fileOutput.write(scores.getBytes());
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
        FileInputStream inputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        int read = 0;
        try {
            inputStream = activity.openFileInput(fileName);
            while ((read = inputStream.read()) != -1) {
                stringBuffer.append((char) read);
                dataFromStorage = stringBuffer.toString();
            }
            return dataFromStorage;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public int countAttempts() {
        int i;
        attempts = 0;
        for (i = 0; i < dataFromStorage.toCharArray().length; i++) {
            if (dataFromStorage.toCharArray()[i] == '#') {
                attempts++;
            }
        }
        return attempts;
    }

    public int countAverageScore() {
        int i;
        averageScore = 0;
        for (i = 0; i < dataFromStorage.toCharArray().length; i++) {
            if (dataFromStorage.toCharArray()[i] == '/') {
                averageScore = averageScore + Character.getNumericValue(dataFromStorage.toCharArray()[i - 1]);
            }
        }
        return averageScore;
    }

    //Function to reset data
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
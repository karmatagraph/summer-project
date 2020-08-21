package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
//  this will help save the data
    public static final String FILENAME = "listinfo.dat";

    //function for the write the data
    public static void writeData(ArrayList<String> items, Context context){

        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(items);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //creating the method to read the values.
    public static ArrayList<String> readData(Context context){
        ArrayList<String> itemsList = null;
        try {

            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            itemsList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemsList = new ArrayList<>();

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

}

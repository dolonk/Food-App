package com.example.fooddelivery.Service.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.fooddelivery.Service.Model.FoodDomains;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class FoodDB {

    private SharedPreferences preferences;
    private String DEFAULT_APP_IMAGEDATA_DIRECTORY;
    private String lastImagePath = "";

    public FoodDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }


    // Decodes the Bitmap from 'path' and returns it
    public Bitmap getImage(String path) {
        Bitmap bitmapFromPath = null;
        try {
            bitmapFromPath = BitmapFactory.decodeFile(path);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bitmapFromPath;
    }


    // Returns the String path of the last saved image
    public String getSavedImagePath() {
        return lastImagePath;
    }


    // Saves 'theBitmap' into folder 'theFolder' with the name 'theImageName'
    public String putImage(String theFolder, String theImageName, Bitmap theBitmap) {
        if (theFolder == null || theImageName == null || theBitmap == null)
            return null;

        this.DEFAULT_APP_IMAGEDATA_DIRECTORY = theFolder;
        String mFullPath = setupFullPath(theImageName);

        if (!mFullPath.equals("")) {
            lastImagePath = mFullPath;
            saveBitmap(mFullPath, theBitmap);
        }
        return mFullPath;
    }


    // Saves 'theBitmap' into 'fullPath'
    public boolean putImageWithFullPath(String fullPath, Bitmap theBitmap) {
        return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap);
    }


    // Creates the path for the image with name 'imageName' in DEFAULT_APP.. directory

    private String setupFullPath(String imageName) {
        File mFolder = new File(Environment.getExternalStorageDirectory(), DEFAULT_APP_IMAGEDATA_DIRECTORY);

        if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
            if (!mFolder.mkdirs()) {
                Log.e("ERROR", "Failed to setup folder");
                return "";
            }
        }
        return mFolder.getPath() + '/' + imageName;
    }


    // Saves the Bitmap as a PNG file at path 'fullPath'
    private boolean saveBitmap(String fullPath, Bitmap bitmap) {
        if (fullPath == null || bitmap == null)
            return false;

        boolean fileCreated = false;
        boolean bitmapCompressed = false;
        boolean streamClosed = false;

        File imageFile = new File(fullPath);

        if (imageFile.exists())
            if (!imageFile.delete())
                return false;

        try {
            fileCreated = imageFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageFile);
            bitmapCompressed = bitmap.compress(CompressFormat.PNG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
            bitmapCompressed = false;

        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    streamClosed = true;

                } catch (IOException e) {
                    e.printStackTrace();
                    streamClosed = false;
                }
            }
        }
        return (fileCreated && bitmapCompressed && streamClosed);
    }

    // Getters
    //Get int value from SharedPreferences at 'key'. If key not found, return 0
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }


    // Get parsed ArrayList of Integers from SharedPreferences at 'key'
    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "â€šâ€—â€š");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<Integer>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }


    // Get long value from SharedPreferences at 'key'. If key not found, return 0

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }


    // Get float value from SharedPreferences at 'key'. If key not found, return 0
    public float getFloat(String key) {
        return preferences.getFloat(key, 0);
    }


    // Get double value from SharedPreferences at 'key'. If exception thrown, return 0
    public double getDouble(String key) {
        String number = getString(key);

        try {
            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return 0;
        }
    }


    // Get parsed ArrayList of Double from SharedPreferences at 'key'
    public ArrayList<Double> getListDouble(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "â€šâ€—â€š");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Double> newList = new ArrayList<Double>();

        for (String item : arrayToList)
            newList.add(Double.parseDouble(item));

        return newList;
    }


    // Get parsed ArrayList of Integers from SharedPreferences at 'key'

    public ArrayList<Long> getListLong(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "â€šâ€—â€š");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Long> newList = new ArrayList<Long>();

        for (String item : arrayToList)
            newList.add(Long.parseLong(item));

        return newList;
    }


    // Get String value from SharedPreferences at 'key'. If key not found, return ""
    public String getString(String key) {
        return preferences.getString(key, "");
    }


    // Get parsed ArrayList of String from SharedPreferences at 'key'
    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "â€šâ€—â€š")));
    }


    // Get boolean value from SharedPreferences at 'key'. If key not found, return false
    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }


    // Get parsed ArrayList of Boolean from SharedPreferences at 'key'
    public ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> myList = getListString(key);
        ArrayList<Boolean> newList = new ArrayList<Boolean>();

        for (String item : myList) {
            if (item.equals("true")) {
                newList.add(true);
            } else {
                newList.add(false);
            }
        }

        return newList;
    }


    public ArrayList<FoodDomains> getListObject(String key) {
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<FoodDomains> playerList = new ArrayList<FoodDomains>();

        for (String jObjString : objStrings) {
            FoodDomains player = gson.fromJson(jObjString, FoodDomains.class);
            playerList.add(player);
        }
        return playerList;
    }


    public <T> T getObject(String key, Class<T> classOfT) {
        String json = getString(key);
        Object value = new Gson().fromJson(json, classOfT);
        if (value == null)
            throw new NullPointerException();
        return (T) value;
    }


    // Put methods
    // Put int value into SharedPreferences with 'key' and save
    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }


    // Put ArrayList of Integer into SharedPreferences with 'key' and save
    public void putListInt(String key, ArrayList<Integer> intList) {
        checkForNullKey(key);
        Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
        preferences.edit().putString(key, TextUtils.join("â€šâ€—â€š", myIntList)).apply();
    }


    // Put long value into SharedPreferences with 'key' and save
    public void putLong(String key, long value) {
        checkForNullKey(key);
        preferences.edit().putLong(key, value).apply();
    }

    // Put ArrayList of Long into SharedPreferences with 'key' and save
    public void putListLong(String key, ArrayList<Long> longList) {
        checkForNullKey(key);
        Long[] myLongList = longList.toArray(new Long[longList.size()]);
        preferences.edit().putString(key, TextUtils.join("â€šâ€—â€š", myLongList)).apply();
    }


    // Put float value into SharedPreferences with 'key' and save
    public void putFloat(String key, float value) {
        checkForNullKey(key);
        preferences.edit().putFloat(key, value).apply();
    }


    // Put double value into SharedPreferences with 'key' and save
    public void putDouble(String key, double value) {
        checkForNullKey(key);
        putString(key, String.valueOf(value));
    }


    // Put ArrayList of Double into SharedPreferences with 'key' and save
    public void putListDouble(String key, ArrayList<Double> doubleList) {
        checkForNullKey(key);
        Double[] myDoubleList = doubleList.toArray(new Double[doubleList.size()]);
        preferences.edit().putString(key, TextUtils.join("â€šâ€—â€š", myDoubleList)).apply();
    }


    // Put String value into SharedPreferences with 'key' and save
    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }


    // Put ArrayList of String into SharedPreferences with 'key' and save
    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("â€šâ€—â€š", myStringList)).apply();
    }


    // Put boolean value into SharedPreferences with 'key' and save

    public void putBoolean(String key, boolean value) {
        checkForNullKey(key);
        preferences.edit().putBoolean(key, value).apply();
    }


    // Put ArrayList of Boolean into SharedPreferences with 'key' and save
    public void putListBoolean(String key, ArrayList<Boolean> boolList) {
        checkForNullKey(key);
        ArrayList<String> newList = new ArrayList<String>();

        for (Boolean item : boolList) {
            if (item) {
                newList.add("true");
            } else {
                newList.add("false");
            }
        }
        putListString(key, newList);
    }


    // Put Object any type into Shared Preferences with 'key' and save
    public void putObject(String key, Object obj) {
        checkForNullKey(key);
        Gson gson = new Gson();
        putString(key, gson.toJson(obj));
    }

    public void putListObject(String key, ArrayList<FoodDomains> playerList) {
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for (FoodDomains player : playerList) {
            objStrings.add(gson.toJson(player));
        }
        putListString(key, objStrings);
    }


    // Remove SharedPreferences item with 'key'
    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }


    // Delete image file at 'path'
    public boolean deleteImage(String path) {
        return new File(path).delete();
    }


    // Clear SharedPreferences (remove everything)
    public void clear() {
        preferences.edit().clear().apply();
    }


    // Retrieve all values from SharedPreferences. Do not modify collection return by method
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }


    // Register SharedPreferences change listener
    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.registerOnSharedPreferenceChangeListener(listener);
    }


    // Unregister SharedPreferences change listener
    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }


    // Check if external storage is writable or not
    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }


    // Check if external storage is readable or not
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }


    // null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
    private void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }


    // null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
    private void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}

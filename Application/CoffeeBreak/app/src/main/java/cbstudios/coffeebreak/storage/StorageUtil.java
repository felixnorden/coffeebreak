package cbstudios.coffeebreak.storage;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * A utility class used to save and load data between application executions.
 */
public class StorageUtil {

    private StorageUtil() {
    }

    /**
     * Saves the given JsonElement to the filesystem for later use.
     *
     * @param context The Android-context where the file should be saved. Decides <i>where</i> the
     *                file should be saved on the android filesystem.
     * @param id      The identifier for the saved data.
     * @param element The JsonElement to be saved.
     */
    public static void save(Context context, String id, JsonElement element) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileOutputStream output = context.openFileOutput(id, Context.MODE_PRIVATE);
            output.write(gson.toJson(element).getBytes());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data with specified ID and returns it as a JsonElement.
     *
     * @return Loaded data as JsonElement.
     */
    public static JsonElement load(Context context, String id) {
        File file = new File(context.getFilesDir(), id);
        JsonElement element = null;

        try {
            //Read string from file and parse it to JsonObject
            FileReader reader = new FileReader(file);
            JsonParser parser = new JsonParser();
            element = parser.parse(reader);
        } catch (FileNotFoundException | IllegalStateException e) {
            e.printStackTrace();
        }
        return element;
    }
}
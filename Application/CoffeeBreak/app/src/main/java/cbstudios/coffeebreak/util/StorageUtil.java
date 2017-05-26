package cbstudios.coffeebreak.util;

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
 * @author Zacl
 * @version 1.0
 *          Responsibility: Handles saving/loading of data to/from disk.
 *          Uses: Gson.
 *          Used by: DelegatingPresenter.
 */
public class StorageUtil {

    private StorageUtil() {
    }

    /**
     * Saves the given JsonElement to the filesystem for later use.
     *
     * @param context The Android-context where the data should be saved. Decides <i>where</i> the
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
     * Loads the Json-data with the given ID and returns it as a JsonElement.
     *
     * @param context The Android-context where the data is stored.
     * @param id      The identifier for the saved data.
     * @return The loaded JsonElement.
     * @throws IllegalStateException Thrown when the data is somehow corrupt.
     * @throws FileNotFoundException Thrown when trying to load data that doesn't exist.
     */
    public static JsonElement load(Context context, String id) throws IllegalStateException, FileNotFoundException {
        File file = new File(context.getFilesDir(), id);

        //Read string from file and parse it to JsonObject
        FileReader reader = new FileReader(file);
        JsonParser parser = new JsonParser();

        return parser.parse(reader);
    }

    /**
     * Resets all the data related to the given ID. Should be used in the case of corrupt data.
     * If there is not associated data with the given ID, nothing happens.
     *
     * @param context The Android-context where the data is stored.
     * @param id      The identifier for the saved data.
     */
    public static void resetData(Context context, String id) {
        context.deleteFile(id);
    }
}
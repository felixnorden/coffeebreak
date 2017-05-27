package cbstudios.coffeebreak.util;

import com.google.gson.JsonArray;

import java.util.List;

/**
 * @author Zack
 * @version 1.0
 *          Responsibility: Abstracting interface for converters that are able to handle lists of
 *          objects as well.
 *          Uses:
 *          Used by: DelegatingPresenter,
 */

public interface IListConverter<T> extends IConverter<T> {

    /**
     * Converts a list of objects into a JsonArray.
     *
     * @param list The list of objects to be converted.
     * @return The JsonArray containing the data of the objects in the list.
     */
    JsonArray toJson(List<T> list);

    /**
     * Converts a JsonArray into a List.
     *
     * @param array The JsonArray to be converted.
     * @return The List of objects.
     */
    List<T> toObject(JsonArray array);
}

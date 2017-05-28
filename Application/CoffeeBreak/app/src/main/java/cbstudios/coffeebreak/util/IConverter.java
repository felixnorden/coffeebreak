package cbstudios.coffeebreak.util;

import com.google.gson.JsonObject;

/**
 * @author Zack
 * @version 1.0
 *          Responsibility: Abstracting interface for converters
 *          Uses:
 *          Used by: IListConverter, StatisticsConverter, DelegatingPresenter
 * @param <T> The type of objects the converter converts.
 */

public interface IConverter<T> {

    /**
     * Converts a object into a JsonObject.
     *
     * @param object The object to be converted
     * @return The converted JsonObject.
     */
    JsonObject toJson(T object);

    /**
     * Converts a JsonObject into a real object.
     *
     * @param object The JsonObject to be converted.
     * @return The converted object.
     */
    T toObject(JsonObject object);
}

package cbstudios.coffeebreak.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.categorylist.CategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ICategoryFactory;
import cbstudios.coffeebreak.model.tododatamodule.categorylist.ILabelCategory;
import cbstudios.coffeebreak.util.IConverter;
import cbstudios.coffeebreak.util.IListConverter;

/**
 * @author Zack
 * @version 1.0
 *          Responsibility: Handles converting between ILabelCategorues and JSON data.
 *          Uses:
 *          Used by:
 */
public class CategoryConverter implements IListConverter<ILabelCategory> {

    private final static CategoryConverter INSTANCE = new CategoryConverter();
    private ICategoryFactory factory = CategoryFactory.getInstance();

    //List of names for properties in Json objects.
    private static final String NAME = "Name";
    private static final String COLOR = "Color";

    /**
     * @return The only instance of the class
     */
    public static IListConverter<ILabelCategory> getInstance() {
        return INSTANCE;
    }

    /**
     * Hidden constructor because of singleton.
     */
    private CategoryConverter() {
    }

    @Override
    public JsonObject toJson(ILabelCategory object) {
        return iLabelCategoryToJsonObject(object);
    }

    @Override
    public ILabelCategory toObject(JsonObject object) {
        String name = object.get(NAME).getAsString();
        String color = object.get(COLOR).getAsString();

        return factory.createLabelCategory(name, color);
    }

    @Override
    public JsonArray toJson(List<ILabelCategory> list) {
        JsonArray array = new JsonArray();

        for (ILabelCategory category : list) {
            array.add(iLabelCategoryToJsonObject(category));
        }

        return array;
    }

    @Override
    public List<ILabelCategory> toObject(JsonArray array) {
        List<ILabelCategory> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(toObject(object));
        }

        return list;
    }

    /**
     * Converts a ILabelCategory
     *
     * @param category The category to be converted
     * @return The converted category in a JsonObject.
     */
    private JsonObject iLabelCategoryToJsonObject(ILabelCategory category) {
        JsonObject object = new JsonObject();
        object.addProperty(NAME, category.getName());
        object.addProperty(COLOR, category.getColor());

        return object;
    }
}

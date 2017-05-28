package cbstudios.coffeebreak.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.NumberAchievement;
import cbstudios.coffeebreak.util.IListConverter;

/**
 * @author Johan
 * @version 1.0
 *          Responsibility: Handles converting between Achievements and JSON data.
 *          Uses: IAchievement, NumberAchievement
 *          Used by: DelegatingPresenter.
 */

public class AchievementConverter implements IListConverter<IAchievement> {

    private final static AchievementConverter INSTANCE = new AchievementConverter();

    //List of names for properties in Json objects.
    private static final String NAME = "Name";
    private static final String NUMBER_LIMIT = "NumberLimit";
    private static final String IS_COMPLETED = "IsCompleted";
    private static final String TYPE = "Type";


    /**
     * @return Returns the only instance of this class
     */
    public static AchievementConverter getInstance() {
        return INSTANCE;
    }

    /**
     * Hidden constructor because of singleton
     */
    private AchievementConverter() {
    }

    @Override
    public JsonArray toJson(List<IAchievement> achievements) {
        JsonArray array = new JsonArray();

        for (IAchievement achievement : achievements) {
            if (achievement instanceof NumberAchievement) {
                array.add(numberAchievementToJsonObject(achievement));
            }
        }
        return array;
    }

    @Override
    public JsonObject toJson(IAchievement achievement) {
        if (achievement instanceof NumberAchievement) {
            return numberAchievementToJsonObject(achievement);
        } else {
            return null;
        }
    }

    @Override
    public IAchievement toObject(JsonObject object) {
        return jsonObjectToNumberAchievement(object);
    }

    @Override
    public List<IAchievement> toObject(JsonArray array) {
        List<IAchievement> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(jsonObjectToNumberAchievement(object));
        }
        return list;
    }

    /**
     * Converts a IAchievement into a JsonObject
     *
     * @param achievement The achiv to be converted.
     * @return The new JsonObject.
     */
    private JsonObject numberAchievementToJsonObject(IAchievement achievement) {
        JsonObject achievementObject = new JsonObject();

        achievementObject.addProperty(NAME, achievement.getName());
        achievementObject.addProperty(NUMBER_LIMIT, achievement.getNumberLimit());
        achievementObject.addProperty(IS_COMPLETED, achievement.getIfCompleted());
        achievementObject.addProperty(TYPE, achievement.getType());

        return achievementObject;
    }

    /**
     * Converts a JsonObject into a IAchievement
     *
     * @param object The JsonObject to be converted.
     * @return The new IAchievement.
     */
    private IAchievement jsonObjectToNumberAchievement(JsonObject object) {
        IAchievement numberAchievement = new NumberAchievement();

        numberAchievement.setName(object.get(NAME).getAsString());
        numberAchievement.setNumberLimit(object.get(NUMBER_LIMIT).getAsInt());
        numberAchievement.setIsCompleted(object.get(IS_COMPLETED).getAsBoolean());
        numberAchievement.setType(object.get(TYPE).getAsInt());

        return numberAchievement;
    }
}

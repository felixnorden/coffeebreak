package cbstudios.coffeebreak.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.IAchievement;
import cbstudios.coffeebreak.model.tododatamodule.statistics.achievements.NumberAchievement;

/**
 * Created by johan on 5/8/2017.
 */

public class AchievementConverter {

    private static AchievementConverter INSTANCE = new AchievementConverter();

    public static AchievementConverter getInstance() { return  INSTANCE;}

    private AchievementConverter(){

    }

    public JsonArray toJsonArray(List<IAchievement> achievements) {
        JsonArray array = new JsonArray();

        for (IAchievement achievement : achievements){
            if (achievement instanceof NumberAchievement) {
                array.add(numberAchievementToJsonObject(achievement));
            }
        }
        return array;
    }

    public JsonObject toJsonObject(IAchievement achievement){
        if (achievement instanceof NumberAchievement){
            return numberAchievementToJsonObject(achievement);
        } else {
            return null;
        }
    }

    public List<IAchievement> toAchievementList(JsonArray array){
        List<IAchievement> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            JsonObject object = array.get(i).getAsJsonObject();

            list.add(jsonObjectToNumberAchievement(object));
        }
        return list;
    }

    private JsonObject numberAchievementToJsonObject(IAchievement achievement){
        JsonObject achievementObject = new JsonObject();

        achievementObject.addProperty("Name", achievement.getName());
        achievementObject.addProperty("NumberLimit", achievement.getNumberLimit());
        achievementObject.addProperty("IsCompleted", achievement.getIfCompleted());
        achievementObject.addProperty("Type", achievement.getType());

        return achievementObject;
    }

    private IAchievement jsonObjectToNumberAchievement(JsonObject object){
        IAchievement numberAchievement = new NumberAchievement();

        numberAchievement.setName(object.get("Name").getAsString());
        numberAchievement.setNumberLimit(object.get("NumberLimit").getAsInt());
        numberAchievement.setIsCompleted(object.get("IsCompleted").getAsBoolean());
        numberAchievement.setType(object.get("Type").getAsInt());

        return numberAchievement;
    }
}

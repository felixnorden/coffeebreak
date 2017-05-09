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
                array.add(numberAchievementToJsonObject((NumberAchievement) achievement));
            }
        }
        return array;
    }

    public JsonObject toJsonObject(IAchievement achievement){
        if (achievement instanceof NumberAchievement){
            return numberAchievementToJsonObject((NumberAchievement) achievement);
        } else {
            return null;
        }
    }

    public List<IAchievement> toAchievementList(JsonArray array){
        List<IAchievement> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            JsonObject object = array.get(i).getAsJsonObject();
            String name = object.get("Name").getAsString();

            if(name.equals("Create")){
                list.add(jsonObjectToNumberAchievement(object));
            }
        }
        return list;
    }

    private JsonObject numberAchievementToJsonObject(IAchievement achievement){
        JsonObject achievementObject = new JsonObject();

        achievementObject.addProperty("Name", achievement.getName());
        achievementObject.addProperty("NumberLimit", achievement.getNumberLimit());
        achievementObject.addProperty("IsCompleted", achievement.checkIfCompleted());

        return achievementObject;
    }

    private NumberAchievement jsonObjectToNumberAchievement(JsonObject object){
        NumberAchievement numberAchievement = new NumberAchievement();

        numberAchievement.setName(object.get("Name").getAsString());
        numberAchievement.setNumberLimit(object.get("NumberLimit").getAsInt());
        numberAchievement.setIsCompleted(object.get("IsCompleted").getAsBoolean());

        return numberAchievement;
    }
}

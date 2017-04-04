package cbstudios.coffeebreak.model.tododatamodule.categorylist;
//

//  @ Project : Untitled
//  @ File Name : cbstudios.coffeebreak.model.toDoDataModule.categoryList.CategoryList.java
//  @ cbstudios.coffeebreak.model.toDoDataModule.categoryList.Date : 03/04/2017
//  @ Author : 
//
//

import java.util.List;

public class CategoryList implements ICategoryList {
    private ICategoryFactory categoryFactory;
    private List<ILabelCategory> labelCategories;
    private List<ITimeCategory> timeCategories;

    /**
     * 
     * @param name
     */
    public void addLabelCategory(String name) {
        labelCategories.add(categoryFactory.createLabelCategory(name));
    }


    public void removeLabelCategory(ILabelCategory label) {
        labelCategories.remove(label);
    }

    public void removeLabelCategory(String name) {
        for(ILabelCategory label : labelCategories){
            if(label.getName() == name){
                labelCategories.remove(label);
            }
        }
    }

    public List<ITimeCategory> getTimeCategories() {
        return timeCategories;
    }

    // TODO
    public void initTimeCategories() {

    }

    public List<ILabelCategory> getLabelCategories() {
        return labelCategories;
    }
}


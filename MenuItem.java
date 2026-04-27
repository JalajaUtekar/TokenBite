import java.util.*;

class MenuItem{

    private int itemID;
    private String itemName;
    private int itemPrice;
    private String category;
    private boolean availability;
    private int prepTime;

public MenuItem(int id, String name, int price, String cat, boolean aval, int time){
    this.itemID = id;
    this.itemName = name;
    this.itemPrice = price;
    this.category = cat;
    this.availability = aval;
    this.prepTime = time;
}

public int getID(){
    return itemID;
}

public String getName(){
    return itemName;
}

public int getPrice(){
    return itemPrice;
}

public String getCategory(){
    return category;
}

public boolean isAvailable(){
    return availability;
}

public void setAvailability(boolean status){
    this.availability = status;
}

public int getPrepTime(){
    return prepTime;
}

public String toString(){
    return itemID + " | " + itemName + " | ₹" + itemPrice + " | " + category + " | " + (availability?"Available":"Unavailable");
}

}
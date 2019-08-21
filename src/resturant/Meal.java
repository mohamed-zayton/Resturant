/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

/**
 *
 * @author EGYPT-LAPTOP
 */
public class Meal {
    int id  = 0;
    String name ,type;
    int price;
    public Meal(int id, String name, String type, int price) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    
    
    
}

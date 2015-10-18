package com.example.alberto.explorer;

/**
 * Created by Alberto on 17/10/2015.
 */
public class Demo {
    static DBadapter myDb;
    public static void create_list(DBadapter db){
//name image place type

        db.insertRow("Juan Acosta","R.drawable.spain","1280$", "Rome, Italy", "City Break", "5");
        db.insertRow("Li Huan","R.drawable.japan","750$", "Rome, Italy", "City Break", "4");
        db.insertRow("Maria Alvarez","R.drawable.spain", "1000$", "Rome, Italy", "City Break", "4");
        db.insertRow("George Anagnwstopoulos","R.drawable.greece", "700$", "Prague, Czech Republic", "City Break", "5");
        db.insertRow("Giovanni Giani", "R.drawable.italy", "2800$", "Montreux, Switcherland", "City Break", "4");
    }
}

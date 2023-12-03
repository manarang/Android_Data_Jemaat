package com.d_jemaat;

import android.database.Cursor;

public interface Crudo {
    void addJemaat(Jemaat jemaat);

    void addHadir(Present present);
    
    Cursor getAll();

    Cursor getHadir();
    
    Jemaat getJemaat(int i);

    Cursor getNamJemaat(String str);

    Cursor getUlTah(int i);

    void updateJemaat(Jemaat jemaat, int i);
 
    int getCountHadir();
    
}

package cl.telematica.multimedio.tareamultimedios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Javo on 19-12-2014.
 */
public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos (Context context){
        super(context, "Base", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE items(item INTEGER PRIMARY KEY, like BOOL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST items");
        onCreate(db);
    }

    public void like(int id) {
        ContentValues values = new ContentValues();
        Log.d("LOG", "valorid" + id);
        values.put("item", id);
        values.put("like", 1);
        this.getWritableDatabase().insert("items", null, values);
    }

    public int consultar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select like from items where item=" + id, null);
        if (c.moveToFirst())
            if (c.getInt(0)==1)
                return 2;
            else
                return 1;
        else
            return 0;
    }

    public void toggle(int id, int estado) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("like", estado);
        db.update("items", valores, "item=" + id, null);
    }

    public void close() {
        this.close();
    }
}

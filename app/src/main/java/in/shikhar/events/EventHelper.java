package in.shikhar.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EventHelper extends SQLiteOpenHelper {
    public EventHelper(@Nullable Context context) {
        super(context, "Event.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table events(id integer primary key autoincrement ,club_name TEXT,event_name TEXT,event_description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists events");
    }
    public Boolean insertevent(String club_name,String event_name,String event_description)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("club_name", club_name);
        contentValues.put("event_name", event_name);
        contentValues.put("event_description", event_description);
        long result = MyDB.insert("events", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor getinfo()
    {
     SQLiteDatabase db=this.getReadableDatabase();
     Cursor cursor=db.rawQuery("select * from events ",null);
     return cursor;
    }
    public boolean deleteEventByName(String event_name)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        long r=MyDB.delete("events","event_name=?",new String[]{event_name});
        if(r==-1)
            return false;
        return true;
    }

    public boolean eventexist(String event_name)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor c= MyDB.rawQuery("Select * from events where event_name =?",new String[]{event_name});
        if(c.getCount()>0)
            return true;
        return false;
    }
}

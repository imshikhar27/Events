package in.shikhar.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ParticipationHelper extends SQLiteOpenHelper {
    private static final String dbname="Participants.db";
    public ParticipationHelper(Context context) {
        super(context, dbname,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q="create Table involve (_id integer primary key autoincrement ,name TEXT,year TEXT,roll TEXT,phone Text,email TEXT,event Text)";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists involve");

    }
    public boolean insert_participants(String name,String year,String roll,String email,String phone,String event)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("year", year);
        contentValues.put("roll", roll);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("event", event);

        long result = MyDB.insert("involve", null, contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    public boolean deleteparticipantsByEventName(String event)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        long r=MyDB.delete("involve","event=?",new String[]{event});
        if(r==-1)
            return false;
        return true;
    }
    public Cursor getinfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from involve ",null);
        return cursor;
    }

    public boolean alreadyparticipated(String name,String event)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from involve where name = ? and event = ?", new String[] {name,event});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

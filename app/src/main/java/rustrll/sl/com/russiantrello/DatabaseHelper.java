package rustrll.sl.com.russiantrello;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MZ on 07/12/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RussianTrelloDatabase";
    private static final String TABLE_NAME = "Task";

    //Table Task columns names
    private static final String TASK_ID = "Id";
    private static final String TASK_NAME = "Name";
    private static final String TASK_STATUS = "Status";
    private static final String TASK_DUE_DATE = "DueDate";
    private static final String TASK_MOSCOW = "Moscow";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TASK_NAME + " TEXT NOT NULL,"
                + TASK_STATUS + " TEXT NOT NULL,"

        //TEXT best format is: yyyy-MM-dd HH:mm:ss
                + TASK_DUE_DATE + " TEXT NOT NULL,"
                + TASK_MOSCOW + " TEXT DEFAULT 'To Assign'" + ")";

        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String status, String dueDate, String moscow) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TASK_NAME, name);
        contentValues.put(TASK_STATUS, status);
        contentValues.put(TASK_DUE_DATE, dueDate);
        contentValues.put(TASK_MOSCOW, moscow);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public void clearAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }

    public List<Task> getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        List<Task> tasks = new ArrayList<Task>();

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            while(cursor.moveToNext()) {
                tasks.add(new Task(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            }
        }
        finally {
            if(cursor !=null)
                cursor.close();
        }
        return tasks;
    }

    public boolean updateTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TASK_MOSCOW, task.getMoscow());
        long result = db.update(TABLE_NAME, cv ,"id="+task.getId(),null);

        if(result == -1)
            return false;
        else
            return true;
    }
}

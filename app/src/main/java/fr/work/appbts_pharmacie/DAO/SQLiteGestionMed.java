package fr.work.appbts_pharmacie.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static fr.work.appbts_pharmacie.DAO.MedDAO.*;


public class SQLiteGestionMed extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "med.db";
    private static final int DATABASE_VERSION = 6;
    private Context context=null;

    private static final String SQL_CREATE_ENTRIES_MED =
            "CREATE TABLE " + TABLE_MED + " (" +
                    COL_ID_MED + " INTEGER PRIMARY KEY," +
                    COL_NOMMED + " TEXT," +
                    COL_MAUX + " TEXT," +
                    COL_DATE_ACHAT + " TEXT," +
                    COL_DATE_PEREMPTION + " TEXT," +
                    COL_AGE_MIN + " INTEGER," +
                    COL_QUANTITE + " INTEGER)";



    public SQLiteGestionMed(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("DROP TABLE IF EXISTS med");
            db.execSQL("CREATE TABLE med (id INTEGER PRIMARY KEY AUTOINCREMENT, libelle VARCHAR(100))");
            db.execSQL(SQL_CREATE_ENTRIES_MED);
        } catch (Exception ignored) {}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);


    }



}

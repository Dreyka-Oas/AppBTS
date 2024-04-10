package fr.work.appbts_pharmacie.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.work.appbts_pharmacie.med.Med;

import java.util.ArrayList;
import java.util.List;

public class MedDAO extends DAO<Med> {
    private SQLiteGestionMed dbGestionMed;

    static final String TABLE_MED = "MED", COL_ID_MED = "ID_MED", COL_NOMMED = "NOMMED", COL_MAUX = "maux", COL_DATE_ACHAT= "achat", COL_DATE_PEREMPTION = "peremption", COL_AGE_MIN = "age", COL_QUANTITE = "quantite";
    private SQLiteDatabase db;

    public MedDAO(Context context){
        dbGestionMed = new SQLiteGestionMed(context);
    }

    public void open(){
        db = dbGestionMed.getWritableDatabase();
    }
    public void close(){
        db.close();
    }

    public void insert(Med ma) {
        ContentValues values = new ContentValues();
        values.put(COL_NOMMED, ma.getNomMed());
        values.put(COL_MAUX, ma.getMaux());//
        values.put(COL_DATE_ACHAT, ma.getAchat());
        values.put(COL_DATE_PEREMPTION, ma.getPeremption());
        values.put(COL_AGE_MIN, ma.getAge());
        values.put(COL_QUANTITE, ma.getQuantite());

        db.insert(TABLE_MED, null, values);
    }

    public List<Med> readAll() {
        Log.d("MedDAO", "Lecture de toutes les med depuis la base de données.");
        this.open();
        List<Med> meds = new ArrayList<>();
        Cursor c = db.query(TABLE_MED, new String[] {COL_ID_MED, COL_NOMMED, COL_MAUX, COL_DATE_ACHAT, COL_DATE_PEREMPTION, COL_AGE_MIN, COL_QUANTITE}, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                Med med = new Med(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5), c.getInt(6));
                meds.add(med);

                Log.d("MedDAO", "Lecture depuis la base de données: ID = " + med.getIdMed() + ", Nom = " + med.getNomMed() +
                        ", Maux = " + med.getMaux() + ", Achat = " + med.getAchat() + ", Peremption = " + med.getPeremption() + ", Age = " + med.getAge() + ", Quantite = " + med.getQuantite());

            } while (c.moveToNext());
        }
        c.close();
        this.close();
        return meds;
    }


    public void update(Med obj) {
        ContentValues values = new ContentValues();
        values.put(COL_NOMMED, obj.getNomMed());
        values.put(COL_MAUX, obj.getMaux());
        values.put(COL_DATE_ACHAT, obj.getAchat());
        values.put(COL_DATE_PEREMPTION, obj.getPeremption());
        values.put(COL_AGE_MIN, obj.getAge());
        values.put(COL_QUANTITE, obj.getQuantite());

        db.update(TABLE_MED, values, COL_ID_MED + " = ?",
                new String[]{String.valueOf(obj.getIdMed())});
    }

    public void delete(Med obj) {
        this.open();
        db.delete(TABLE_MED, COL_ID_MED + " = ?", new String[]{String.valueOf(obj.getIdMed())});
        this.close();
    }

    public void ajouterMed() {
        Med med1 = new Med(1, "Test1", "maux1", "12/02/2000", "12/02/2050", 10, 5);
        Med med2 = new Med(2, "Test2", "maux2", "13/02/2000", "13/02/2050", 12, 8);


        this.open();
        this.insert(med1);
        this.insert(med2);
        this.close();
    }

    public void clearTable() {
        this.open();
        db.delete(TABLE_MED, null, null);
        this.close();
    }

    public void edit(Med med) {
        open();

        ContentValues values = new ContentValues();
        values.put(COL_NOMMED, med.getNomMed());
        values.put(COL_MAUX, med.getMaux());
        values.put(COL_DATE_ACHAT, med.getAchat());
        values.put(COL_DATE_PEREMPTION, med.getPeremption());
        values.put(COL_AGE_MIN, med.getAge());
        values.put(COL_QUANTITE, med.getQuantite());

        db.update(TABLE_MED, values, COL_ID_MED + " = ?",
                new String[]{String.valueOf(med.getIdMed())});

        close();
    }

}

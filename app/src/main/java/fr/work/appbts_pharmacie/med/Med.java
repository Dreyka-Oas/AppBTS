package fr.work.appbts_pharmacie.med;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class Med {
    private int idMed, quantite, age;
    private String nomMed, maux, achat, peremption;
    private double coefMatiere;
    public Med(int idMed, String nomMed, String maux, String achat, String peremption,Integer quantite, Integer age) {
        this.idMed = idMed;
        this.nomMed = nomMed;
        this.maux = maux;
        this.achat = achat;
        this.peremption = peremption;
        this.quantite = quantite;
        this.age = age;
    }



    public int getIdMed() {
        return idMed;
    }public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public String getNomMed() {
        return nomMed;
    }public void setNomMed(String nomMed) {
        this.nomMed = nomMed;
    }

    public String getMaux() {return maux;}public void setMaux(String maux) {
        this.maux = maux;
    }
    public String getAchat() {
        return achat;
    }public void setAchat(String achat) {
        this.achat = achat;
    }

    public String getPeremption() {
        return peremption;
    }public void setPeremption(String peremption) {
        this.peremption = peremption;
    }
    public Integer getQuantite() {
        return quantite;
    }public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getAge() {
        return age;
    }public void setAge(Integer age) {
        this.age = age;
    }


}
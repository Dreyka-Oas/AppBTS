package fr.work.appbts_pharmacie;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import fr.work.appbts_pharmacie.DAO.MedDAO;
import fr.work.appbts_pharmacie.med.Med;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnQuit, btnADD, btnFiltre;
    private MedDAO uneDAO;
    private LinearLayout linearlayout, matLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        constructeurGraphique();
        uneDAO = new MedDAO(this);
        //uneDAO.clearTable();uneDAO.ajouterMed(); // ici ici ici ici ici ici ici ici ici ici ici ici

        btnQuit.setOnClickListener(quitListener);

        List<Med> meds = uneDAO.readAll();

        for (Med med : meds){ //ic pour ajouter les med à la vue
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            Button btnMatiere = new Button(this);
            String buttonText = med.getNomMed() + " - Maux : " + med.getMaux();
            btnMatiere.setText(buttonText);
            layout.addView(btnMatiere);

            Button btnVue = new Button(this);
            String btnVuetext = "Vue";
            btnVue.setText(btnVuetext);
            layout.addView(btnVue);

            Button btnRemove = new Button(this);
            String btnRemovetext = "Remove";
            btnRemove.setText(btnRemovetext);
            layout.addView(btnRemove);

            Button btnEdit = new Button(this);
            String btnEditText = "Edit";
            btnEdit.setText(btnEditText);
            layout.addView(btnEdit);

            btnMatiere.setOnClickListener(v -> Log.d("MatiereDAO", "Matiere read from DB: " + "ID: " + ", Nom du med: " + med.getNomMed() + ", Maux: " + med.getMaux()));

            btnRemove.setOnClickListener(v -> {
                uneDAO.delete(med);
                linearlayout.removeView(layout);
            });


            btnEdit.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = getLayoutInflater().inflate(R.layout.edit_mat, null);
                builder.setView(view);

                TextInputEditText medInput = view.findViewById(R.id.nomInput);
                TextInputEditText mauxInput = view.findViewById(R.id.nomInput);

                medInput.setText(med.getNomMed());
                mauxInput.setText(med.getMaux());

                // Set input type for coefInput to enforce numerical values
                //coefInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                builder.setTitle("Modifier")
                        .setMessage("Voulez-vous vraiment modifier ce med ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            String nomMed = Objects.requireNonNull(medInput.getText()).toString();
                            String maux = Objects.requireNonNull(mauxInput.getText()).toString();
                            Log.d("test","ici");

                            med.setNomMed(nomMed);
                            med.setMaux(maux);

                            uneDAO.edit(med);

                            btnMatiere.setText(nomMed + " - Coef : " + maux);



                        })
                        .setNegativeButton("Non", null)
                        .show();
            });

            btnADD.setOnClickListener(v -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        View view = getLayoutInflater().inflate(R.layout.edit_mat, null);
                        builder.setView(view);

                        TextInputEditText medInput = view.findViewById(R.id.nomInput);
                        TextInputEditText mauxInput = view.findViewById(R.id.nomInput);


                        mauxInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                        Med newMed = new Med(0, medInput.toString(), mauxInput.toString(), "test", "test2", 10, 80);
                        uneDAO.open();
                        uneDAO.insert(newMed);
                        uneDAO.close();
                        updateUI();
                    });

            linearlayout.addView(layout);
        }

    }


    private boolean isValidNumeric(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValidSubjectName(String input) {
        try {
            return input.matches("[a-zA-Z]+");
        } catch (NumberFormatException e) {
            return false;
        }
    }



    private void constructeurGraphique(){
        linearlayout = findViewById(R.id.matLayout);
        btnADD = findViewById(R.id.btnADD);
        btnQuit = findViewById(R.id.btnQuit);
        btnFiltre = findViewById(R.id.btnFiltre);
    }
    private void updateUI() {

        List<Med> meds = uneDAO.readAll();


        LinearLayout layout = null;
        for (Med med : meds) {
            layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);

            Button btnMatiere = new Button(this);
            String buttonText = med.getNomMed() + " - Med : " + med.getMaux();
            btnMatiere.setText(buttonText);
            layout.addView(btnMatiere);

            Button btnRemove = new Button(this);
            String btnRemovetext = "Remove";
            btnRemove.setText(btnRemovetext);
            layout.addView(btnRemove);

            Button btnEdit = new Button(this);
            String btnEditText = "Edit";
            btnEdit.setText(btnEditText);
            layout.addView(btnEdit);

            btnMatiere.setOnClickListener(v -> Log.d("MatiereDAO", "Matiere read from DB: " + "ID: " + ", Nom de la matiere: " + med.getNomMed() + ", Coefficient: " + med.getMaux()));

            LinearLayout finalLayout = layout;
            btnRemove.setOnClickListener(v -> {
                uneDAO.delete(med);
                linearlayout.removeView(finalLayout);
            });


            btnEdit.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                LinearLayout layout2 = new LinearLayout(this);
                layout2.setOrientation(LinearLayout.VERTICAL);

                final EditText editText1 = new EditText(this);
                editText1.setText(med.getNomMed()); // Initialiser avec la valeur actuelle
                final EditText editText2 = new EditText(this);
                editText2.setText(String.valueOf(med.getMaux())); // Initialiser avec la valeur actuelle

                layout2.addView(editText1);
                layout2.addView(editText2);

                builder.setView(layout2)
                        .setTitle("Modifier")
                        .setMessage("Voulez-vous vraiment modifier cette matière ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            String text1 = editText1.getText().toString();
                            String text2 = editText2.getText().toString();

                            // Mettre à jour l'objet Matiere avec les nouvelles valeurs
                            med.setNomMed(text1);
                            med.setMaux(text2);

                            // Mettre à jour la base de données
                            uneDAO.edit(med);

                            // Mettre à jour le texte du bouton
                            btnMatiere.setText(text1 + " - Coef : " + text2);
                        })
                        .setNegativeButton("Non", null)
                        .show();
            });
        }

        linearlayout.addView(layout);
    }

    private View.OnClickListener quitListener = V -> new AlertDialog.Builder(MainActivity.this )
            .setTitle("Quit")
            .setMessage("Voulez vous vraiment quitter ?")
            .setPositiveButton("Oui", ((dialog, which) ->  finish()))
            .setNegativeButton("Non", null)
            .show();

}
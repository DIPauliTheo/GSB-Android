package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gsb.utils.BdAdapter;


public class MainActivity extends AppCompatActivity {

    Button addEchantillonButton;
    Button updateEchantillonButton;
    Button listEchantillonButton;
    static final int VERSION_BDD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addEchantillonButton = (Button)findViewById(R.id.menuButtonAjoutEchantillon);
        updateEchantillonButton = (Button)findViewById(R.id.menuButtonMajEchantillons);
        listEchantillonButton = (Button)findViewById(R.id.menuButtonListeEchantillons);

        addEchantillonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, addEchantillon.class);
                startActivity(intent);
            }
        });

        updateEchantillonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("creation","update layout");
                Intent intent = new Intent(MainActivity.this, majEchantillon.class);
                startActivity(intent);
            }
        });

        listEchantillonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listeEchantillon.class);
                startActivity(intent);
            }
        });


    }

    public void jeuEssaiBd(){
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);
        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        //On insère DES ECHANTILLONS DANS LA BD
        echantBdd.insererEchantillon(new Echantillon("code1", "lib1", "3"));
        echantBdd.insererEchantillon(new Echantillon("code2", "lib2", "5"));
        echantBdd.insererEchantillon(new Echantillon("code3", "lib3", "7"));
        echantBdd.insererEchantillon(new Echantillon("code4", "lib4", "6"));
        Cursor unCurseur = echantBdd.getDataEchantillons();
        System.out.println("il y a "+String.valueOf(unCurseur.getCount())+" echantillons dans la BD");
        echantBdd.close();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // L'action pour l'élément "Settings" est déclenchée ici
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
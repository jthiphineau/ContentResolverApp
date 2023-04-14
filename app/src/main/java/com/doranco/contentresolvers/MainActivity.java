package com.doranco.contentresolvers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
            ;

    }


    public void lireContacts(View arg) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            ContentResolver contentResolver = getContentResolver();
            Uri url = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor resultat = contentResolver.query(url, null, null, null, null);

            resultat.moveToFirst();
            int indexNumero = resultat.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int indiceNom = resultat.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String nom = resultat.getString(indiceNom);
            String numeroTelephone = resultat.getString(indexNumero);
            Log.e("Contact lus", "Nom du contact : " + nom);
            Log.e("Telephone lus", "Numero de téléphone : " + numeroTelephone);

            while (!resultat.isAfterLast()) {
                String noms = resultat.getString(indiceNom);
                String numero = resultat.getString(indexNumero);
                Log.e("Contact lus", "Nom du contact : " + noms);
                Log.e("Telephone lus", "Numero de téléphone : " + numero);
                resultat.moveToNext();
            }
        } else {
            Toast.makeText(this, "Donnez nous l'autorisation d'accès à vos contacts", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return super.shouldShowRequestPermissionRationale(permission);
    }
}
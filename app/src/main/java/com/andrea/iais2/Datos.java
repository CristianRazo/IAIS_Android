package com.andrea.iais2;


import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Datos extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<RecursosGupo> expandableListNombres;
    private HashMap<RecursosGupo,List<RecursosHijo>> listaC;
    private int lastExpandedPosicion = -1;

    ImageButton boton;
    int i=1;
    private Paciente paciente = new Paciente("60kg","36cm","156cm");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        Toolbar mitoolbar3 = (Toolbar) findViewById(R.id.mitoolbar3);
        setSupportActionBar(mitoolbar3);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mitoolbar3.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.menu));
        
        //ExpandableListview
        init();
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosicion!= -1 && groupPosition != lastExpandedPosicion){
                    expandableListView.collapseGroup(lastExpandedPosicion);
                }
                lastExpandedPosicion = groupPosition;
            }
        });



    }

    private void init() {
        this.expandableListView = findViewById(R.id.expandableListView);



        HashMap<RecursosGupo, List<RecursosHijo>> listaC = new HashMap<>();
        List<RecursosHijo> listaSoma = new ArrayList<>();
        listaSoma.add(new RecursosHijo("Peso",paciente.getPeso()));
        listaSoma.add(new RecursosHijo("Talla",paciente.getTalla()));
        listaSoma.add(new RecursosHijo("Estatura",paciente.getEstatura()));

        this.expandableListNombres = new ArrayList<>();

        expandableListNombres.add(new RecursosGupo(R.drawable.somatometria1,R.drawable.somatometria2));
        expandableListNombres.add(new RecursosGupo(R.drawable.signos1,R.drawable.signos2));
        expandableListNombres.add(new RecursosGupo(R.drawable.diagnostico1,R.drawable.diagnostico2));
        expandableListNombres.add(new RecursosGupo(R.drawable.tratamiento1,R.drawable.tratamiento2));
        expandableListNombres.add(new RecursosGupo(R.drawable.cuidados1,R.drawable.cuidados2));

        listaC.put(expandableListNombres.get(0),listaSoma);
        listaC.put(expandableListNombres.get(1),listaSoma);
        listaC.put(expandableListNombres.get(2),listaSoma);
        listaC.put(expandableListNombres.get(3),listaSoma);
        listaC.put(expandableListNombres.get(4),listaSoma);




        this.expandableListAdapter = new CustomExpandableListAdapter(this,expandableListNombres,
                listaC);


    }



        public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);

        return true;
    }

    public void boton(View view){

        boton = (ImageButton) findViewById(R.id.imageButton2);
        i++;
        if(i%2==0){
            boton.setBackgroundResource(R.drawable.actualizar);
            expandableListView.expandGroup(0);
        }
        else{
            boton.setBackgroundResource(R.drawable.editarperfil);
            paciente.setPeso("63kg");
            paciente.setTalla("35cm");
            paciente.setEstatura("157cm");
            init();
            expandableListView.setAdapter(expandableListAdapter);
            expandableListView.expandGroup(0);


        }


    }



}

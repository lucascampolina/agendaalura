package com.example.lucas.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucas.agenda.DetalhesProvaActivity;
import com.example.lucas.agenda.ProvasActivity;
import com.example.lucas.agenda.R;
import com.example.lucas.agenda.modelo.Prova;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lucas on 02/11/2017.
 */

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosPort = Arrays.asList("Sujeito","Verbo","Adjetivo");
        Prova provaPort = new Prova("Português","20/05/2017",topicosPort);

        List<String> topicosMat = Arrays.asList("Equacao","Geometria");
        Prova provaMat = new Prova("Matemática","25/05/2017",topicosMat);

        List<String> topicosIng = Arrays.asList("Verbo to Be","Gerúndio");
        Prova provaIngles = new Prova("Inglês","01/11/2017",topicosIng);

        List<Prova> provas = Arrays.asList(provaPort,provaMat);
        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getContext(),android.R.layout.simple_list_item_1,provas);

        ListView lista = (ListView) view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),"Clicou na prova de : "+prova,Toast.LENGTH_SHORT).show();

                ProvasActivity provasActivity = (ProvasActivity) getActivity();
                provasActivity.selecionaProva(prova);
            }
        });
        return view;

    }
}

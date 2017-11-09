package com.example.lucas.agenda;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.example.lucas.agenda.dao.AlunoDAO;
import com.example.lucas.agenda.modelo.Aluno;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lucas on 05/11/2017.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoDaEscola = pegaCoordenadaEndereco("Rua elson nunes de souza 230, castelo, belo horizonte");
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola,17);
        googleMap.moveCamera(update);

        AlunoDAO alunoDao = new AlunoDAO(getContext());

        for(Aluno aluno : alunoDao.buscaAlunos()) {
            LatLng coordenada = pegaCoordenadaEndereco(aluno.getEndereco());
            if (coordenada != null) {
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(aluno.getNome());
                marcador.snippet(String.valueOf(aluno.getNota()));
                googleMap.addMarker(marcador);
            }
        }
        alunoDao.close();
        //new Localizador(getContext(),googleMap);
    }

    private LatLng pegaCoordenadaEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);
            if (! resultados.isEmpty()) {
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(),resultados.get(0).getLongitude());
                return  posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.lucas.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.lucas.agenda.converter.AlunoConverter;
import com.example.lucas.agenda.dao.AlunoDAO;
import com.example.lucas.agenda.modelo.Aluno;

import java.util.List;

/**
 * Created by Lucas on 03/05/2017.
 */

public class EnviaAlunosTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context,"Aguarde","Enviando notas alunos...",true,true);
    }

    @Override
    protected String doInBackground(Void[] params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        AlunoConverter converter = new AlunoConverter();
        String json = converter.converteParaJson(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context,resposta,Toast.LENGTH_SHORT).show();
    }
}

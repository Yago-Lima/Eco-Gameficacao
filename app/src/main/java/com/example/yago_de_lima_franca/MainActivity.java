package com.example.yago_de_lima_franca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.yago_de_lima_franca.dao.IJogadorDao;
import com.example.yago_de_lima_franca.dao.IpartidaDao;
import com.example.yago_de_lima_franca.dao.MyDatabase;
import com.example.yago_de_lima_franca.model.ConexaoDB;
import com.example.yago_de_lima_franca.model.Jogador;
import com.example.yago_de_lima_franca.model.Partida;
import com.example.yago_de_lima_franca.utils.ConversorDeEnums;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements FimDeJogoDialogFragment.FimDeJogoDialogListener {



    private FloatingActionButton button;
    private EditText nome;
    private Jogador jogador;
    private Partida partida;
    private  Bundle bundle;
    private FragmentManager fm;
    private Toolbar myToolbar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.floating_action_button);
        nome = findViewById(R.id.editTextNome);
        radioGroup = findViewById(R.id.radioGroup);

        partida = new Partida();


        fm = getSupportFragmentManager();

        myToolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        ImageView menu = findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.insira_o_nome_do_jogador, Toast.LENGTH_SHORT).show();
                } else {
                    insertJogador(nome.getText().toString());
                    jogo();
                }
            }
        });
    }



    private void jogo() {

        FragmentTransaction ft = fm.beginTransaction();

        FragmentJogo fragmentJogo = new FragmentJogo();
        fragmentJogo.setArguments(bundle);
        ft.replace(R.id.telaPrincipal,fragmentJogo);
        ft.commit();
    }

    private void menu() {
        FragmentTransaction ft = fm.beginTransaction();

        FragmentMenu fragmentMenu = new FragmentMenu();
        ft.replace(R.id.telaPrincipal,fragmentMenu);
        ft.commit();
    }

    private void insertJogador(String nome) {
        jogador = new Jogador();
        jogador.setJogadorName(nome);
        ConexaoDB db  = new ConexaoDB(this);

        IJogadorDao iJogadorDao = db.getDb().iJogadorDao();
        long jogadorid = iJogadorDao.insertJogador(jogador);
        jogador.setJogadorId(jogadorid);

        int itemId = radioGroup.getCheckedRadioButtonId();

        if (itemId != -1) {
            radioButton = findViewById(itemId);
            String dificuldade = radioButton.getText().toString();
            partida.setDificuldade(ConversorDeEnums.fromString(dificuldade));
        } else {
            Toast.makeText(this, R.string.nenhuma_dificuldade_selecionada, Toast.LENGTH_SHORT).show();
        }

        bundle = new Bundle();
        bundle.putSerializable("jogador", jogador);
        bundle.putSerializable("partida", partida);
    }

    @Override
    public void onJogarNovamenteClicked() {
        jogo();
    }

    @Override
    public void onVerPontuacaoClicked() {
        FragmentTransaction ft = fm.beginTransaction();

        FragmentListaPartida fragmentListaPartida = new FragmentListaPartida();
        ft.replace(R.id.telaPrincipal,fragmentListaPartida);
        ft.commit();
    }
}
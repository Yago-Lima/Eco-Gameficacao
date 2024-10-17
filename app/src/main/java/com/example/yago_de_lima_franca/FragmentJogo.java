package com.example.yago_de_lima_franca;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yago_de_lima_franca.dao.IJogadorDao;
import com.example.yago_de_lima_franca.dao.IpartidaDao;
import com.example.yago_de_lima_franca.dao.MyDatabase;
import com.example.yago_de_lima_franca.model.ConexaoDB;
import com.example.yago_de_lima_franca.model.Dificuldade;
import com.example.yago_de_lima_franca.model.Jogador;
import com.example.yago_de_lima_franca.model.Partida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentJogo extends Fragment {

    private GridLayout gridLayout;
    private TextView textViewTimer;
    private TextView textViewJogador;
    private TextView textViewPontuacao;
    private CountDownTimer countDownTimer;
    private IJogadorDao jogadorDao;
    private IpartidaDao ipartidaDao;
    private Double pontuacao;

    private List<Integer> imageList; // Usar apenas a lista
    private ImageView firstCard, secondCard;
    private boolean isFirstCardFlipped = false;
    private ImageView buttonFechar;
    private Partida partida;
    private Jogador jogador;
    ConexaoDB conexaoDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jogo, container, false);
        gridLayout = view.findViewById(R.id.gridLayout);
        textViewTimer = view.findViewById(R.id.textView_timer);
        buttonFechar = view.findViewById(R.id.buttonFechar);
        textViewJogador = view.findViewById(R.id.textViewJogador);
        textViewPontuacao = view.findViewById(R.id.textViewPontuacao);
        conexaoDB = new ConexaoDB(getContext());


        pontuacao = 0.0;
        Long tempo = 0L;
        textViewPontuacao.setText(String.valueOf(pontuacao));
        Bundle bundle = getArguments();

        if (bundle != null) {
            partida = (Partida) bundle.getSerializable("partida");
            if (partida != null){
                if (partida.getDificuldade() == Dificuldade.FACIL) tempo = 40000L;
                if (partida.getDificuldade() == Dificuldade.NORMAL) tempo = 30000L;
                if (partida.getDificuldade() == Dificuldade.DIFICIL) tempo = 20000L;
            }
            jogador = (Jogador) bundle.getSerializable("jogador");
            if (jogador != null) {
                textViewJogador.setText(jogador.getJogadorName());
            }
        }

        buttonFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharFragmento();
            }
        });

        countDownTimer = new CountDownTimer(tempo, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                textViewTimer.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));
                if (seconds == 10){
                    adiconarCorTimer(textViewTimer);
                }
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("00:00");
                salvarPartida();
                mostrarFimDeJogoDialog();
                fecharFragmento();
            }
        };

        countDownTimer.start();

        // Inicializa e embaralha as imagens
        initializeAndShuffleImages();

        // Adicionar as cartas ao GridLayout
        for (int i = 0; i < imageList.size(); i++) {
            ImageView card = new ImageView(getContext());
            card.setImageResource(R.drawable.design_sem_nome__5_); // Todas as cartas começam viradas para baixo
            card.setTag(imageList.get(i)); // Usa a tag para armazenar a imagem associada à carta
            card.setOnClickListener(this::onCardClicked);
            gridLayout.addView(card);
        }
        return view;
    }

    private void salvarPartida() {
        jogadorDao = conexaoDB.getDb().iJogadorDao();
        ipartidaDao = conexaoDB.getDb().ipartidaDao();
        partida.setPontuacao(pontuacao);
        partida.setJogador(jogador.getJogadorId());
        ipartidaDao.insertPartida(partida);
    }



    private void adiconarCorTimer(TextView textViewTimer) {
        int color = ContextCompat.getColor(getContext(), R.color.red);
        textViewTimer.setTextColor(color);
    }

    private void fecharFragmento() {
        getParentFragmentManager().beginTransaction().remove(FragmentJogo.this).commit();
    }

    private void initializeAndShuffleImages() {
        imageList = new ArrayList<>();
        // Adicionar imagens duplicadas para pares
        addImagesToList(R.drawable.arvore);
        addImagesToList(R.drawable.enchente);
        addImagesToList(R.drawable.fedor);
        addImagesToList(R.drawable.furacao);
        addImagesToList(R.drawable.lixeira_de_reciclagem);
        addImagesToList(R.drawable.salve_);

        Collections.shuffle(imageList); // Embaralha a lista
    }

    private void addImagesToList(int imageResource) {
        imageList.add(imageResource);
        imageList.add(imageResource); // Adiciona a imagem novamente para criar pares
    }

    public void onCardClicked(View view) {
        ImageView card = (ImageView) view;

        if (!isFirstCardFlipped) {
            // Primeira carta virada
            firstCard = card;
            firstCard.setImageResource((int) card.getTag());
            isFirstCardFlipped = true;
        } else {
            // Segunda carta virada
            secondCard = card;
            secondCard.setImageResource((int) card.getTag());

            // Verificar se as cartas são iguais
            if (firstCard.getTag().equals(secondCard.getTag())) {
                // Match! Deixar as cartas viradas
                pontuacao += 1.6;
                textViewPontuacao.setText(String.format("%.2f",pontuacao));
                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
            } else {
                // Sem match! Virar as cartas de volta após um breve atraso
                gridLayout.postDelayed(() -> {
                    firstCard.setImageResource(R.drawable.design_sem_nome__5_);
                    secondCard.setImageResource(R.drawable.design_sem_nome__5_);
                }, 500);
            }

            isFirstCardFlipped = false;
        }
    }
    private void mostrarFimDeJogoDialog() {
        FimDeJogoDialogFragment dialog = new FimDeJogoDialogFragment();
        dialog.setTargetFragment(FragmentJogo.this, 0); // Define o fragmento que será notificado
        dialog.show(getParentFragmentManager(), "FimDeJogoDialog");
    }
}

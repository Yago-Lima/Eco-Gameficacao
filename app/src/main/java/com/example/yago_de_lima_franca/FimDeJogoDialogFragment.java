package com.example.yago_de_lima_franca;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

public class FimDeJogoDialogFragment extends DialogFragment {

    public interface FimDeJogoDialogListener {
        void onJogarNovamenteClicked();
        void onVerPontuacaoClicked();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Fim de Jogo")
                .setMessage("O jogo terminou. O que você gostaria de fazer?")
                .setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Notifica a atividade para reiniciar o jogo
                        if (getActivity() instanceof FimDeJogoDialogListener) {
                            ((FimDeJogoDialogListener) getActivity()).onJogarNovamenteClicked();
                        }
                    }
                })
                .setNegativeButton("Ver Pontuação", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Notifica a atividade para mostrar a pontuação
                        if (getActivity() instanceof FimDeJogoDialogListener) {
                            ((FimDeJogoDialogListener) getActivity()).onVerPontuacaoClicked();
                        }
                    }
                });

        return builder.create();
    }
}

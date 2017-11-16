package com.felepo.tic_tac_toe;

import java.util.Random;

/**
 * Created by Fernando Poncio on 14/11/2017.
 */

public class Partida
{
    public final int dificultad;
    public int jugador;

    public Partida(int dificultad)
    {
        this.dificultad = dificultad;
        jugador = 1;
    }

    public void cambiarTurno()
    {
        jugador++;

        if( jugador > 2 )
        {
            jugador = 1;
        }
    }

    public int inteligenciaArtificial()
    {
        int casilla;

        Random numAleatorio = new Random();
        casilla = numAleatorio.nextInt(9);

        return casilla;
    }
}

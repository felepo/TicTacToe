package com.felepo.tic_tac_toe;

import java.util.Random;

/**
 * Created by Fernando Poncio on 14/11/2017.
 */

public class Partida
{
    public final int dificultad;
    public int jugador;
    private int casillasOcupadas[];

    public Partida(int dificultad)
    {
        this.dificultad = dificultad;
        jugador = 1;

        casillasOcupadas = new int[9];
        for( int i = 0; i < 9; i++ )
        {
            casillasOcupadas[i] = 0;
        }
    }

    public boolean comprobarCasilla(int numeroCasilla)
    {
        if( casillasOcupadas[numeroCasilla] != 0 )
        {
            return false;
        }
        else
        {
            casillasOcupadas[numeroCasilla] = jugador;
        }

        return true;
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

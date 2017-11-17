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
    private final int COMBINACIONES[][] = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

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

    public int cambiarTurno()
    {
        boolean empate = true;

        //Verificando las combinaciones de victoria del juego
        for( int i = 0; i < COMBINACIONES.length; i++ )
        {
            for( int pos : COMBINACIONES[i] )
            {
                //System.out.println("Valor en posición " + i + " " + casillasOcupadas[pos]);

                if( casillasOcupadas[pos] == 0 )
                {
                    empate = false;
                }
            }
        }

        if( empate )
        {
            return 3;
        }

        //Con esto se cambia el turno
        jugador++;

        if( jugador > 2 )
        {
            jugador = 1;
        }

        return 0;
    }

    public int inteligenciaArtificial()
    {
        int casilla;

        Random numAleatorio = new Random();
        casilla = numAleatorio.nextInt(9);

        return casilla;
    }
}

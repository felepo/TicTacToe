package com.felepo.tic_tac_toe;

import java.util.Random;

/**
 * Created by Fernando Poncio on 14/11/2017.
 */

public class Partida
{
    public final int dificultad;
    public int jugador;
    private int[] casillasOcupadas;
    private final int[][] COMBINACIONES = {{0,1,2},{3,4,5},{6,7,8},
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

    /* En cambiar turno se verifica si se llegó a un empate, si alguien ganó o si
       no ha pasado nada aún.
    */
    public int cambiarTurno()
    {
        boolean empate = true;
        boolean ultimoMovimiento = true;

        //Verificando las combinaciones de victoria del juego
        for( int i = 0; i < COMBINACIONES.length; i++ )
        {
            for( int pos : COMBINACIONES[i] )
            {
                //System.out.println("Valor en posición " + i + " " + casillasOcupadas[pos]);

                //Con esto se sabe si se ha ganado el juego o no
                if( casillasOcupadas[pos] != jugador )
                {
                    ultimoMovimiento = false;
                }

                if( casillasOcupadas[pos] == 0 )
                {
                    empate = false;
                }
            }   //Fin de for anidado

            if( ultimoMovimiento )
            {
                return jugador;
            }

            ultimoMovimiento = true;

        }   //Fin del for principal

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

    //Método para enriquecer la inteligencia del programa
    public int dosEnRaya(int jugadorEnTurno)
    {
        int casilla = -1;
        int conteoCasilla = 0;

        for( int i = 0; i < COMBINACIONES.length; i++ )
        {
            for( int pos : COMBINACIONES[i] )
            {
                if( casillasOcupadas[pos] == jugadorEnTurno )
                {
                    conteoCasilla++;
                }

                if( casillasOcupadas[pos] == 0 )
                {
                    casilla = pos;
                }
            }

            if( ( conteoCasilla == 2 ) && ( casilla != -1 ) )
            {
                return casilla;
            }

            casilla = -1;
            conteoCasilla = 0;
        }

        return -1;
    }

    public int inteligenciaArtificial()
    {
        int casilla;

        //Con esto la maquina busca ganar el juego
        casilla = dosEnRaya(2);
        if( casilla != -1 )
        {
            return casilla;
        }

        if( dificultad > 0 )
        {
            //Con esto la maquina impide que el jugador 1 gane
            casilla = dosEnRaya(1);
            if( casilla != -1 )
            {
                return casilla;
            }
        }

        //De esta forma, el juego se hace imposible de ganar
        if( dificultad == 2 )
        {
            if( casillasOcupadas[0] == 0 ) return 0;
            if( casillasOcupadas[2] == 0 ) return 2;
            if( casillasOcupadas[6] == 0 ) return 6;
            if( casillasOcupadas[8] == 0 ) return 8;
        }

        Random numAleatorio = new Random();
        casilla = numAleatorio.nextInt(9);

        return casilla;
    }
}

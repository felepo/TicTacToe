package com.felepo.tic_tac_toe;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int jugadores;
    private int CASILLAS[];
    private Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se inicia el arreglo de CASILLAS y se almacenan los id's
        CASILLAS = new int[9];

        CASILLAS[0] = R.id.a1;
        CASILLAS[1] = R.id.a2;
        CASILLAS[2] = R.id.a3;

        CASILLAS[3] = R.id.b1;
        CASILLAS[4] = R.id.b2;
        CASILLAS[5] = R.id.b3;

        CASILLAS[6] = R.id.c1;
        CASILLAS[7] = R.id.c2;
        CASILLAS[8] = R.id.c3;
    }

    public void jugar(View vista)
    {
        //Antes de empezar a jugar:

        //PASO 1: Limpiar el tablero
        ImageView imagen;

            //Asignar la imagen de casilla a cada una de las casillas del array.
        for( int casilla : CASILLAS )
        {
            imagen = (ImageView) findViewById(casilla);
            imagen.setImageResource(R.drawable.casilla);
        }

        //PASO 2: Identificar el número de jugadores
        jugadores = 1;

            //Si el botón de dos jugadores es presionado, la variable jugadores se reescribe a 2.
        if( vista.getId() == R.id.dosJugadores )
        {
            jugadores = 2;
        }

        //PASO 3: Detectar la dificultad seleccionada.
        RadioGroup grupoDificultad = (RadioGroup) findViewById(R.id.grupoDificultad);
        int id = grupoDificultad.getCheckedRadioButtonId();

        int dificultad = 0;
        if( id == R.id.medio )
        {
            dificultad = 1;
        }
        if (id == R.id.dificil)
        {
            dificultad = 2;
        }

        //PASO 4: Inhabilitar los botones mientras dure la partida
        partida = new Partida(dificultad); //Se crea una nueva partida con dificultad

        ((Button) findViewById(R.id.unJugador)).setEnabled(false);
        ((RadioGroup) findViewById(R.id.grupoDificultad)).setAlpha(0);
        ((Button) findViewById(R.id.dosJugadores)).setEnabled(false);
    }

    public void toque(View casillaPresionada)
    {
        //Se verifica si la partida está iniciada
        if( partida == null )
        {
            return;
        }

        int casilla = 0;

        //Para saber el número de la casilla que fue presionada
        for( int i = 0; i < 9; i++ )
        {
            if( CASILLAS[i] == casillaPresionada.getId() )
            {
                casilla = i;

                break;
            }
        }

        //Código para mostrar texto en la pantalla del movil.
        /*
        Toast toast = Toast.makeText(this, "Casilla " + casilla + " pulsada",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        */

        if( partida.comprobarCasilla(casilla) == false )
        {
            return;
        }

        //Se dibuja en la casilla seleccionada el simbolo del jugador 1
        marcarCasilla(casilla);

        partida.cambiarTurno();

        //Se dibuja en la casilla seleccionada el simbolo del jugador 2
        casilla = partida.inteligenciaArtificial();

        while( partida.comprobarCasilla(casilla) != true )
        {
            casilla = partida.inteligenciaArtificial();
        }

        marcarCasilla(casilla);
        partida.cambiarTurno();
    }

    private void marcarCasilla(int casilla)
    {
        ImageView imagen;
        imagen = (ImageView) findViewById(CASILLAS[casilla]);

        if( partida.jugador == 1 )
        {
            imagen.setImageResource(R.drawable.circulo);
        }
        else
        {
            imagen.setImageResource(R.drawable.equis);
        }
    }
}

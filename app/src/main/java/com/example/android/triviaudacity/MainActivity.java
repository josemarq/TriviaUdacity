package com.example.android.triviaudacity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.lang.UCharacter.toUpperCase;
import static com.example.android.triviaudacity.R.styleable.CompoundButton;

public class MainActivity extends AppCompatActivity {
    public int pregunta = 1;
    public int resultado = 0;
    public int puntos = 0;
    private ProgressBar firstBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void siguiente(View view) {

        // Clear Check or Radio ButtonGroup
        RadioGroup OptionsGroup = (RadioGroup) findViewById(R.id.options);
        int radioButtonID = OptionsGroup.getCheckedRadioButtonId();
        View RadioButton = OptionsGroup.findViewById(radioButtonID);
        int idx = OptionsGroup.indexOfChild(RadioButton);
        OptionsGroup.clearCheck();

        //Get the commons Views
        ImageView ImageView = (ImageView) findViewById(R.id.thumbnail);
        TextView TextView = (TextView) findViewById(R.id.pregunta);
        TextView TextOpcion1 = (TextView) findViewById(R.id.option1);
        TextView TextOpcion2 = (TextView) findViewById(R.id.option2);
        TextView TextOpcion3 = (TextView) findViewById(R.id.option3);
        firstBar = (ProgressBar)findViewById(R.id.firstBar);



        if (pregunta == 0) {
            TextView.setVisibility(View.VISIBLE);
            firstBar.setVisibility(View.VISIBLE);
            firstBar.setProgress(0);
            TextView TitleTextView = (TextView) findViewById(R.id.title);
            TitleTextView.setVisibility(View.GONE);
            Button botonSiguiente = (Button) findViewById(R.id.siguiente);
            botonSiguiente.setText("Siguiente");
            View LayoutOpciones = findViewById(R.id.options);
            LayoutOpciones.setVisibility(View.VISIBLE);
            puntos = 0;
            resultado = 0;
            ImageView.setImageResource(R.drawable.atomiun);
            TextView.setText("Cúal es el nombre del monumento que ves en la imagen superior:");
            TextOpcion1.setText("Tour Eiffel");
            TextOpcion2.setText("Microbium");
            TextOpcion3.setText("Atomium");
            pregunta = pregunta + 1;
            //Toast toast = Toast.makeText(getApplicationContext(), "Primera Pregunta Resultado = " + resultado, Toast.LENGTH_SHORT);
            //toast.show();
        } else {

            if (pregunta == 1) {
                firstBar.setProgress(20);
                puntos = corregirRadioButton(idx, pregunta);
                resultado = resultado + puntos;
                ImageView.setImageResource(R.drawable.pis);
                TextView.setText("Esta pequeña estatua lleva el nombre de:");
                TextOpcion1.setText("Little Pis");
                TextOpcion2.setText("Manneken Pis");
                TextOpcion3.setText("Pettit Boy Pee");
                pregunta = pregunta + 1;
                //Toast toast = Toast.makeText(getApplicationContext(), "Resultado Atomium en Manneken: " + resultado, Toast.LENGTH_SHORT);
                //toast.show();
            } else {
                if (pregunta == 2) {
                    firstBar.setProgress(40);
                    puntos = corregirRadioButton(idx, pregunta);
                    ImageView.setImageResource(R.drawable.frites);
                    TextView.setText("Las Patatas Fritas son un invento:");
                    TextOpcion1.setText("Belga");
                    TextOpcion2.setText("Francés");
                    TextOpcion3.setText("Español");
                    pregunta = pregunta + 1;
                    resultado = resultado + puntos;
                    //Toast toast = Toast.makeText(getApplicationContext(), "Resultado Manenken en Frites: " + resultado, Toast.LENGTH_SHORT);
                    //toast.show();
                } else {
                    if (pregunta == 3) {
                        firstBar.setProgress(60);
                        puntos = corregirRadioButton(idx, pregunta);
                        resultado = resultado + puntos;
                        ImageView.setImageResource(R.drawable.bruselas2);
                        TextView.setText("Qué productos puedes encontrar fácilmente en las calles de Bruselas:");
                        View LayoutOpciones = findViewById(R.id.options);
                        LayoutOpciones.setVisibility(View.GONE);
                        View LayoutCheckBoxes = findViewById(R.id.checkboxes);
                        LayoutCheckBoxes.setVisibility(View.VISIBLE);
                        pregunta = pregunta + 1;
                        //Toast toast = Toast.makeText(getApplicationContext(), "Resultado Fites en Wafles: " + resultado, Toast.LENGTH_SHORT);
                        //toast.show();
                    } else {
                        if (pregunta == 4) {
                            firstBar.setProgress(80);
                            CheckBox CheckBox1 = (CheckBox) findViewById(R.id.check1);
                            boolean perros = CheckBox1.isChecked();
                            CheckBox CheckBox2 = (CheckBox) findViewById(R.id.check2);
                            boolean chocolate = CheckBox2.isChecked();
                            CheckBox CheckBox3 = (CheckBox) findViewById(R.id.check3);
                            boolean wafles = CheckBox3.isChecked();
                            puntos = corregirCheckBox(perros, chocolate, wafles);
                                //RESET CHECKBOX STATES
                                if(CheckBox1.isChecked()){
                                    CheckBox1.toggle();
                                }
                                if(CheckBox2.isChecked()){
                                    CheckBox2.toggle();
                                }
                                if(CheckBox3.isChecked()){
                                    CheckBox3.toggle();
                                }
                            resultado = resultado + puntos;
                            ImageView.setImageResource(R.drawable.miluo5);
                            View layoutRespuestaTexto = findViewById(R.id.respuestaTexto);
                            layoutRespuestaTexto.setVisibility(View.VISIBLE);
                            TextView.setText("Como se llama el perro de Tin-Tin:");
                            pregunta = pregunta + 1;
                            View layoutOpciones = findViewById(R.id.checkboxes);
                            layoutOpciones.setVisibility(View.GONE);
                            //Toast toast = Toast.makeText(getApplicationContext(), "Resultado Pregunta Wafles en Pregunta : " + resultado, Toast.LENGTH_SHORT);
                            //toast.show();
                        } else {
                            if (pregunta == 5) {
                                firstBar.setProgress(100);
                                TextView ResponseTextview = (TextView) findViewById(R.id.answer);
                                String respuesta = ResponseTextview.getText().toString();
                                respuesta = toUpperCase(respuesta).trim();
                                ResponseTextview.setText("");
                                Log.v("Answer", respuesta);

                                if (respuesta.equals("MILOU")) {
                                    puntos = 1;
                                } else {
                                    puntos = 0;
                                }
                                resultado = resultado + puntos;
                                Toast toast = Toast.makeText(getApplicationContext(), "Resultado Final: " + resultado + "/5", Toast.LENGTH_SHORT);
                                toast.show();

                                // FINAL DE LA TRIVIA
                                TextView TitleTextView = (TextView) findViewById(R.id.title);
                                TitleTextView.setVisibility(View.VISIBLE);
                                if (resultado == 5) {
                                    ImageView.setImageResource(R.drawable.smiley);
                                    TitleTextView.setText("Wow!. You have made:\n" + resultado + "/5\nYou are a Belgium Expert!");
                                } else {
                                    if (resultado > 1) {
                                        TitleTextView.setText("Yuuuujuuuuu! You have made:\n" + resultado +"/5");
                                        ImageView.setImageResource(R.drawable.smiley);
                                    } else {
                                        TitleTextView.setText("Oh no!. You have made:\n" + resultado + "/5\nTry again");
                                        ImageView.setImageResource(R.drawable.sad);
                                    }
                                }
                                pregunta = 0;
                                TextView.setVisibility(View.GONE);
                                View layoutRespuesta = findViewById(R.id.respuestaTexto);
                                layoutRespuesta.setVisibility(View.GONE);
                                Button botonSiguiente = (Button) findViewById(R.id.siguiente);
                                botonSiguiente.setText("Restart");
                                //Log.v("Resultado Final", resultado + "");
                            }
                        }
                    }
                }
            }

        }
    }

    //Corrige las preguntas de RadioButton 1, 2, y 3
    public int corregirRadioButton(int idx, int pregunta) {
        Log.v("Pregunta " + pregunta, idx + "");
        if ((pregunta == 1) && (idx == 2)) {
            puntos = 1;
        } else if ((pregunta == 2) && (idx == 1)) {
            puntos = 1;
        } else if ((pregunta == 3) && (idx == 0)) {
            puntos = 1;
        } else {
            puntos = 0;
        }
        return puntos;
    }

    //Corrige las preguntas de CheckBox 4
    public int corregirCheckBox(boolean perros, boolean chocolate, boolean wafles) {
        Log.v("CheckBoxes", perros + "\n" + chocolate + "\n" + wafles);
        if ((chocolate) && (wafles) && (!(perros))) {
            puntos = 1;
        } else {
            puntos = 0;
        }
        return puntos;
    }
}

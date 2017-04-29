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
            botonSiguiente.setText(getString(R.string.button_next));
            View LayoutOpciones = findViewById(R.id.options);
            LayoutOpciones.setVisibility(View.VISIBLE);
            puntos = 0;
            resultado = 0;
            ImageView.setImageResource(R.drawable.atomiun);
            TextView.setText(getString(R.string.pregunta1));
            TextOpcion1.setText(getString(R.string.respuesta1_1));
            TextOpcion2.setText(getString(R.string.respuesta2_1));
            TextOpcion3.setText(getString(R.string.respuesta3_1));
            pregunta = pregunta + 1;

        } else {

            if (pregunta == 1) {
                firstBar.setProgress(20);
                puntos = corregirRadioButton(idx, pregunta);
                resultado = resultado + puntos;
                ImageView.setImageResource(R.drawable.pis);
                TextView.setText(getString(R.string.pregunta2));
                TextOpcion1.setText(getString(R.string.respuesta1_2));
                TextOpcion2.setText(getString(R.string.respuesta2_2));
                TextOpcion3.setText(getString(R.string.respuesta3_2));
                pregunta = pregunta + 1;

            } else {
                if (pregunta == 2) {
                    firstBar.setProgress(40);
                    puntos = corregirRadioButton(idx, pregunta);
                    ImageView.setImageResource(R.drawable.frites);
                    TextView.setText(getString(R.string.pregunta3));
                    TextOpcion1.setText((getString(R.string.respuesta1_3)));
                    TextOpcion2.setText((getString(R.string.respuesta2_3)));
                    TextOpcion3.setText((getString(R.string.respuesta3_3)));
                    pregunta = pregunta + 1;
                    resultado = resultado + puntos;

                } else {
                    if (pregunta == 3) {
                        firstBar.setProgress(60);
                        puntos = corregirRadioButton(idx, pregunta);
                        resultado = resultado + puntos;
                        ImageView.setImageResource(R.drawable.bruselas2);
                        TextView.setText(getString(R.string.pregunta4));
                        View LayoutOpciones = findViewById(R.id.options);
                        LayoutOpciones.setVisibility(View.GONE);
                        View LayoutCheckBoxes = findViewById(R.id.checkboxes);
                        LayoutCheckBoxes.setVisibility(View.VISIBLE);
                        pregunta = pregunta + 1;

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
                            TextView.setText(getString(R.string.pregunta5));
                            pregunta = pregunta + 1;
                            View layoutOpciones = findViewById(R.id.checkboxes);
                            layoutOpciones.setVisibility(View.GONE);

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
                                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.finish_counter)+ " " + resultado + getString(R.string.of), Toast.LENGTH_SHORT);
                                toast.show();

                                // FINAL DE LA TRIVIA
                                TextView TitleTextView = (TextView) findViewById(R.id.title);
                                TitleTextView.setVisibility(View.VISIBLE);
                                if (resultado == 5) {
                                    ImageView.setImageResource(R.drawable.smiley);
                                    TitleTextView.setText(getString(R.string.wow) + ". " + getString(R.string.finish_counter) + " " + resultado + getString(R.string.of) + "\n" + getString(R.string.experto));
                                } else {
                                    if (resultado > 1) {
                                        TitleTextView.setText(getString(R.string.yuhu) + ".\n" + getString(R.string.finish_counter)+ " " + resultado + getString(R.string.of));
                                        ImageView.setImageResource(R.drawable.smiley);
                                    } else {
                                        TitleTextView.setText(getString(R.string.ohno) + ". " + getString(R.string.finish_counter)+ " " + resultado + getString(R.string.of) + "\n" + getString(R.string.try_again));
                                        ImageView.setImageResource(R.drawable.sad);
                                    }
                                }
                                pregunta = 0;
                                TextView.setVisibility(View.GONE);
                                View layoutRespuesta = findViewById(R.id.respuestaTexto);
                                layoutRespuesta.setVisibility(View.GONE);
                                Button botonSiguiente = (Button) findViewById(R.id.siguiente);
                                botonSiguiente.setText(getString(R.string.button_restart));
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

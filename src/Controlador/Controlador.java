/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Decimal;
import Vista.CalculadoraIEEE;
import javax.swing.JOptionPane;

/**
 *
 * @author yefre
 */
public class Controlador {

    private Decimal modelo;
    private CalculadoraIEEE vista;

    public Controlador(Decimal modelo, CalculadoraIEEE vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void convertirDecimalAIEEE() {

        String numero = vista.getTxtDecimal().getText();

        String[] opciones = {"32 bits", "64 bits"};
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona el formato IEEE 754:",
                "Conversión IEEE 754",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        String resultado = "";

        try {
            if (seleccion == 0) {
                resultado = modelo.toIEEE754(Float.parseFloat(numero));
            } else if (seleccion == 1) {
                resultado = modelo.toIEEE754_2(Double.parseDouble(numero));
            } else {
                return;
            }

            JOptionPane.showMessageDialog(null, "Resultado IEEE 754:\n" + resultado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void convertirIEEEADecimal() {
        String binario = vista.getTxtIEEE().getText();
        if (binario.length() == 32) {
            float resultado = modelo.toDecimal(binario);
            JOptionPane.showMessageDialog(null, resultado);
        } else {
            JOptionPane.showMessageDialog(null, "Debe tener 32 bits");
            limpiar();
        }
    }

    public void limpiar() {
        vista.getTxtDecimal().setText("");
        vista.getTxtIEEE().setText("");
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yefre
 */
public class Decimal {

    public void Decimal() {
    }

    ;

    public String toIEEE754(float num) {
        int signo;
        if (num < 0) {
            signo = 1;
            num = -num;
        } else {
            signo = 0;
        }

        int exponente = 127; // 32 bits
        double mantisa = num;

        while (mantisa >= 2) {
            mantisa = mantisa / 2;
            exponente = exponente + 1;
        }
        while (mantisa < 1 && mantisa != 0) {
            mantisa = mantisa * 2;
            exponente = exponente - 1;
        }

        mantisa = mantisa - 1;
        int mantisaBits = 0;
        int i = 0;
        while (i < 23) {
            mantisa = mantisa * 2;
            if (mantisa >= 1) {
                mantisaBits = (mantisaBits << 1) | 1;
                mantisa = mantisa - 1;
            } else {
                mantisaBits = mantisaBits << 1;
            }
            i = i + 1;
        }

        int exponenteFinal = exponente + 127;
        String ieee754 = String.format("%1d%8s%23s", signo, Integer.toBinaryString(exponenteFinal), Integer.toBinaryString(mantisaBits));
        String rta = ieee754.replace(' ', '0');
        return rta;
    }

    public String toIEEE754_2(double num) {
        int signo;
        if (num < 0) {
            signo = 1;
            num = -num;
        } else {
            signo = 0;
        }

        int exponente = 1023; // 64 bits
        double mantisa = num;

        while (mantisa >= 2) {
            mantisa = mantisa / 2;
            exponente = exponente + 1;
        }
        while (mantisa < 1 && mantisa != 0) {
            mantisa = mantisa * 2;
            exponente = exponente - 1;
        }

        mantisa = mantisa - 1;
        long mantisaBits = 0;
        int i = 0;
        while (i < 52) {
            mantisa = mantisa * 2;
            if (mantisa >= 1) {
                mantisaBits = (mantisaBits << 1) | 1;
                mantisa = mantisa - 1;
            } else {
                mantisaBits = mantisaBits << 1;
            }
            i = i + 1;
        }

        int exponenteFinal = exponente + 1023;
        String ieee754 = String.format("%1d%11s%52s", signo, Integer.toBinaryString(exponenteFinal), Long.toBinaryString(mantisaBits));
        String rta = ieee754.replace(' ', '0');
        return rta;
    }

    public float toDecimal(String binario) {
        int signo;
        if (binario.charAt(0) == '1') {
            signo = -1;
        } else {
            signo = 1;
        }

        int exponente = Integer.parseInt(binario.substring(1, 9), 2) - 127;
        String mantisaBin = binario.substring(9);

        double mantisa = 1.0;
        int j = 0;
        while (j < mantisaBin.length()) {
            if (mantisaBin.charAt(j) == '1') {
                mantisa = mantisa + Math.pow(2, -(j + 1));
            }
            j = j + 1;
        }
        float rta = (float) (signo * mantisa * Math.pow(2, exponente));
        return rta;
    }
}

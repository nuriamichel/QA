package calcularFechaSiguiente;

import java.util.Arrays;

public class CalcularFechaSiguiente {

    public String fechaSiguiente(int dia, String mes, int anio, int hora, int minuto, int segundo) {
        String message;
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        boolean mesesDeTreintaDias = (mes.equals("Abril") || mes.equals("Junio") || mes.equals("Septiembre") || mes.equals("Noviembre"));

        if (dia < 1 || dia > 31) {
            message = "Dia no valido";
        } else if (mes.equals("Febrero") && (dia == 30 || dia == 31)) {
            message = "Dia no valido";
        } else if (mes.equals("Febrero") && dia == 29 && anio % 4 != 0) {
            message = "Dia no valido";
        } else if (dia == 31 && (mesesDeTreintaDias)) {
            message = "Dia no valido";
        } else if (!(Arrays.asList(meses).contains(mes))) {
            message = "Mes no valido";
        } else if (anio < 0) {
            message = "Anio no valido";
        } else if (hora < 0 || hora > 23) {
            message = "Hora no valida";
        } else if (minuto < 0 || minuto > 59) {
            message = "Minuto no valido";
        } else if (segundo < 0 || segundo > 59) {
            message = "Segundo no valido";
        } else if (segundo != 59) {
            int c = segundo + 1;
            message = dia + " " + mes + " " + anio + " " + hora + ":" + minuto + ":" + c;
        } else if (minuto != 59) {
            int c = minuto + 1;
            message = dia + " " + mes + " " + anio + " " + hora + ":" + c + ":0";
        } else if (hora != 23) {
            int c = hora + 1;
            message = dia + " " + mes + " " + anio + " " + c + ":0:0";
        } else if (dia < 28) {
            int c = dia + 1;
            message = c + " " + mes + " " + anio + " 0:0:0";
        } else if (dia == 28 && mes.equals("Febrero")) {
            if (anio % 4 == 0) {
                message = "29 Febrero " + anio + " 0:0:0";
            } else {
                message = "1 Marzo " + anio + " 0:0:0";
            }
        } else if (dia == 28) {
            int c = dia + 1;
            message = c + " " + mes + " " + anio + " 0:0:0";
        } else if (dia == 29 && mes.equals("Febrero")) {
            message = "1 Marzo " + anio + " 0:0:0";
        } else if (dia == 29) {
            int c = dia + 1;
            message = c + " " + mes + " " + anio + " 0:0:0";
        } else if (dia == 30 && (mesesDeTreintaDias)) {
            int c = 0;
            for (int i = 0; i < meses.length; i++) {
                if (meses[i].equals(mes)) {
                    c = i + 1;
                }
            }
            message = "1 " + meses[c] + " " + anio + " 0:0:0";
        } else if (dia == 30) {
            int c = dia + 1;
            message = c + " " + mes + " " + anio + " 0:0:0";
        } else if (!mes.equals("Diciembre")) {
            int c = 0;
            for (int i = 0; i < meses.length; i++) {
                if (meses[i].equals(mes)) {
                    c = i + 1;
                }
            }
            message = "1 " + meses[c] + " " + anio + " 0:0:0";
        } else {
            int c = anio + 1;
            message = "1 Enero " + c + " 0:0:0";
        }
        return message;
    }

}


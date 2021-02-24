package calcularSaldo;

public class Cajero2 {
    private BDUtil2 dbUtil2 = new BDUtil2();
    private ClientDB2 clientDB2 = new ClientDB2();
    private String msg = "";
    private int saldo;

    public Cajero2(int saldo) {
        this.saldo = saldo;
    }

    public String getCash(int ci, int amount) {

        if (clientDB2.isConnectionSuccessfully("mysql")) {
            if (amount > 0 && amount <= this.saldo) {
                int newSaldo = this.saldo - amount;
                boolean isUpdated = dbUtil2.updateSaldo(ci, newSaldo);
                msg = isUpdated ? "Usted esta sacando la cantidad de " + amount + " y tiene en saldo " + newSaldo : "Actualizacion Incorrecta, Intente Nuevamente";
            } else if (amount <= 0) {
                msg = "Amount No Valido";
            } else {
                msg = "Usted no tiene suficiente saldo";
            }
        } else {
            msg = "Conexion a BD no fue satisfactoria";
        }
        return msg;
    }
}

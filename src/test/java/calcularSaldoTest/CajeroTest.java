package calcularSaldoTest;

import calcularSaldo.BDUtil;
import calcularSaldo.Cajero;
import calcularSaldo.ClientDB;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@RunWith(value= Parameterized.class)
public class CajeroTest {

    private int ci;
    private int saldo;
    private int cantidad;
    private boolean isConnected;
    private boolean isUpdated;
    private String expectedResult;


    public CajeroTest(int ci, int saldo, int cantidad, boolean isConnected, boolean isUpdated, String expectedResult) {
        this.ci = ci;
        this.saldo = saldo;
        this.cantidad = cantidad;
        this.isConnected = isConnected;
        this.isUpdated = isUpdated;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData(){
        List<Object[]> objects= new ArrayList<>();

        objects.add(new Object[]{698999,500,1,true,true,"Usted esta sacando la cantidad de 1 y tiene en saldo 499"});
        objects.add(new Object[]{698999,500,2,true,true,"Usted esta sacando la cantidad de 2 y tiene en saldo 498"});
        objects.add(new Object[]{698999,500,100,true,true,"Usted esta sacando la cantidad de 100 y tiene en saldo 400"});
        objects.add(new Object[]{698999,500,500,true,true,"Usted esta sacando la cantidad de 500 y tiene en saldo 0"});
        objects.add(new Object[]{698999,500,0,true,true,"Amount No Valido"});
        objects.add(new Object[]{698999,500,-10,true,true,"Amount No Valido"});
        objects.add(new Object[]{698999,500,1000,true,true,"Usted no tiene suficiente saldo"});

        objects.add(new Object[]{698999,500,1,false,true,"Conexion a BD no fue satisfactoria"});
        objects.add(new Object[]{698999,500,1,true,false,"Actualizacion Incorrecta, Intente Nuevamente"});


        return objects;
    }

    //PASO 2
    BDUtil bdUtilMocked=Mockito.mock(BDUtil.class);
    ClientDB clientDBMocked=Mockito.mock(ClientDB.class);

    @Test
    public void verify_calculate_saldo(){
        // Paso 3

        Mockito.when(bdUtilMocked.updateSaldo(this.ci,this.saldo-this.cantidad)).thenReturn(this.isUpdated);
        Mockito.when(clientDBMocked.isConnectionSuccessfully("mysql")).thenReturn(this.isConnected);

        // Paso 4

        Cajero cajero=new Cajero(this.saldo,bdUtilMocked,clientDBMocked);
        String actualResult= cajero.getCash(this.ci,this.cantidad);

        Assert.assertEquals("ERROR! ",this.expectedResult,actualResult);
    }

}


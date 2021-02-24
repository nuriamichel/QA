package calcularSaldoTest;

import calcularSaldo.BDUtil2;
import calcularSaldo.Cajero2;
import calcularSaldo.ClientDB2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Parameterized.class)
@PrepareForTest({BDUtil2.class, ClientDB2.class})

public class CajeroTest2 {
    @Parameterized.Parameter(0)
    public int ci;
    @Parameterized.Parameter(1)
    public int saldo;
    @Parameterized.Parameter(2)
    public int cantidad;
    @Parameterized.Parameter(3)
    public boolean isConnected;
    @Parameterized.Parameter(4)
    public boolean isUpdated;
    @Parameterized.Parameter(5)
    public String expectedResult;


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



    @Test
    public void verify_calculate_saldo(){
        // Paso 3
        PowerMockito.mockStatic(BDUtil2.class);
        PowerMockito.mockStatic(ClientDB2.class);

        // Paso 4

        Mockito.when(BDUtil2.updateSaldo(this.ci,this.saldo-this.cantidad)).thenReturn(this.isUpdated);
        Mockito.when(ClientDB2.isConnectionSuccessfully("mysql")).thenReturn(this.isConnected);


        Cajero2 cajero2=new Cajero2(this.saldo);
        String actualResult= cajero2.getCash(this.ci,this.cantidad);

        Assert.assertEquals("ERROR! ",this.expectedResult,actualResult);
    }

}


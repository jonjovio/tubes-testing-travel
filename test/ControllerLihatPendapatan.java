
import Controller.ControllerA_Pendapatan;
import Model.Seat;
import Model.Transaksi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonjovio
 */
public class ControllerLihatPendapatan {
    private ControllerA_Pendapatan clihatPendapatan;
    
    @Before
    public void setUp() {
        clihatPendapatan = new ControllerA_Pendapatan();
    }
    
    
    @Test
    public void testHitungPendapatan() throws ParseException {
        int idTiket = 1;
        ArrayList<Transaksi> trans = clihatPendapatan.cariPendapatan(4, "2024");
        
        double result = clihatPendapatan.hitungPendapatan(trans);
        double expected = 496000;
        assertEquals(expected, result, 1.0);

    }
    
}

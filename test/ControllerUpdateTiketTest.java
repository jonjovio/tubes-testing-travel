
import Controller.ControllerA_updateTiket;
import Model.Tiket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;
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
public class ControllerUpdateTiketTest {
    
    private ControllerA_updateTiket cUpdateTiket;
    
    @Before
    public void setUp() {
        cUpdateTiket = new ControllerA_updateTiket();
    }
    
    @Test
    public void testAddTiketBenar() throws ParseException {
        Tiket t = new Tiket();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2024-04-25");
        t.setDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        t.setJam("12.00");
        t.setHarga(80000);
        t.getMobil().setIdMobil(1);
        t.getRute().setIdRute(5);
        
        boolean result = cUpdateTiket.addTiket(t);
        assertTrue(result);
    }
    
    @Test
    public void testAddTiketSalah() throws ParseException {
        Tiket t = new Tiket();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2024-04-25");
        t.setDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        t.setJam("12.00");
        t.setHarga(80000);
        t.getMobil().setIdMobil(1);
        
        boolean result = cUpdateTiket.addTiket(t);
        assertFalse(result);
    }
    
    @Test
    public void testDeleteTiket() throws ParseException {
        Tiket t = new Tiket();
        t.setIdTiket(4);
        
        boolean result = cUpdateTiket.deleteTiket(t);
        assertTrue(result);
    }
    
    @Test
    public void testUpdateTiket() throws ParseException {
        Tiket t = new Tiket();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2024-04-25");
        t.setDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        t.setJam("14.00");
        t.setHarga(80000);
        t.getMobil().setIdMobil(1);
        t.getRute().setIdRute(5);
        
        boolean result = cUpdateTiket.updateTiket(t, 5);
        assertTrue(result);
    }
    
    
}

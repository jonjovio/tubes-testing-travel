
import Controller.Controller_tiket;
import Model.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class ControllerTiketTest {
    private Controller_tiket cTiket;
    
    @Before
    public void setUp() {
        cTiket = new Controller_tiket();
    }

    @Test
    public void testGetKota() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Bandung");
        expected.add("Jakarta");
        expected.add("Depok");
        expected.add("Tanggerang");
        expected.add("Bekasi");
        ArrayList<String> result = cTiket.getKota();
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetRute() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("5 - Jakarta-Bandung - van");
        ArrayList<String> result = cTiket.getRute("Jakarta", "2024-04-24");
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetTiket() throws ParseException {
        ArrayList<Tiket> expected = new ArrayList<>();
        Tiket t = new Tiket();
        t.setIdTiket(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2024-04-24");
        t.setDate(date);
        t.setJam("12.00");
        t.setHarga(80000);
        t.getMobil().setJenisMobil("van");
        t.getMobil().setBanyakKursi(6);
        t.getRute().setTujuan(1);
        t.getRute().setKeberangkatan(2);
        expected.add(t);
        ArrayList<Tiket> result = cTiket.getTiket("Jakarta", "2024-04-24", 5, "van", "12.00");
        assertEquals(expected, result);
    }
    
    @Test
    public void testSetTransaksiBenar() throws ParseException {
        int idTiket = 1;
        Transaksi trans = new Transaksi();
        Seat seat = new Seat();
        trans.setCaraPembayaran("BCA");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2024-04-23");
        trans.setDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        trans.setRefund(false);
        trans.setTotalPembayaran(80000);
        trans.getMember().setIdUser(1);
        ArrayList<Integer> s = new ArrayList<>();
        s.add(1);
        seat.setSeat(s);
        
        boolean result = cTiket.setTransaksi(idTiket, trans, seat);
        assertTrue(result);
    }
    
    @Test
    public void testSetTransaksiSalah() throws ParseException {
        int idTiket = 1;
        Transaksi trans = new Transaksi();
        Seat seat = new Seat();
        trans.setCaraPembayaran("BCA");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2024-04-23");
        trans.setDate(java.sql.Date.valueOf(simpleDateFormat.format(date)));
        trans.setRefund(false);
        trans.setTotalPembayaran(80000);
        ArrayList<Integer> s = new ArrayList<>();
        s.add(1);
        seat.setSeat(s);
        
        boolean result = cTiket.setTransaksi(idTiket, trans, seat);
        assertFalse(result);
    }
    
    
    @Test
    public void testPembatalanTiket() throws ParseException {
        int idTransaksi = 18; //HARUS DI SETTING
        
        boolean result = cTiket.setPembatalanTiket(idTransaksi);
        assertTrue(result);
    }
    
    
//    @Test
//    public void testPembatalanTiketSalah() throws ParseException {
//        int idTransaksi = 1;
//        
//        boolean result = cTiket.setPembatalanTiket(idTransaksi);
//        assertTrue(result);
//    }
    
}

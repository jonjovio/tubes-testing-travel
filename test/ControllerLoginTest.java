
import Controller.Controller_user;
import Model.Member;
import static org.junit.Assert.*;
import org.junit.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonjovio
 */
public class ControllerLoginTest {

    private Controller_user cUser;
    
    @Before
    public void setUp() {
        cUser = new Controller_user();
    }

    @Test
    public void testSuccessfulLogin() {
        String result = cUser.checkLogin("jovi@gmail.com", "jovi");
        String expected = "admin"; 
        assertEquals(expected, result);
    }

    @Test
    public void testFailedLogin() {
        String result = cUser.checkLogin("jovi@gmail.com", "jov");
        String expected = "gaada"; 
        assertEquals(expected, result);
    }
    
    @Test
    public void testCekEmailAda() {
        Member m = new Member();
        m.setEmail("gibran@gmail.com");
        boolean result = cUser.checkEmail(m);
        assertFalse(result);
    }
    
    @Test
    public void testInsertNewMemberBenar() {
        Member m = new Member();
        m.setFirstName("jodi");
        m.setEmail("jodi@gmail.com");
        m.setPassword("jodi");
        m.setPhoneNumber("0928348324");
        boolean result = cUser.insertNewMember(m);
        assertTrue(result);
    }
    
    @Test
    public void testInsertNewMemberSalah() {
        Member m = new Member();
        m.setFirstName("sdawds");
        m.setPassword("dsa");
        m.setPhoneNumber("213531413");
        boolean result = cUser.insertNewMember(m);
        assertFalse(result);
    }

}

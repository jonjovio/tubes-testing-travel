/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Admin extends User{
    private static Admin adminInst;
    private String adminName;

    public Admin() {
    }

    public Admin(String adminName, int idUser, String email, String password, Model.UserType UserType) {
        super(idUser, email, password, UserType);
        this.adminName = adminName;
    }
    
    public static Admin getAdminInst(){
        if(adminInst==null){
            adminInst = new Admin();
            System.out.println("Admin instance created for the first time.");
        }
        return adminInst;
    }
    
    public void deleteAdminInstance(){
        adminInst = null;
        System.out.println("Admin instance deleted.");
    }
    
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String welcomeText() {
        return "Welcome admin " + adminName + " to Travel Gajelas";
    }
    
    
}

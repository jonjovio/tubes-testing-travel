/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Member extends User{
    
    private static Member memberInst;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    //VOUCHER

    public Member() {
    }

    public Member(String firstName, String lastName, String phoneNumber, int idUser, String email, String password, Model.UserType UserType) {
        super(idUser, email, password, UserType);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public static Member getMemberInst(){
        if(memberInst==null){
            memberInst = new Member();
            System.out.println("Member instance created for the first time.");
        }
        return memberInst;
    }
    
    public void deleteMemberInstance(){
        memberInst = null;
        System.out.println("Member instance deleted.");
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String welcomeText() {
        return "Welcome " + firstName + " to Travel Gajelas";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author User
 */

public class Spending {
    private String date;
    private String description;
    private String amount;
    private String balance;
    
    public Spending(String date, String description, String amount, String balance){
    setAll(date, description, amount, balance);
    
    //System.out.println("from spending object, date = " + date);
    //System.out.println("from spending object, description = " + description);
    //System.out.println("from spending object, amount = " + amount);
    //System.out.println("from spending object, balance = " + balance);
    
    }
    
    public void setAll(String da, String d, String a, String b){
        setDate(da);
        setDescription(d);
        setAmount(a);
        setBalance(b);
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    
}

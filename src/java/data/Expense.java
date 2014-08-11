/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.util.Date;

/**
 *
 * @author User
 */
public class Expense {
    private String description;
    private String amount;
    private String date;
    
    
    public Expense(String description, String amount, String date) {
        setAll(description, amount, date);
    }
    
    public void setAll(String desc, String amt, String date){
    setDescription(desc);
    setAmount(amt);
    setDate(date);
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
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}

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
public class Expense {
    private String description;
    private String amount;
    
    public Expense(String description, String amount) {
        setAll(description, amount);
    }
    
    public void setAll(String desc, String amt){
    setDescription(desc);
    setAmount(amt);
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
    
    
}

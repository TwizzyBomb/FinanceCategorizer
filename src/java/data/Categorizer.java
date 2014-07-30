/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import java.io.*;
import java.io.Console;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.*;
import com.thoughtworks.xstream.*;
/**
 *
 * @author User
 */
public class Categorizer {

    static String tempFilePath = "C:\\Users\\User\\Downloads\\csvdownload3.csv";

    //Eventually used to hold a lines worth of data from the CSV file
    static String fileName;//used in Buffered reader conditional which builds arrays
    //Creates Array list holding Spending Objects
    static ArrayList<Spending> expenses = new ArrayList<Spending>();
    //Holds the , separated values from fileName in order to plug them into the
    //spending function as parameters
    static String[] tempArray = new String[5];
    
    
    public ArrayList<Spending> RecentSpending() throws FileNotFoundException, IOException {
    
        //Java's way of reading files
        FileReader fr = new FileReader(tempFilePath);
        BufferedReader br = new BufferedReader(fr);
        //separates the values, java wanted me to split declaration
        StringTokenizer st = null;
        int lineNumber = 0, tokenNumber = 0;
        final String delimiter = ",";
        //Sets string fileName to the line in the csv file, (to be delimited by commas later)
        //and in the same step checks if this line is equal to null as the exit condition of the loop
        for(lineNumber = 1; (fileName = br.readLine()) != null; lineNumber++){

            //System.out.println(fileName);
            
            //break comma separated line using ","
            st = new StringTokenizer(fileName, delimiter);
            while (st.hasMoreTokens()) {
                    tokenNumber++;
                    //use value to hold nextToken
                    String value = st.nextToken();
                    //System.out.println("Line # " + lineNumber
                    //        + ", Token # " + tokenNumber
                    //        + ", Token : " + value);//value
                            //sets value to temp array to properly populate object
                            tempArray[tokenNumber] = value;
                            
            }
            //set Spending object
            expenses.add( new Spending(tempArray[1],tempArray[2],tempArray[3],tempArray[4]) );
            //reset token number
            tokenNumber = 0;
        }
        //System.out.println("Expenses: " + expenses.get(4).getDescription());
        return expenses;
        //perhaps this could return as a key value pair list? of descriptions and amounts
    }
    
    //Sorts Center String
    String[] eventuallySortable  = {"DDA PURCHASE", "VISA DDA PUR", "DDA WITHDRAW", "CHECK", "DEBIT"};
    String[] immediatelySortable = {"IBASE", "Online Xfer from", "Online Xfer to", "MAINTENANCE FEE", 
                                    "Debit", "ST TRF TRANSFER TO SAVINGS ACCT", "NONTD ATM FEE", "DDA DEPOSIT"};
    static String[] testStrings = { "DDA PURCHASE 00704491   SY8 SONO ALE HOUSE 531      NORWALK       * CT",
                                    "VISA DDA PUR 443106     TASTE OF HIGH RIDGE         STAMFORD      * CT",
                                    "DDA PURCHASE 001        STOP   SHOP  637            STAMFORD      * CT",
                                    "VISA DDA PUR 423168     GULF OIL 91190990           FAIRFIELD     * CT",
                                    "VISA DDA PUR 423168    SOME OTHER STRING   FAIRFIELD"};
    private static String expendature;
    private static int indx2;
    private static int indx;
    private static int middleStringIndex2;
    private static int middleStringIndex;
    private static ArrayList<Integer> indexes = new ArrayList<Integer>();
    
    public static String Sorter(String fullDescription){
        expendature = fullDescription;
        String whiteSpaces = "(\\s{3,})";//You should be able to get it to print out the string you need with regex dude.
        String wordSpaces = "[a-zA-Z0-9]{3,}[ ]?\\b{1,}";//change this to word spaces to get the next needed index
        //fuck it String wordSpaces2 = "^\\w+";
        Pattern whiteSpaceRegex = Pattern.compile(whiteSpaces);
        //Pattern wordSpaceRegex = Pattern.compile(wordSpaces);
        Matcher m = whiteSpaceRegex.matcher(fullDescription);
        //Matcher m2 = wordSpaceRegex.matcher(fullDescription);
        //Matcher m3; m3 = wordSpaceRegex.matcher(fullDescription);
        String spaces3 = "   ";
        int i = 0;
        String secondHalf;
        //include conditional to handle non dda strings
        while(m.find()){
            indx = m.start();
            indx2 = m.end();
            //Using the three spaces to sort center string, couldn't figure out word regex
            if(i<1){
            middleStringIndex = m.end();
            //System.out.println("Starting index of string: " + middleStringIndex);
            }
            //System.out.println("Start of Whitespace: #" + i + " = " + indx);//should be the first instance of 3 spaces after 1st iteration
            //System.out.println("End of Whitespace: #" + i + " = " + indx2);//should be the end of the previous white space
            if(i==1){
                middleStringIndex2 = m.start();
                //System.out.println("Ending index of string: " + middleStringIndex2);
                expendature = fullDescription.substring(middleStringIndex, middleStringIndex2);
                //got index of first word, now we have to count the number of word characters to get only this.
            }
            i++;
            //System.out.println("Found white spaces @ " + indx + " To " + indx2 + " i= " + i);
        }

        //System.out.println(expendature);
        return expendature;
    }
    
    //CATEGORY ARRAY LISTS
    private static ArrayList<Expense> SortedArrayList = new ArrayList<Expense>();
    public static ArrayList<Expense> Partying = new ArrayList<Expense>();
    private static ArrayList<Expense> Rent = new ArrayList<Expense>();
    private static ArrayList<Expense> Restaurant = new ArrayList<Expense>();
    private static ArrayList<Expense> Gas = new ArrayList<Expense>();
    private static ArrayList<Expense> BankFees = new ArrayList<Expense>();
    private static ArrayList<Expense> Groceries = new ArrayList<Expense>();
    private static ArrayList<Expense> Cash = new ArrayList<Expense>();
    private static ArrayList<Expense> Bills = new ArrayList<Expense>();
    private static ArrayList<Expense> Misc = new ArrayList<Expense>();
    private static ArrayList<Expense> Exersize = new ArrayList<Expense>();
    private static ArrayList<Expense> Transportation = new ArrayList<Expense>();
    private static ArrayList<Expense> Savings = new ArrayList<Expense>();
    private static ArrayList<Expense> Dates = new ArrayList<Expense>();
    private static ArrayList<Expense> Fines = new ArrayList<Expense>();
    //for matching
    private static boolean foundMatch;
    
    private static ArrayList<ArrayList<Expense>> Categories = new ArrayList<ArrayList<Expense>>();
    //For Printing Purposes
    private static String[] categoryStrings = {"Partying", "Rent", "Restaurant", "Gas", "BankFees",
        "Groceries", "Cash", "Bills", "Misc", "Exersize", "Transportation", "Savings", "Dates", "Fines" };
    
    
    private static void addToCorrectCategory(String category, Expense expense){
        //Switch Statement that sorts the object into the correct category
        switch (category) {
            case "Partying":
                Partying.add(expense);
                break;
            case "Rent":
                Rent.add(expense);
                break;
            case "Restaurant":
                Rent.add(expense);
                break;
            case "Gas":
                Gas.add(expense);
                break;
            case "BankFees":
                BankFees.add(expense);
                break;
            case "Groceries":
                Groceries.add(expense);
                break;
            case "Cash":
                Cash.add(expense);
                break;
            case "Bills":
                Bills.add(expense);
                break;
            case "Misc":
                Misc.add(expense);
                break;
            case "Exersize":
                Exersize.add(expense);
                break;
            case "Transportation":
                Transportation.add(expense);
                break;
            case "Savings":
                Savings.add(expense);
                break;
            case "Dates":
                Dates.add(expense);
                break;
            case "Fines":
                System.out.println("Accepted Input, Expense Added");
                Fines.add(expense);
                break;
            default: System.out.println("Not A valid Entry, Nothing added");
        }
    }
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!MAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //If I wanted to print out the full string for each entry I could return full description and do the strippng in sorter
    public static void main(String[] args) throws IOException {
        //For user input
        Console c = System.console();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Categories.add(Partying);
        Categories.add(Groceries);
        Categories.add(Gas);
        Partying.add( new Expense("WAREHOUSE WINES", "-43.45"));
        Partying.add( new Expense("BURGER BAR AND BISTRO", "-22.01"));
        Partying.add( new Expense("SY8 SONO ALE HOUSE 531", "-32.01"));
        Groceries.add( new Expense("TRADER JOE S 529 QPS", "-41.45"));
        Groceries.add( new Expense("TARGET T1544", "-21.01"));
        Groceries.add( new Expense("RITE AID CORP", "-31.01")); 
        Gas.add( new Expense("EXXONMOBIL", "-45.45"));
        Gas.add( new Expense("GULF OIL 91190990", "-25.01"));
        Gas.add( new Expense("SHELL OIL 57542948609", "-35.01"));

        try{
        Categorizer catClass = new Categorizer();//actually have to make a constructor because above class aint "static"
        ArrayList<Spending> arr = new ArrayList();//declare array list of type Spending Object
        arr = catClass.RecentSpending();//create an instance of Recent spending object called arr
        
        
        //THIS IS WHERE I PLUG IN THE VALUES OF SortedArrayList FROM SORTER
        int length = arr.size();
        for(int i=0;i<length;i++){
            String description = arr.get(i).getDescription();//calls each objects getDescription()
            String amount = arr.get(i).getAmount();//calls each objects getAmount
            //System.out.println("From Main Class: ");
            //System.out.println(description);
            String expenseItem = Sorter(description);

            
            // Plug them into expense, and their corresponding Array Lists here
            
            //WHAT IF I PLUG THE CATEGORIES IN WITH A LOOP?
            //Putting sorted 
            SortedArrayList.add( new Expense(expenseItem ,amount) );
            
            //System.out.println("#" + i + " " + SortedArrayList.get(i).getDescription() + " Amount: " + SortedArrayList.get(i).getAmount());
            
        }//End Of Creating SortedArrayList Loop

        }catch(IOException i){
            //do stuff Was IO Exception
        }
        
        
        
        int len = Categories.size();
        
        //System.out.println(SortedArrayList.get(10).getAmount());
        System.out.println("Categories.size() = " + len );
        for(int i=1;i<SortedArrayList.size();i++){
            //Looping through Categories Array
            for(int j=0;j<len;j++){
                int siz = Categories.get(j).size();
                System.out.println("Categories.get(j).size() = " + siz );
                //Looping through each expense category
                for(int k=0;k<siz;k++){
                        foundMatch=false;
                        //            partying, gas, grocieries
                        String expense = Categories.get(j).get(k).getDescription();//From Known Categories
                        String expense2 = SortedArrayList.get(i).getDescription();//From CSV after Sorting Description
                        System.out.println(i + " From Categories Loop: " + expense);
                        System.out.println(i + " From CSV File:        " + expense2); 
                        //check if category exists
                        if(expense.equals(expense2)){//MATCH FOUND, IS IN CATEGORY?
                            System.out.println("  !! Found !! " + expense + " At Index " + i + ")");
                            foundMatch = true;
                            break;
                        }else if(!expense.equals(expense2)){
                            System.out.println("Not a match");
                            //break;
                        }
                        
                        if(k==(siz-1) && foundMatch!=true){//NO MATCH FOUND, ADD TO PROPER CATEGORY. Get user input here...
                            System.out.println("  !! No Match Found !! " + expense2 + " At Index " + i + ")");
                            System.out.println(" Add " + expense2 + " to a Category?");
                            System.out.println(" Y/N ");
                            //Attempts to get input from the user
                            String YNinput = br.readLine();
                            
                            System.out.println("you said: " + YNinput);
                            if(YNinput.equals("y") || YNinput.equals("Y")){
                                //Put in category
                                System.out.println(" Add to the correct category from the List: ");
                                for(int l=0;l<categoryStrings.length;l++){
                                    System.out.println(categoryStrings[l]);
                                }
                                String ctgry = br.readLine();
                                addToCorrectCategory(ctgry, SortedArrayList.get(i)/*expense object from sorter*/);
                                System.out.println(" Accepted Input! ");
                                
                            }else if(YNinput.equals("n") || YNinput.equals("N")){
                                System.out.println("not added");
                            }else{
                                System.out.println("don't recognize input..moving on");
                            }
                        }
                        //You need to reload the list after somethings added
                        //You also need to have it search through the entire list and THEN
                        //Say "NOT FOUND" add to category?
                        
                }//End of Inner Category Loop
                
            }//End of Outer Category Loop
        }//End of SortedArrayList Loop
        XStream xstream = new XStream();
        String xml = xstream.toXML(Categories);
        xstream.setMode(XStream.NO_REFERENCES);
        //xstream.alias("Expense", Expense.class);
        System.out.println(xml);
    }//End of Main

}
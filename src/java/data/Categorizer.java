/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import com.thoughtworks.xstream.*;
import java.io.*;
import java.io.Console;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.*;
/**
 *
 * @author User
 */
public class Categorizer {

    static String tempFilePath = "C:\\Users\\User\\Downloads\\csvdownload2.csv";

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
    private static ArrayList<Expense> Asset = new ArrayList<Expense>();
    //for matching
    private static boolean foundMatch =false;
    private static String expense;
    private static String expense2;
    private static String amount;
    private static String amount2;
    private static String cmprExpense;
    private static String cmprAmount;
    
    private static TreeMap<String, ArrayList<Expense>> Categories = new TreeMap<String, ArrayList<Expense>>();
    //For Printing Purposes
    private static String[] categoryStrings = {"Partying", "Rent", "Restaurant", "Gas", "BankFees",
        "Groceries", "Cash", "Bills", "Misc", "Exersize", "Transportation", "Savings", "Dates", "Fines", "Asset" };
    
    
    private static void addToCorrectCategory(String category, Expense expense){
        //Switch Statement that sorts the object into the correct category
        switch (category) {
            case "Partying":
                System.out.println("Accepted Input, Partying Expense Added");
                Partying.add(expense);
                break;
            case "Rent":
                System.out.println("Accepted Input, Rent Expense Added");
                Rent.add(expense);
                break;
            case "Restaurant":
                System.out.println("Accepted Input, Restaurant Expense Added");
                Rent.add(expense);
                break;
            case "Gas":
                System.out.println("Accepted Input, Gas Expense Added");
                Gas.add(expense);
                break;
            case "BankFees":
                System.out.println("Accepted Input, Bank Fees Asset Added");
                BankFees.add(expense);
                break;
            case "Groceries":
                System.out.println("Accepted Input, Groceries Expense Added");
                Groceries.add(expense);
                break;
            case "Cash":
                System.out.println("Accepted Input, Cash Expense Added");
                Cash.add(expense);
                break;
            case "Bills":
                System.out.println("Accepted Input, Expense Added");
                Bills.add(expense);
                break;
            case "Misc":
                System.out.println("Accepted Input, Misc Expense Added");
                Misc.add(expense);
                break;
            case "Exersize":
                System.out.println("Accepted Input, Exersize Expense Added");
                Exersize.add(expense);
                break;
            case "Transportation":
                System.out.println("Accepted Input, Transportation Expense Added");
                Transportation.add(expense);
                break;
            case "Savings":
                System.out.println("Accepted Input, Good Job! Keep Saving!");
                Savings.add(expense);
                break;
            case "Dates":
                System.out.println("Accepted Input, I hope your date went well!");
                Dates.add(expense);
                break;
            case "Fines":
                System.out.println("Accepted Input, Fine Added. You've been a bad boy!");
                Fines.add(expense);
                break;
            case "Asset":
                System.out.println("Accepted Input, Asset Added");
                Asset.add(expense);
                break;                
            default: System.out.println("Not A valid Entry, Nothing added");
        }
    }
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!MAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //If I wanted to print out the full string for each entry I could return full description and do the strippng in sorter
    public static void main(String[] args) throws IOException, ParseException{
        //For user input
        Console c = System.console();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
        
        Categories.put("Partying", Partying);
        Categories.put("Rent", Rent);
        Categories.put("Restaurant", Restaurant);
        Categories.put("Gas", Gas);
        Categories.put("BankFees", BankFees);
        Categories.put("Groceries", Groceries);
        Categories.put("Cash", Cash);
        Categories.put("Bills", Bills);
        Categories.put("Cash", Cash);
        Categories.put("Bills", Bills);
        Categories.put("Misc", Misc);
        Categories.put("Exersize", Exersize);
        Categories.put("Transportation", Transportation);
        Categories.put("Savings", Savings);
        Categories.put("Dates", Dates);
        Categories.put("Fines", Fines);
        Categories.put("Asset", Asset);
        Partying.add( new Expense("Partying", "null", "1/1/1970"));
        Partying.add( new Expense("THE FIELD", "-16", "8/7/2014"));
        Partying.add( new Expense("KRAUSZERS FOOD STORE", "-19.21", "8/7/2014"));
        Rent.add( new Expense("Rent", "null", "1/1/1970"));
        Restaurant.add( new Expense("Restaurant", "null", "1/1/1970"));
        Restaurant.add( new Expense("VILLAGE BAGELS FAIRFIELD", "-5.84", "8/7/2014"));
        Gas.add( new Expense("Gas", "null", "1/1/1970"));
        Gas.add( new Expense("GULF OIL 91190990", "2.65", "8/7/2014"));//SHOW AMOUNTS WITH THE QUESTIONS BECAUSE EVERYMORNINGS BREAKFAST SHOULD COUNT AS GROCERIES
        BankFees.add( new Expense("Bank Fees", "null", "1/1/1970"));
        BankFees.add( new Expense("MAINTENANCE FEE", "-15", "8/7/2014"));
        Groceries.add( new Expense("Groceries", "null", "1/1/1970"));
        Cash.add( new Expense("Cash", "null", "1/1/1970"));
        Bills.add( new Expense("Bills", "null", "1/1/1970"));
        Misc.add( new Expense("Misc", "null", "1/1/1970"));
        Exersize.add( new Expense("Exersize", "null", "1/1/1970")); 
        Transportation.add( new Expense("Transportation", "null", "1/1/1970")); 
        Savings.add( new Expense("Savings", "null", "1/1/1970")); 
        Dates.add( new Expense("Dates", "null", "1/1/1970")); 
        Fines.add( new Expense("Fines", "null", "1/1/1970")); 
        Asset.add( new Expense("Asset", "null", "1/1/1970"));
        Asset.add( new Expense("Online Xfer Transfer from SV 00004789062224", "25", "8/7/2014")); 
        
        try{
        Categorizer catClass = new Categorizer();//actually have to make a constructor because above class aint "static"
        ArrayList<Spending> arr = new ArrayList();//declare array list of type Spending Object
        arr = catClass.RecentSpending();//create an instance of Recent spending object called arr
        
        
        //THIS IS WHERE I PLUG IN THE VALUES OF SortedArrayList FROM SORTER
        int length = arr.size();
        for(int i=0;i<length;i++){
            String description = arr.get(i).getDescription();//calls each objects getDescription()
            String amount = arr.get(i).getAmount();//calls each objects getAmount
            String date = arr.get(i).getDate();
            //System.out.println("From Main Class: ");
            //System.out.println(description);
            String expenseItem = Sorter(description);
            //System.out.println(date);
            
            
            // Plug them into expense, and their corresponding Array Lists here
            
            //WHAT IF I PLUG THE CATEGORIES IN WITH A LOOP?
            //Putting sorted 
            SortedArrayList.add( new Expense(expenseItem , amount, date) );
            
            //System.out.println("#" + i + " " + SortedArrayList.get(i).getDescription() + " Amount: " + SortedArrayList.get(i).getAmount());
            
        }//End Of Creating SortedArrayList Loop

        }catch(IOException i){
            //do stuff Was IO Exception
        }
        
        
        
        int len = Categories.size();
        
        //System.out.println(SortedArrayList.get(10).getAmount());
        System.out.println("The Map Categories.size() = " + len );//Need to figure out how many iterations to loop categories AND 
        for(int i=1;i<SortedArrayList.size();i++){
            //Looping through Categories Array
            //Use Categories Array!
            for(int j=0;j<len;j++){
                foundMatch = false;
                int currCatSiz = Categories.get(categoryStrings[j]).size();
                System.out.println("Current Categories length = " + currCatSiz );
                //Looping through individual expense categories (partying, groceries, etc)
                for(int k=0;k<currCatSiz;k++){
                        System.out.println("current record from within category = " + k);
                        //            partying, gas, grocieries
                        expense = Categories.get(categoryStrings[j]).get(k).getDescription();//From Known Categories
                        expense2 = SortedArrayList.get(i).getDescription();//From CSV after Sorting Description
                        //now compare the expenses to the amounts
                        amount = Categories.get(categoryStrings[j]).get(k).getAmount();//From Known Categories
                        amount2 = SortedArrayList.get(i).getAmount();//From CSV after Sorting Description
                        System.out.println(i + " From Categories Loop: " + expense + ", Amount: " + amount);
                        System.out.println(i + " From CSV File:        " + expense2+ ", Amount: " + amount2); 
                        //check if category exists
                        if(expense.equals(expense2)){//MATCH FOUND, IS IN CATEGORY?
                            System.out.println("  !! Found !! " + expense + " At Index " + j + ")");
                            foundMatch = true;
                            System.out.println("foundMatch = " + foundMatch);
                            cmprExpense = expense;
                            cmprAmount = amount;
                            break;
                        }else if(!expense.equals(expense2)){
                            System.out.println("Not a match");
                            //break;
                        }
                        

                        //You need to reload the list after somethings added
                        //You also need to have it search through the entire list and THEN
                        //Say "NOT FOUND" add to category?
                        
                }//End of Inner Category Loop
                    if(foundMatch==true){//MATCH FOUND, ADD TO PROPER CATEGORY. Get user input here...
                    System.out.println("  !! Match Found !! " + expense2 + " At Index " + i + ")");
                    System.out.println(" Add " + expense2 + " with amount " + amount2 + " to a Category?");
                    System.out.println(" Compare to " + cmprExpense + " with amount " + cmprAmount);
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
                
            }//End of Outer Category Loop
        }//End of SortedArrayList Loop
        //System.out.println(Categories.get(2).get(2).getDescription());
        XStream xstream = new XStream();
        xstream.alias("Expense", Expense.class);
        xstream.alias("Category", String.class);
        xstream.alias("Expenses", List.class);
        
        //CREATE XML STRING
        String xml = xstream.toXML(Categories);
        System.out.println(xml);
        
        //WRITE TO XML FILE, FROM MAP OF ARRAY LISTS
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Categories.xml"), "utf-8"));
            writer.write(xml);
        }catch(IOException ex){
            
        }finally{
            try{writer.close();} catch(Exception ex) {}
        }
        
        //READ FROM XML FILE, TO MAP OF ARRAY LISTS
        String everything;
        
        //THIS WAS USING xml STRING WHICH WAS FRESH IN JAVA AS AN ARRAY LIST XML
        //ArrayList<ArrayList<Expense>> cats = (ArrayList<ArrayList<Expense>>)xstream.fromXML(xml);
        //System.out.println(cats.get(2).get(2).getDescription());//IT WORKS! I can get the info back from the xml
        //Now I just have to make it into a file and load up the file at the begining
        BufferedReader br2 = new BufferedReader(new FileReader("Categories.xml"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br2.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br2.readLine();
            }
            everything = sb.toString();
        } finally {
            br2.close();
        }
        //ArrayList<ArrayList<Expense>> cats2 = (ArrayList<ArrayList<Expense>>)xstream.fromXML(everything);
        TreeMap<String, ArrayList<Expense>> xmlAllDataMap = (TreeMap<String,ArrayList<Expense>>)xstream.fromXML(everything);
        //Gets the Arraylist Back from the XML File, now iterate to find Amounts for each?
        System.out.println(xmlAllDataMap.get("Groceries").get(0).getDescription());
    }//End of Main

}
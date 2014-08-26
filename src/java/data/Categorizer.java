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

    static String tempFilePath = "C:\\Users\\User\\Downloads\\csvdownload.csv";

    //Eventually used to hold a lines worth of data from the CSV file
    static String fileName;//used in Buffered reader conditional which builds arrays
    //Creates Array list holding Spending Objects
    static ArrayList<Spending> expenses = new ArrayList<Spending>();
    //Holds the , separated values from fileName in order to plug them into the
    //spending function as parameters
    static String[] tempArray = new String[5];
    private static int lineNumber;
    
    public ArrayList<Spending> RecentSpending() throws FileNotFoundException, IOException {
    
        //Java's way of reading files
        FileReader fr = new FileReader(tempFilePath);
        BufferedReader br = new BufferedReader(fr);
        //separates the values, java wanted me to split declaration
        StringTokenizer st = null;
        lineNumber = 0;
        int tokenNumber = 0;
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
    private static ArrayList<Expense> Partying = new ArrayList<Expense>();
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
    private static ArrayList<Expense> Convenience = new ArrayList<Expense>();
    //for matching
    private static boolean foundMatch =false;
    private static String expense;
    private static String expense2;
    private static String date;
    private static String date2;        
    private static String amount;
    private static String amount2;
    private static String cmprExpense;
    private static String cmprAmount;
    private static String cmprDate;
    
    private static TreeMap<String, ArrayList<Expense>> Categories = new TreeMap<String, ArrayList<Expense>>();
    //For Printing Purposes
    private static String[] categoryStrings = {"Asset", "BankFees", "Bills", "Cash", "Convenience",
        "Dates", "Exersize", "Fines", "Gas", "Groceries", "Misc", "Partying", "Rent", "Restaurant", "Savings", "Transportation" };
    
    
    private static int addToCorrectCategory(String category, Expense expense){
        //Switch StatemeAssetnt that sorts the object into the correct category
        switch (category) {
            case "Asset":
            case "asset":
            case "1":
                System.out.println("Accepted Input, Asset Added");
                Asset.add(expense);
                break;
            case "BankFees":
            case "bankfees":
            case "2":
                System.out.println("Accepted Input, Bank Fee Expense Added"); 
                BankFees.add(expense);
                break;
            case "Bills":
            case "bills":
            case "3":
                System.out.println("Accepted Input, Bills Expense Added");
                Bills.add(expense);
                break;
            case "Cash":
            case "cash":
            case "4":
                System.out.println("Accepted Input, Cash Expense Added");
                Cash.add(expense);
                break;
            case "Convenience":
            case "convenience":
            case "5":
                System.out.println("Accepted Input, Convenience Expense Added");
                Convenience.add(expense);
                break;
            case "Dates":
            case "dates":
            case "6":
                System.out.println("Accepted Input, Dates Expense Added");
                Dates.add(expense);
                break;
            case "Exersize":
            case "exersize":
            case "7":
                System.out.println("Accepted Input, Exersize Expense Added");
                Exersize.add(expense);
                break;
            case "Fines":
            case "fines":
            case "8":
                System.out.println("Accepted Input, Fines Expense Added");
                Fines.add(expense);
                break;
            case "Gas":
            case "gas":
            case "9":
                System.out.println("Accepted Input, Gas Expense Added");
                Gas.add(expense);
                break;
            case "Groceries":
            case "groceries":
            case "10":
                System.out.println("Accepted Input, Groceries Expense Added");
                Groceries.add(expense);
                break;
            case "Misc":
            case "misc":
            case "11":
                System.out.println("Accepted Input, Misc Expense Added");
                Misc.add(expense);
                break;
            case "Partying":
            case "partying":
            case "12":
                System.out.println("Accepted Input, Partying Expense Added");
                Partying.add(expense);
                break;
            case "Rent":
            case "rent":
            case "13":
                System.out.println("Accepted Input, Rent Expense Added");
                Rent.add(expense);
                break;
            case "Restaurant":
            case "restaurant":
            case "14":
                System.out.println("Accepted Input, Restaurant Expense Added");
                Restaurant.add(expense);
                break;
            case "Savings":
            case "savings":
            case "15":
                System.out.println("Accepted Input, Savings Expense Added");
                Savings.add(expense);
                break;
            case "Transportation":
            case "transportation":
            case "16":
                System.out.println("Accepted Input, Transportation Expense Added");
                Transportation.add(expense);
                break;  
            default: System.out.println("Not A valid Entry, Nothing added");
            return 0;
        }
        return 1;
    }
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!MAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //If I wanted to print out the full string for each entry I could return full description and do the strippng in sorter
    public static void main(String[] args) throws IOException, ParseException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Maps remove duplicates!! you had two entries for bills and it reported the size as only the individuals
        Categories.put("Partying", Partying);
        Categories.put("Rent", Rent);
        Categories.put("Restaurant", Restaurant);
        Categories.put("Gas", Gas);
        Categories.put("BankFees", BankFees);
        Categories.put("Groceries", Groceries);
        Categories.put("Cash", Cash);
        Categories.put("Bills", Bills);
        Categories.put("Misc", Misc);
        Categories.put("Exersize", Exersize);
        Categories.put("Transportation", Transportation);
        Categories.put("Savings", Savings);
        Categories.put("Dates", Dates);
        Categories.put("Fines", Fines);
        Categories.put("Asset", Asset);
        Categories.put("Convenience", Convenience);
        Partying.add( new Expense("Partying", "null", "1/1/1970"));
        Rent.add( new Expense("Rent", "null", "1/1/1970"));
        Restaurant.add( new Expense("Restaurant", "null", "1/1/1970"));
        Gas.add( new Expense("Gas", "null", "1/1/1970"));
        BankFees.add( new Expense("Bank Fees", "null", "1/1/1970"));
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
        Convenience.add( new Expense("Convenience", "null" , "1/1/1970"));
        
        //I ACTUALLY HAVE TO MAKE A CONSTRUCTOR FOR A FUNCTION WITHIN THIS CLASS BECAUSE RECENT SPENDING ISN'T STATIC
        //Try catch because recent spending throws IO and FileNotFound exceptions
        try{
        Categorizer catClass = new Categorizer();
        ArrayList<Spending> arr = new ArrayList();//declare array list of type Spending Object
        //Arr is an array list of Spending Objects from the CSV file Unsorted
        arr = catClass.RecentSpending();//create an instance of Recent spending object called arr
        System.out.println(arr);
        
        
        //THIS IS WHERE I PLUG IN THE VALUES OF SortedArrayList FROM SORTER
        int length = arr.size();
        for(int i=0;i<length;i++){
            String description = arr.get(i).getDescription();//calls each objects getDescription()
            String amount = arr.get(i).getAmount();//calls each objects getAmount
            String date = arr.get(i).getDate();
            //System.out.println(description);
            String expenseItem = Sorter(description);
            //System.out.println(date);
            //Putting sorted And getting rid of pesky quotes
            SortedArrayList.add( new Expense(expenseItem.replace("\"", "") , amount.replace("\"", ""), date.replace("\"", "")) );
            //System.out.println("#" + i + " " + SortedArrayList.get(i).getDescription() + " Amount: " + SortedArrayList.get(i).getAmount());
            }//END OF "CREATING SortedArrayList" LOOP
        }catch(IOException i){
            //do stuff Was IO Exception
            System.out.println("IOException from method Recent spending. Must have to do with csvFile");
        }
        
        
        
        int allCategoriesSize = Categories.size();
        System.out.println("Line Numbers from CSV file = " + lineNumber );
        //System.out.println(SortedArrayList.get(10).getAmount());
        System.out.println("The Map Categories.size() = " + allCategoriesSize );//Need to figure out how many iterations to loop categories AND
        for(int i=1;i<SortedArrayList.size();i++){
            System.out.println("At start of new entry, foundMatch = " + foundMatch);
            //Looping through Categories Array
            for(int j=0;j<allCategoriesSize;j++){
                foundMatch = false;
                int currCatSiz = Categories.get(categoryStrings[j]).size();
                System.out.println(j + " Current Category: " + categoryStrings[j] + ", length = " + currCatSiz );
                for(int k=0;k<currCatSiz;k++){//Looping through individual expense categories (partying, groceries, etc)
                            //trying to get the current category to display
                            //System.out.println("Current Category = " + Categories.floorKey(categoryStrings[i]) );
                            //System.out.println("current record from within category = " + k);
                        //            partying, gas, grocieries
                        expense = Categories.get(categoryStrings[j]).get(k).getDescription();//From Known Categories
                        expense2 = SortedArrayList.get(i).getDescription();//From CSV after Sorting Description
                        date = Categories.get(categoryStrings[j]).get(k).getDate();//From Known Categories
                        date2 = SortedArrayList.get(i).getDate();//From CSV after Sorting Description
                        //now compare the expenses to the amounts
                        amount = Categories.get(categoryStrings[j]).get(k).getAmount();//From Known Categories
                        amount2 = SortedArrayList.get(i).getAmount();//From CSV after Sorting Description
                        //amount2 = amount2.replace("\"", "");
                        System.out.println(i + " From Categories Loop: " + expense + ", Amount: " + amount + " At " + date);
                        System.out.println(i + " From CSV File:        " + expense2+ ", Amount: " + amount2 + " At " + date2); 
                        //STRING COMPARISON HERE
                        
                        //MATCH FOUND, IS IN CATEGORY?
                            if(expense.equals(expense2)){
                                System.out.println("  !! Found !! " + expense + " At Index " + j + ")");
                                foundMatch = true;
                                System.out.println("foundMatch = " + foundMatch);
                                cmprExpense = expense;
                                cmprAmount = amount;
                                cmprDate = date;
                                break;
                            }else if(!expense.equals(expense2)){
                                System.out.println("Not a match");
                            }
                    //IN THE FINAL VERSION YOU NEED TO RELOAD THE WHOLE LIST AFTER ANYTHING GETS ADDED TO AVOID DUPLICATES
                    }//End of Inner INDIVIDUAL Category Loop
                    if(foundMatch==true){//MATCH FOUND, ADD TO PROPER CATEGORY or ASK USER WHAT TO DO
                        if(expense2.equals(cmprExpense) && amount2.equals(cmprAmount) && date2.equals(cmprDate)){
                        //ALREADY EXISTS IN CURRENT LIST OF EXPENSES, BREAK LOOP AND MOVE ON
                            //(not necessarily, I buy 2 tacitos for 2.13 from gulf oil all the time)
                        System.out.println("ALREADY EXISTS IN CURRENT LIST OF EXPENSES, BREAKING LOOP AND MOVING ON");
                        break;
                        }
                        //One of the three comparisions returned false, add to category
                        else{//Get user input here...
                        System.out.println("  !! Match Found !! " + expense2 + " in Category: " + categoryStrings[j]);
                        System.out.println(" In DB " + cmprExpense + " with amount " + cmprAmount + " at date: " + cmprDate);
                        System.out.println(" Add   " + expense2 + " with amount " + amount2 + " at date " + date2 +" to a Category?");
                        System.out.println(" Y/N ");
                        //Attempts to get input from the user
                        String YNinput = br.readLine();

                        System.out.println("you said: " + YNinput);
                            if(YNinput.equals("y") || YNinput.equals("Y")){
                                //Put in category
                                System.out.println(" Add to the correct category from the List: ");
                                for(int l=0;l<categoryStrings.length;l++){
                                    System.out.println(categoryStrings[l] + " " + (l+1) );
                                }
                                String ctgry = br.readLine();
                                if(addToCorrectCategory(ctgry, SortedArrayList.get(i)/*expense object from sorter*/)==1){
                                System.out.println("  Accepted Input!!!");
                                }else{
                                    System.out.println(expense2 + " with amount " + amount2 + " at date " + date2 + "Not Added!!");
                                }
                                //Attempts to break out of Current CSV record loop since match was found and dealt with
                                break;
                            }else if(YNinput.equals("n") || YNinput.equals("N")){
                                System.out.println("not added");
                                //Attempts to break out of Current CSV record loop since match was found and dealt with
                                break;
                            }else{
                                System.out.println("don't recognize input..moving on");
                                //Attempts to break out of Current CSV record loop since match was found and dealt with
                                break;
                            }
                        }
                    }
                }   //Iterated through all categories' entries
                if(foundMatch==false){//NO MATCH FOUND, ASK USER TO ADD TO CATEGORY
                    System.out.println(" Entry " + expense2 + " is not in any categories,");
                    System.out.println(" Add " + expense2 + " with amount " + amount2 + " at date " + date2 + " to a Category?");
                    System.out.println(" Y/N");
                    //Attempts to get input from the user
                    String YNinput = br.readLine();

                    System.out.println("you said: " + YNinput);
                        if(YNinput.equals("y") || YNinput.equals("Y")){
                            //Put in category
                            System.out.println(" Add to the correct category from the List: ");
                            for(int l=0;l<categoryStrings.length;l++){
                                System.out.println(" " + categoryStrings[l] + " " + (l+1) );
                            }
                            String ctgry = br.readLine();
                            if(addToCorrectCategory(ctgry, SortedArrayList.get(i)/*expense object from sorter*/)==1){
                            System.out.println("  Accepted Input!!!");
                            }else{
                                    System.out.println(expense2 + " with amount " + amount2 + " at date " + date2 + "Not Added!!");
                            }

                        }else if(YNinput.equals("n") || YNinput.equals("N")){
                            System.out.println("not added");
                        }else{
                            System.out.println("don't recognize input..moving on");
                        }
                }//End of foundMatch=false conditional
            }//End of Outer Category Loop
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
        //@@gets xml data from file, after first run move this to top of the main function and call the get amount from all
        //ArrayList<ArrayList<Expense>> cats2 = (ArrayList<ArrayList<Expense>>)xstream.fromXML(everything);
        TreeMap<String, ArrayList<Expense>> xmlAllDataMap = (TreeMap<String,ArrayList<Expense>>)xstream.fromXML(everything);
        //Gets the Arraylist Back from the XML File, now iterate to print Amounts for each?
        for(int m = 0; m<categoryStrings.length; m++){
            System.out.println("Category = " + categoryStrings[m]);
            double total = 0.0;
            for(int n = 0; n < xmlAllDataMap.get(categoryStrings[m]).size(); n++){
                String dsc = xmlAllDataMap.get(categoryStrings[m]).get(n).getDescription();
                String amt = xmlAllDataMap.get(categoryStrings[m]).get(n).getAmount();
                if(!amt.equals("null")){
                    double amtValue = Double.parseDouble(amt);
                    System.out.println("  Description " + (m+n) + " " + dsc);
                    System.out.println("  Amount " + (m+n) + " " + amtValue);
                    total+=amtValue;
                }
            }
            System.out.println(total);
        }
    }//End of Main

}
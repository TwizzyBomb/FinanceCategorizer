/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import java.io.*;
import java.util.*;
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
            System.out.println("Starting index of string: " + middleStringIndex);
            }
            //System.out.println("Start of Whitespace: #" + i + " = " + indx);//should be the first instance of 3 spaces after 1st iteration
            //System.out.println("End of Whitespace: #" + i + " = " + indx2);//should be the end of the previous white space
            if(i==1){
                middleStringIndex2 = m.start();
                System.out.println("Ending index of string: " + middleStringIndex2);
                expendature = fullDescription.substring(middleStringIndex, middleStringIndex2);
                //got index of first word, now we have to count the number of word characters to get only this.
            }
            i++;
            //System.out.println("Found white spaces @ " + indx + " To " + indx2 + " i= " + i);
        }
        //System.out.println(expendature);
        return expendature;
    }
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!MAIN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //If I wanted to print out the full string for each entry I could return full description and do the strippng in sorter
    public static void main(String[] args) {
    // TODO code application logic here
        try{
        Categorizer catClass = new Categorizer();//actually have to make a constructor because above class aint "static"
        ArrayList<Spending> arr = new ArrayList();//declare array list of type Spending Object
        arr = catClass.RecentSpending();//create an instance of Recent spending object called arr
        
        int length = arr.size();
        for(int i=1;i<length;i++){
            String something = arr.get(i).getDescription();//calls each objects getDescription()
            System.out.println("From Main Class: ");
            System.out.println(something);
            String expenseItem = Sorter(something);
            System.out.println("Sorted from Main:" + expenseItem);
        }
        //System.out.println("Something: " + something);
        //String localSomething = testStrings[2];
        //System.out.println("localSomething: " + localSomething);
        //String something1 = Sorter(something);
        //System.out.println("Sorted = " + something1);
        }catch(IOException i){
            //do stuff Was IO Exception
        }
        
    }
        
}
    


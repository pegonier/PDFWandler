package org.pegonier.pdfwandler;

public class changeDateForm {
    public static int countNumbers(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }
    public static String dateTurner (String inDate) {
        String outDate = "";
        if (countNumbers(inDate)==8){
            String Day = inDate.substring(0, 2);
            String Month = inDate.substring(2, 4);
            String Year = inDate.substring(4, 8);
            outDate = Year + Month + Day;
        }else if (countNumbers(inDate)==6){
            String Day = inDate.substring(0, 2);
            String Month = inDate.substring(2, 4);
            String Year = inDate.substring(4, 6);
            outDate = "20"+Year + Month + Day;
        }

        return outDate;
    }
}

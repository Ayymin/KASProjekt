package application.model;

public class Number {

    public static boolean isANumber(String str){
        boolean isNumber = true;
        if(str.isEmpty()){
            isNumber = false;
        } else {
            try {
                double i = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                isNumber = false;
            }
        }
        return isNumber;
    }
}

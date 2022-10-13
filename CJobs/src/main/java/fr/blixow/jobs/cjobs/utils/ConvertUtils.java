package fr.blixow.jobs.cjobs.utils;

public class ConvertUtils {

    public static boolean isDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception ignored){}
        return false;
    }
    public static double getDoubleFromFormatted(String formatted){
        double val = 0;
        try {
            if(formatted.endsWith("B")){
                formatted = formatted.substring(0, formatted.length() - 1);
                if(isDouble(formatted)){ val = Double.parseDouble(formatted) * 1000000000; }
            } else if(formatted.endsWith("M")){
                formatted = formatted.substring(0, formatted.length() - 1);
                if(isDouble(formatted)){ val = Double.parseDouble(formatted) * 1000000; }
            } else if(formatted.endsWith("k")){
                formatted = formatted.substring(0, formatted.length() - 1);
                if(isDouble(formatted)){ val = Double.parseDouble(formatted) * 1000; }
            } else {
                if(isDouble(formatted)){ val = Double.parseDouble(formatted); }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return val;
    }


    public static String tryFormatStringDouble(String str){
        if(isDouble(str)){ return doubleToStringFormatted(Double.parseDouble(str)); }
        return str;
    }

    public static String doubleToStringFormatted(double value){
        try {
            String val = String.valueOf(value);
            if(value / 1000000000 >= 1){
                int bil = (int) (value / 1000000000);
                double db2 = value - (bil*Math.pow(10, 9));
                int mil = (int) (db2 / 100000000);
                val = bil + "." + mil + "B";
            } else if(value / 1000000 >= 1){
                int bil = (int) (value / 1000000);
                double db2 = value - (bil*Math.pow(10, 6));
                int mil = (int) (db2 / 100000);
                val = bil + "." + mil + "M";
            } else if(value / 1000 >= 1){
                int bil = (int) (value / 1000);
                double db2 = value - (bil*Math.pow(10, 3));
                int mil = (int) (db2 / 100);
                val = bil + "." + mil + "k";
            }
            return val;
        } catch (Exception ignored){}
        return "???";
    }

    public static boolean isDoubleFormated(String str){
        if(str.endsWith("B") || str.endsWith("M") || str.endsWith("k")){ str = str.substring(0, str.length() - 1); }
        return isDouble(str);
    }


}


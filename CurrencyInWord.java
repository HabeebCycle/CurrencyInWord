
import java.util.*;

/*
 * This is a Java class library that will return the amount in words of any amount bellow 999 trillion.
 * For instance, 312195 will return 'Three Hundred and Twelve Thousand, One Hundred and Ninety Five' of the currency1 and currency2 given.
 *
 * USAGE
 * Create an object of this class by instantiating
 * Instantiate this class as 'CurrencyInWord currencyWord = new CurrencyInWord();'
 * Call the 'convertToWord()' method passing the value or digits you wish to convert as an argument; this will eturn a string word of the value.
 * String word = currencyWord.convertToWord(312195.50); //No comma (,) between the digits.
 *
 * You can use and share it, but do not modify the content.
 * Contact me if you need the PHP, JavaScript, C#, C++ or Python versions.
 * You can contact me if you need an explanation, suggesstions or questions.
 *
 * AUTHOR: Okunade, Habeeb Babatunde
 * COUNTRY: Nigeria
 * E-MAIL: imsofnet@gmail.com
 * DATE: 10-Jul-2010
*/


public class CurrencyInWord {
	
	/**
	 * @amount : Passing the string value of the amount to convert only.
	 *
	 */
	public String convertToWord(String amount){
		return convertToWord(amount, "", "");
	}
	
	/**
	 * @amount : Passing the string value of the amount to convert.
	 * @currencyName : the required currency name e.g Naira, United State Dollars
	 * @currencyPart : fractional part of the required currency e.g kobo, cents
	 */
	public String convertToWord(String amount, String currencyName, String currencyPart){
		try{
			String part[] = amount.replace(".","#").split("#");
			int sort = part.length;
			int part2 = 0;
			boolean haspart = false;
			if(sort>1){
				haspart = true;
				part2 = Integer.parseInt(part[1]);
			}
			return getSingleDigit(part[0]) + " " + currencyName + ((haspart && (part2>0)) ? ", " + 
				getDoubleDigit(part[1]) + " " + currencyPart : "") + ((haspart && (part2>0)) ? "" : " Only");
		}catch(NumberFormatException nfe){
			nfe.printStackTrace();
			return "Invalid string value passed : " + nfe.getMessage();
		}
	}
	
	/**
	 * @amount : Passing the numeric value of the amount to convert only.
	 *
	 */
	public String convertToWord(double amount){
		try{
			return convertToWord(Double.toString(amount));
		}catch(Exception ex){
			return "Invalid string value passed : " + ex.getMessage();
		}
	}
	
	private String getSingleDigit(String digit){
		String num[] = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
		if(digit.length() == 1)
			return num[Integer.parseInt(digit)];
		else
			return getDoubleDigit(digit);
	}
	
	private String getDoubleDigit(String digit){
		if(digit.length() <= 2){
			Map<String, String> num = new HashMap<>();
			String num1[] = {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen",
			"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
			String num2[] = {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "30", "40", "50", "60", "70", "80", "90"};
			for(int i=0; i<num1.length; i++){
				num.put(num2[i], num1[i]);
			}
			int part1 = Integer.parseInt(digit) / 10;
			int part2 = Integer.parseInt(digit) % 10;
			if(part1 == 1){
				return num.get(digit);
			}else if(part2 == 0){
				return num.get(digit);
			}else{
				return (part1>0?num.get(String.valueOf(part1*10)):"") + (part1>0?"-":"") + getSingleDigit(String.valueOf(part2));
			}
		}else{
			return getThreeDigit(digit);
		}
	}
	
	private String getThreeDigit(String digit){
		if(digit.length() <= 3){
			int part1 = Integer.parseInt(digit) / 100;
			int part2 = Integer.parseInt(digit) % 100;
			if(part2 == 0){
				return getSingleDigit(String.valueOf(part1)) + " Hundred";
			}else{
				return (part1>0?getSingleDigit(String.valueOf(part1)) + " Hundred":"") + (part1>0?" and ":"") + getDoubleDigit(String.valueOf(part2));
			}
		}else{
			return get4To6Digit(digit);
		}
	}
	
	private String get4To6Digit(String digit){
		if(digit.length() < 7){
			int part1 = Integer.parseInt(digit) / 1000;
			int part2 = Integer.parseInt(digit) % 1000;
			if(part2==0){
				return getSingleDigit(String.valueOf(part1)) + " Thousand";
			}else{
				return (part1>0?getSingleDigit(String.valueOf(part1)) + " Thousand":"") + (part1>0?", ":"") + getThreeDigit(String.valueOf(part2));
			}
		}else{
			return get7To9Digit(digit);
		}
	}
	
	private String get7To9Digit(String digit){
		if(digit.length() < 10){
			int part1 = Integer.parseInt(digit) / 1000000;
			int part2 = Integer.parseInt(digit) % 1000000;
			if(part2==0){
				return getSingleDigit(String.valueOf(part1)) + " Million";
			}else{
				return (part1>0?getSingleDigit(String.valueOf(part1)) + " Million":"") + (part1>0?", ":"") + get4To6Digit(String.valueOf(part2));
			}
		}else{
			return get10To12Digit(digit);
		}
	}
	
	private String get10To12Digit(String digit){
		//System.out.println (digit);
		if(digit.length() < 13){
			int part1 = (int)(Long.parseLong(digit) / 1000000000L);
			int part2 = (int)(Long.parseLong(digit) % 1000000000L);
			if(part2==0){
				return getSingleDigit(String.valueOf(part1)) + " Billion";
			}else{
				return (part1>0?getSingleDigit(String.valueOf(part1)) + " Billion":"") + (part1>0?", ":"") + get7To9Digit(String.valueOf(part2));
			}
		}else{
			return get13To15Digit(digit);
		}
	}
	
	private String get13To15Digit(String digit){
		if(digit.length() < 16){
			int part1 = (int)(Long.parseLong(digit) / 1000000000000L);
			int part2 = (int)(Long.parseLong(digit) % 1000000000000L);
			if(part2==0){
				return getSingleDigit(String.valueOf(part1)) + " Trillion";
			}else{
				return (part1>0?getSingleDigit(String.valueOf(part1)) + " Trillion":"") + (part1>0?", ":"") + get10To12Digit(String.valueOf(part2));
			}
		}else{
			return digit + " - No correct word found!";
		}
	}
	
	
	/* Testing */
	
	public static void main (String[] args) {
		CurrencyInWord 	currencyWord = new CurrencyInWord();
		String word = currencyWord.convertToWord("7136257874405.48", "Naira", "Kobo");
		System.out.println (word);
	}

}
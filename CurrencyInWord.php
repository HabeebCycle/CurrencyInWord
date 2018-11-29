<?php
/*
 * This is a PHP class library that will return the amount in words of any amount bellow 999 trillion.
 * For instance, 312195 will return 'Three Hundred and Twelve Thousand, One Hundred and Ninety Five' of the currency1 and currency2 given.
 * USAGE
 * Instantiate this class as '$your_variable = new CurrencyInWord();'
 * Call the 'convertToWord()' function passing the value or digits you wish to convert as argument.
 * $var_in_word = $your_variable -> convertToWord(312195.50); //No comma (,) between the digits.
 * echo $your_variable->convertToWord(123,456,789);
 *
 * You can use and share it, but do not modify the content.
 * Contact me if you need the Java, JavaScript, C#, C++ or Python versions.
 * You can contact me if you need an explanation, suggesstions or questions.
 *
 * AUTHOR: Okunade, Habeeb Babatunde
 * COUNTRY: Nigeria
 * E-MAIL: imsofnet@gmail.com
 * DATE: 10-Jul-2010
*/

public class CurrencyInWord{
	
	function convertToWord($amount){
		$currency1 = "Naira";   //Change to the required currency name e.g United State Dollars.
		$currency2 = "Kobo";    //Change to fractional part of the required currency e.g cents.
		$part = explode(".",$amount);
		$sort = count($part);
		if($sort>1){
			$haspart = true;
		}else{
			$haspart = false;
		}
		return $this->getSingleDigit($part[0])." ".$currency1.(($haspart and $part[1]>0)?", ".$this->getDoubleDigit($part[1])." ".$currency2:"").(($haspart and $part[1]>0)?"":" Only");
	}
	
	function getSingleDigit($digit){
		$num = ["","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"];
		if(strlen($digit)==1)
			return $num[$digit];
		else
			return $this->getDoubleDigit($digit);
	}
	
	function getDoubleDigit($digit){
		$num = array(10=>"Ten",11=>"Eleven",12=>"Twelve",13=>"Thirteen",14=>"Fourteen",15=>"Fifteen",16=>"Sixteen",17=>"Seventeen",18=>"Eighteen",19=>"Nineteen",20=>"Twenty",30=>"Thirty",40=>"Forty",50=>"Fifty",60=>"Sixty",70=>"Seventy",80=>"Eighty",90=>"Ninety");
		if(strlen($digit)<=2){
			$part1 = (int)($digit/10);
			$part2 = $digit%10;
			if($part1==1){
				return $num[$digit];
			}elseif($part2==0){
				return $num[$digit];
			}else{
				return ($part1>0?$num[$part1*10]:"").($part1>0?"-":"").$this->getSingleDigit($part2);
			}
		}else{
			return $this->getThreeDigit($digit);
		}
	}
	
	function getThreeDigit($digit){
		if(strlen($digit)<=3){
			$part1 = (int)($digit/100);
			$part2 = $digit%100;
			if($part2==0){
				return $this->getSingleDigit($part1)." Hundred";
			}else{
				return ($part1>0?$this->getSingleDigit($part1)." Hundred":"").($part1>0?" and ":"").$this->getDoubleDigit($part2);
			}
		}else{
			return $this->get4To6Digit($digit);
		}
	}
	
	function get4To6Digit($digit){
		if(strlen($digit)<7){
			$part1 = (int)($digit/1000);
			$part2 = $digit%1000;
			if($part2==0){
				return $this->getSingleDigit($part1)." Thousand";
			}else{
				return ($part1>0?$this->getSingleDigit($part1)." Thousand":"").($part1>0?", ":"").$this->getThreeDigit($part2);
			}
		}else{
			return $this->get7To9Digit($digit);
		}
	}
	
	function get7To9Digit($digit){
		if(strlen($digit)<10){
			$part1 = (int)($digit/1000000);
			$part2 = $digit%1000000;
			if($part2==0){
				return $this->getSingleDigit($part1)." Million";
			}else{
				return ($part1>0?$this->getSingleDigit($part1)." Million":"").($part1>0?", ":"").$this->get4To6Digit($part2);
			}
		}else{
			return $this->get10To12Digit($digit);
		}
	}
	
	function get10To12Digit($digit){
		if(strlen($digit)<13){
			$part1 = (int)($digit/1000000000);
			$part2 = $digit%1000000000;
			if($part2==0){
				return $this->getSingleDigit($part1)." Billion";
			}else{
				return ($part1>0?$this->getSingleDigit($part1)." Billion":"").($part1>0?", ":"").$this->get7To9Digit($part2);
			}
		}else{
			return $this->get13To15Digit($digit);
		}
	}
	
	function get13To15Digit($digit){
		if(strlen($digit)<16){
			$part1 = (int)($digit/1000000000000);
			$part2 = $digit%1000000000000;
			if($part2==0){
				return $this->getSingleDigit($part1)." Trillion";
			}else{
				return ($part1>0?$this->getSingleDigit($part1)." Trillion":"").($part1>0?", ":"").$this->get10To12Digit($part2);
			}
		}else{
			return $digit." - No correct word found!";
		}
	}

}

?>

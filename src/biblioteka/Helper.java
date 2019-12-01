package biblioteka;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
	
	private Helper() {}
	
	static String provjeriNaziv(Scanner tss) {
		
		String strNaziv = tss.next();
		
		if(strNaziv.length() < 3) {
			System.out.println("Ime korisnika mora imati barem tri slova!");
			return null;
		}else if(Character.isLowerCase(strNaziv.charAt(0))) {
			System.out.println("Ime korisnika mora pocinjati velikim slovom!");
			return null;
		}
		
		for(int i = 0; i < strNaziv.length(); i++) {
			if(Character.isDigit(strNaziv.charAt(i))) {
				System.out.println("Pogresan unos podataka. Molimo unesite ispravo ime bez brojeva!");
				return null;
			}
		}
		return strNaziv;
	}
	
	static int vratiInt(Scanner ts1) {
		
		int intb = 0;
		
		try {
			intb = ts1.nextInt();
		} catch (InputMismatchException iznimka) {
			System.out.println("Pogresan unos podataka. Molimo unesite ispravnu brojcanu vrijednost!\n");
			ts1.nextLine();
			return -1;
		}		
		return intb;
	}
	
	static double vratiDouble(Scanner ts2) {
		
		double doub = 0;
		
		try {
			doub = ts2.nextDouble();
		} catch (InputMismatchException iznimka) {
			System.out.println("Pogresan unos podataka. Molimo unesite ispravnu brojcanu vrijednost!\n");
			ts2.nextLine();
			return -1;
		}
		
		return doub;
	}	
}

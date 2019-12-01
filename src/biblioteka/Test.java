package biblioteka;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		Scanner input = new Scanner("Ja,Wolfgang,Hilbig,Fraktura,Roman,978,false").useDelimiter(",");

		String nazivKnjige = input.next();
		/* -- Ucitavnje autora -- */
		String autorIme = input.next();
		String authorPrezime = input.next();
		Autor autor = new Autor(autorIme, authorPrezime);
		/* -- Ucitavnje izdavaca knjige -- */
		String naziv = input.next();
		Izdavac izdavac = new Izdavac(naziv);
		/* Nastavak ucitavanja */
		String zanr = input.next();
		int isbn = input.nextInt();
		boolean status = input.nextBoolean();

		System.out.println(nazivKnjige + "; " + autorIme + " " + authorPrezime + " " + naziv + " " + zanr
				+ " " + isbn + " " + status);
		/*
		 * while(sc.hasNextInt()) { int broj = sc.nextInt(); if (broj % 2 == 0) {
		 * System.out.println(broj); } }
		 */

	}
}

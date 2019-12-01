package biblioteka;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	/* Method that reads existing books from the file */
	static ArrayList<Knjiga> booksFromFile(ArrayList<Knjiga> list) throws IOException {

		Path path = Paths.get("knjige.txt");
		Scanner input = new Scanner(path);

		/* In case that file doesnt exists */
		if (!Files.exists(path)) {
			System.out.println("Fajl ne postoji. Kreiranje novog fajla. ");
			Files.createFile(path);
		}

		/* Read the lines from the file */
		while (input.hasNext()) {
			/* Koristenje delimitera za razdvajanje vrijednosti */
			input.useDelimiter(",");
			/* -- Ucitavnje naziva knjige -- */
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
			input.nextLine(); // prelazak unovu liniju

			list.add(new Knjiga(nazivKnjige, autor, izdavac, zanr, isbn, status));

		}
		input.close();

		return list;
	}

	/* Method that writes books to the file */
	static void booksToFile(ArrayList<Knjiga> list) throws IOException {

		Path path = Paths.get("knjige.txt");
		BufferedWriter writer = Files.newBufferedWriter(path);

		/* In case that file doen't exists */
		if (!Files.exists(path)) {
			Files.createFile(path);
		}

		/* Write currently books to the file */
		for (Knjiga knjiga : list) {
			writer.write(knjiga.get_nazivKnjige() + "," + knjiga.get_autor().get_autorIme() + ","
					+ knjiga.get_autor().get_authorPrezime() + "," + knjiga.get_izdavac().getNaziv() + ","
					+ knjiga.get_zanr() + "," + knjiga.get_isbn() + "," + knjiga.get_status() + ",");
			
			writer.newLine();
		}
		writer.close();
	}

}

package biblioteka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Biblioteka {
	
	/* Array liste knjiga, racuna i izdavanja */
	private ArrayList<Knjiga> knjige = new ArrayList<Knjiga>(); 	
	private ArrayList<Racun> racuni = new ArrayList<Racun>(); 	
	private ArrayList<Izdavanje> izdavanja = new ArrayList<Izdavanje>();
	
	/* ------------------------------------------------------------------ */
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		Biblioteka biblioteka = new Biblioteka();
		
		biblioteka.start(input);
		
	}
	
	public void start(Scanner sc) {	
		
		System.out.printf("———————————————————————————————————————————————————————————————%n");
		System.out.printf("****%10sDOBRODOSLI U JEDNOSTAVNU BIBLIOTEKU%10s****%n","","");
		System.out.printf("———————————————————————————————————————————————————————————————%n%n");
		System.out.printf("%15sOdaberite jednu od mogucnosti:%n","");

		int izbor;
		
		/* Pokusaj citanja knjiga iz datoteke */
		try {
			knjige = FileIO.booksFromFile(knjige);
		} catch (IOException e) {
			System.out.printf("%n");
			System.out.printf("**** DATOTEKA SA KNJIGAMA NE POSTOJI ILI JE OSTECENA ****%n");
			System.out.printf("****       NOVA DATOTEKA CE BITI KREIRANA            ****%n%n");
		}
				
		do {
			
			this.intro();  // ponovni ispis opcija za unos u program
			
			if((izbor = Helper.vratiInt(sc)) ==  -1) {
				izbor = 1;
				continue;
			}
			
			//izbor = unos.nextInt();
			sc.nextLine();
			
			switch (izbor) {
			/* Procesuiranje izbora */
			case 0:	
				/* Izlaz iz programa */
				System.out.println("Kraj programa!\n");
				break;				
			case 1:	
				/* Pozivanje funkcije za kreiranje racuna*/
				this.kreiranjeRacuna(sc);				
				break;				
			case 2:	
				/* Pozivanje funkcije za kreiranje knjiga*/
				this.dodavanjeKnjige(sc);				
				break;
			case 3:	
				/* Pozivanje funkcije za podizanje knjiga*/
				this.podizanjeKnjige(sc);				
				break;				
			case 4:	
				/* Pozivanje funkcije za ispis postojecih knjiga*/
				this.spisakKnjiga();
				break;				
			case 5:	
				/* Pozivanje funkcije za ispis postojecih racuna*/
				this.spisakRacuna();
				break;				
			default:				
				System.out.println("Pogresan unos sa komandne linije!\n");				
				break;
			}			
		}while(izbor != 0);
		
		/* Try to write books to file */
		try {
			FileIO.booksToFile(knjige);
		} catch (IOException e) {			
			System.out.printf("%n");
			System.out.printf("**** NIJE MOGUCE PISATI U DATOTEKU ****%n.");
			System.out.printf("****     PROVJERITE ISPRAVNOST	  ****%n%n");			
		}
		
	}
	
	/* Funkcija za ispis opcija za interakciju sa korisnikom */
	public static void intro() {
		
	    System.out.println("1 - Kreiranje novog racuna.");
	    System.out.println("2 - Dodavanje novih knjiga.");
	    System.out.println("3 - Podizanje knjige iz biblioteke");
	    System.out.println("4 - Ispisivanje detalja postojecih knjiga");
	    System.out.println("5 - Ispisivanje detalja postojecih racuna");
	    System.out.println("0 - Izlaz iz programa");
	    System.out.println();
		
	}
	
	/* Funkcija za podizanje knjiga iz biblioteke */
	public void podizanjeKnjige(Scanner sc) {
		
		/* Unosenje i provjera podataka za racun */
	    System.out.print("Unesite broj racuna: ");
		int racunId = 1;
		
		while(racunId > 0) {
			/* Provjera ispravnog unosa tipa podatka */
			if((racunId = Helper.vratiInt(sc)) ==  -1) {
				racunId = 1;
				continue;
			}			
			
			if(provjeri(1, racunId) == 0) {
				System.out.println("Broj racuna nije vazeci. Unesite ponovo broj racuna!\n");
				continue;
			}else if(provjeraRacuna(racunId) == -1) {
				System.out.println("Nije dozvoljeno podizanje više od 3 knjige! Da li zelite koristiti drugi racun? (d,D)-da (ost)-ne \n");
				char pitanje = sc.next().charAt(0);
				if(pitanje == 'd' || pitanje == 'D') {
					continue;
				}else if(pitanje == 'n' || pitanje == 'N') {
					break;
				}else {
					continue;
				}
			} else {
				break;
			}
		}
		
		// Unosenje i provjera podataka za knjigu 
		System.out.print("Unesite broj knjige: ");
		int knjigaId = 1;
		
		while(knjigaId > 0) {
			// Provjera ispravnog unosa tipa podatka 
			if((knjigaId = Helper.vratiInt(sc)) ==  -1) {
				knjigaId = 1;
				continue;
			}			

			if(provjeri(2, knjigaId) == 0) {
				System.out.println("Broj knjige nije vazeci. Unesite ponovo broj knjige!\n");
				continue;
			}else if(provjeraKnjiga(knjigaId) == 0) {
				System.out.println("Odabrana knjiga je vec izdata! Da li zelite odabrati drugu knjigu? (d,D)-da (ost)-ne \n");
				char pitanje = sc.next().charAt(0);							
				if(pitanje == 'd' || pitanje == 'D') {
					continue;
				}else if(pitanje == 'n' || pitanje == 'N') {
					break;
				}else {
					continue;
				}
			}
			else {
				break;
			}
		}	
		
		System.out.println();
		
		/* Kreiranje novog izdavanja knjige */					
		izdavanja.add(new Izdavanje(racunId, knjigaId, new Date()));
		
		/* Povecaj broj izdatih knjiga za dati racun */				
		for(Racun r : racuni) {
			if(r.get_brojRacuna() == racunId) {
				r.povecajBrojKnjiga();
			}		
		}
		
		/* Promijeni status knjige */
		for(Knjiga kg : knjige) {
			if(kg.get_isbn() == knjigaId) {
				kg.postaviStatus();
			}		
		}
		
	}
	
	/* Funkcija za provjeru da li na racunu postoje vec tri knjige */
	private int provjeraRacuna(int refId) {
		
		for(Racun racun : racuni) {
			if(racun.get_brojRacuna() == refId && racun.get_brojPosudKnjiga() == 3) {				
				return -1;				
			}			
		}
		return 0;
	}
	
	/* Funkcija za provjeru da li je knjiga izdata */
	private int provjeraKnjiga(int knjigaId) {
		
		for(Knjiga knjiga : knjige) {
			if(knjiga.get_isbn() == knjigaId && knjiga.get_status()) {
				return 1;
			}
		}
		return 0;
	}
	
	/* Funkcija za provjeru da li postoji odredjena knjiga ili racun */
	private int provjeri(int ref, int pBr) {
		
		switch (ref) {
		
		case 1:
			
			for(Racun racn : racuni) {
				if(racn.get_brojRacuna() == pBr)
					return -1;
			}			
			break;
			
		case 2:
			
			for(Knjiga kng : knjige) {
				if(kng.get_isbn() == pBr)
					return -1;
			}
			break;
		}				
		return 0;
	}
	
	/* Funkcija za dodavanje knjiga u bazu */
	public void dodavanjeKnjige(Scanner tast) {
		
		String naziv, zanr, ime, prez;
		int brojKnjige = 1;
		Autor autor;
		Izdavac izdavac;
		
	    System.out.print("Unesite naziv knjige: ");
	    naziv = tast.nextLine();
	    System.out.print("Unesite ime i prezime autora knjige: ");
	    ime = tast.next();
	    prez = tast.next();
		autor = new Autor(ime, prez);
		System.out.print("Unesite naziv izdavaca: ");
		izdavac = new Izdavac(tast.next());
		System.out.print("Unesite naziv zanra: ");
		zanr = tast.next();
		System.out.print("Unesite broj knjige: ");
		
		while(brojKnjige > 0) {
			
			if((brojKnjige = Helper.vratiInt(tast)) ==  -1) {
				brojKnjige = 1;
				continue;
			}

			if(brojKnjige < 0) {
				System.out.println("Broj knjige ne moze biti negativan. Ponovo unesite broj knjige!\n");
				brojKnjige = 1;
				continue;
			}else if(provjeri(2, brojKnjige) == -1) {
				System.out.println("Broj knjige vec postoji. Unesite drugi broj knjige!\n");
				continue;
			}
			else {		
				break;
			}
		}
		
		System.out.println();
		
		knjige.add(new Knjiga(naziv, autor, izdavac, zanr, brojKnjige));
		
	}
	
	/* Funkcija za ispis svih knjiga u sistemu */
	public void spisakKnjiga() {	
		
		if(knjige.size() == 0) {
			System.out.printf(">> U biblioteci nema raspoloživih knjiga! <<%n%n");
			return;
		}
		
		System.out.printf("%S %d%n", "spisak knjiga u biblioteci po stanjima:", knjige.size());
		
		for(Knjiga knj : knjige) {
			System.out.println(knj.informacije());
		}
		
		System.out.printf("************************************************%n%n");
		
	}
	
	/* Funkcija za ispis svih racuna u sistemu */
	public void spisakRacuna() {		
		
		if(racuni.size() == 0) {
			System.out.printf(">> U biblioteci nema kreiranih racuna! <<%n%n");
			return;
		}
		
		System.out.printf("%S %d%n", "ukupan broj racuna po stanjima:", racuni.size());
		
		for(Racun rac : racuni) {
			System.out.println(rac.detaljiRacuna());
		}
		
		System.out.printf("************************************************%n%n");
		
	}
	
	/* Funkcija za kreiranje novog racuna */
	public void kreiranjeRacuna(Scanner tast) {
		
		String imeKor = new String("-");
		int brojRacuna = 1;		
		
	    System.out.print("Unesite broj racuna: ");
	    
	    while(brojRacuna > 0) {
	    	
	    	if((brojRacuna = Helper.vratiInt(tast)) ==  -1) {
	    		brojRacuna = 1;
				continue;
			}	    	
	    	//brojRacuna = tast.nextInt();
			if(brojRacuna < 0) {
				System.out.println("Broj racuna ne moze biti negativan. Ponovo unesite broj!\n");
				brojRacuna = 1;
				continue;
			}else if(provjeri(1, brojRacuna) == -1) {
				System.out.println("Broj racuna vec postoji. Molimo unesite drugi broj!\n");
				continue;
			}
			else {
				break;
			}
		}	    
	    System.out.print("\nUnesite ime korisnika: ");
	    
	    while(imeKor.length() > 0) {			
			if((imeKor = Helper.provjeriNaziv(tast)) ==  null) {
				imeKor = "-";
				continue;
			} else {
				break;
			}			
	    }
	    //imeKor = tast.next();
		System.out.println();

	    racuni.add( new Racun(brojRacuna, imeKor) );
		
	}	
}

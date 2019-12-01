package biblioteka;

public class Knjiga {
	
	private String nazivKnjige;
	private Autor autor;
	private Izdavac izdavac;
	private String zanr;
	private int isbn;
	private boolean status;
	
	Knjiga(String naziv, Autor aut, Izdavac izd, String zanr, int isbn) {
		this.nazivKnjige = naziv;
		this.autor = aut;
		this.izdavac = izd;
		this.zanr = zanr;
		this.isbn = isbn;
		this.status = true;
		// Ovaj dio koda za provjeru ISBN je namijenjem za kasniju implementaciju
		//this._isbn = (_provjeraISBN(_isbn))? _isbn.charAt(0)+"-"+_isbn.substring(1, 3)+"-"+_isbn.substring(3, 9)+"X" : "x-xx-xxxxxx-x";
	}
	
	/* Arq konstruktor koji upotrebljavam radi citanja iz fajla */
	Knjiga(String naziv, Autor aut, Izdavac izd, String zanr, int isbn, boolean status) {
		this.nazivKnjige = naziv;
		this.autor = aut;
		this.izdavac = izd;
		this.zanr = zanr;
		this.isbn = isbn;
		this.status = status;		
	}
		
	public String get_zanr() {
		return zanr;
	}

	public String get_nazivKnjige() {
		return nazivKnjige;
	}

	public Autor get_autor() {
		return autor;
	}

	public Izdavac get_izdavac() {
		return izdavac;
	}

	public int get_isbn() {
		return isbn;
	}

	public boolean get_status() {
		return status;
	}
	
	public void postaviStatus() {
		if(this.status)
			status = false;
		else
			status = true;
	}

	public String informacije() {
		
		String status = (this.get_status())? "Knjiga na stanju": "Knjiga je izdata";
		
		return "———————————————————————————————————————————————\n"+
				" Naslov: " + nazivKnjige + "\n Autor: " + autor.get_autorIme() + "\n Izdavac: " +
				izdavac.izdavacInfo() + "\n Zanr: " + zanr + "\n ID knjige: " + isbn+
				"\n Status knjige: " + status +
				"\n———————————————————————————————————————————————";
	}

	private boolean provjeraISBN(String booknum) {
		
		// Validacija checksum - d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11
		
		int suma = 0;
		
		if(booknum.length() < 10 ) {
			System.out.println("ISBN broj nije validan!");
			return false;
		}
		
		for(int i = 0; i < 10; i++){
			suma += (i+1) * Integer.parseInt(booknum.substring(i, i+1));
		}
		
		return ((suma % 11) == 0 )? true : false;		
	}
	
}

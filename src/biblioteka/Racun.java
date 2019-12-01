package biblioteka;

public class Racun {
	
	private int brojRacuna;
	private String imeKorisnika;
	private int brojPosudKnjiga;
	
	
	Racun(int brRacuna, String imeKor) {
		
		this.brojRacuna = brRacuna;		
		this.imeKorisnika = imeKor;	
		
	}
	
	public void povecajBrojKnjiga() {
		if(brojPosudKnjiga == 3) {
			System.out.println("Broj posudjenih knjiga moze biti maksimalno 3");
			return;
		}
		else
			brojPosudKnjiga++;
	}
	
	public void smanjiBrojKnjiga() {
		if(brojPosudKnjiga < 0) {
			System.out.println("Stanje posudjenih knjiga ne moze biti negativno");
			return;
		}else
			brojPosudKnjiga--;
	}
	
	public int get_brojPosudKnjiga() {
		return brojPosudKnjiga;
	}
	
	public int get_brojRacuna() {
		return brojRacuna;
	}

	public String get_imeKorisnika() {
		return imeKorisnika;
	}
	
	public String detaljiRacuna() {
		return "———————————————————————————————————————————————\n"+
				" ID racuna: " + brojRacuna + "\n Korisnik: " + imeKorisnika +
				"\n Broj posudjenih knjiga: " + brojPosudKnjiga+
				"\n———————————————————————————————————————————————";
	}
	
}

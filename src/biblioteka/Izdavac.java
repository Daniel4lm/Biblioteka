package biblioteka;

public class Izdavac {
	
	private String naziv;
	private String lokacija;
		
	public Izdavac(String naz) {
		naziv = naz;
		lokacija = new String();
	}
	
	public Izdavac(String naz, String lok) {
		naziv = naz;
		lokacija = lok;
	}
	
	/* Getter i setter metode*/
	
	public String getNaziv() {
		return naziv;
	}

	public String getLokacija() {
		if(this.lokacija == null )
			System.out.println("Trazeni podatak nije unijet!");
		return lokacija;
	}

	public String izdavacInfo() {
		return (lokacija.length()>0)? naziv + " " + lokacija : naziv;
	}
}

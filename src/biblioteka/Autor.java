package biblioteka;

public class Autor {
	
	private String autorIme;
	private String authorPrezime;	
	private int godRod;
	private int godSm;	
	private String mjestoRodj;
	
	public Autor(String autorIme, String authorPrezime) {
		this.autorIme = autorIme;
		this.authorPrezime = authorPrezime;
		mjestoRodj = new String();
	}
	
	public Autor(String autorIme, String authorPrezime, int godRod, int godSm, String mjestoRodj) {
		this.autorIme = autorIme;
		this.authorPrezime = authorPrezime;	
		this.godRod = godRod;
		this.godSm = godSm;	
		this.mjestoRodj = mjestoRodj;
	}

	public String get_autorIme() {
		return this.autorIme;
	}

	public String get_authorPrezime() {
		return this.authorPrezime;
	}

	public int get_godRod() {
		
		if(this.godRod == 0 )
			System.out.println("Trazeni podatak nije unijet!");
		
		return this.godRod;			
	}

	public int get_godSm() {
		
		if(this.godSm == 0 )
			System.out.println("Trazeni podatak nije unijet!");
		
		return this.godSm;
	}

	public String get_mjestoRodj() {
		
		if(this.mjestoRodj.length() == 0 )
			System.out.println("Trazeni podatak nije unijet!");
		
		return this.mjestoRodj;
	}	
}

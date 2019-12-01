package biblioteka;

import java.util.Date;

public class Izdavanje {
	
	private int idRacuna;
	private int idKnjige;
	private Date datumIzd;
	
	public Izdavanje(int idRac, int idKnj, Date datum) {
		this.idRacuna = idRac;
		this.idKnjige = idKnj;
		this.datumIzd = datum;
	}

	public int get_idRacuna() {
		return idRacuna;
	}

	public int get_idKnjige() {
		return idKnjige;
	}

	public Date get_datumIzd() {
		return datumIzd;
	}

	public String izdavanjeInfo() {
		return " Broj racuna: " + idRacuna + "\n ID knjige: " + idKnjige + "\n Datum izdavanja: " + datumIzd;
	}
	
}

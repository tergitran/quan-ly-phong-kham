package DTO;

public class CachDungDTO {
	int MaCD;
	String CachDung;
	public int getMaCD() {
		return MaCD;
	}
	public void setMaCD(int maCD) {
		MaCD = maCD;
	}
	public String getCachDung() {
		return CachDung;
	}
	public void setCachDung(String cachDung) {
		CachDung = cachDung;
	}
	
	public String toString() {
		return MaCD + " - " + CachDung;
	}
	
}

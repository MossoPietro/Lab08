package it.polito.tdp.extflightdelays.model;

public class Rotta {

	private Airport partenza;
	private Airport arrivo;
	private double distanza;
	public Rotta(Airport partenza, Airport arrivo, double distanza) {
		super();
		this.partenza = partenza;
		this.arrivo = arrivo;
		this.distanza = distanza;
	}
	public Airport getPartenza() {
		return partenza;
	}
	public void setPartenza(Airport partenza) {
		this.partenza = partenza;
	}
	public Airport getArrivo() {
		return arrivo;
	}
	public void setArrivo(Airport arrivo) {
		this.arrivo = arrivo;
	}
	public double getDistanza() {
		return distanza;
	}
	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	@Override
	public String toString() {
		return this.partenza + " -- " + this.arrivo + " -- " + distanza;
	}

}

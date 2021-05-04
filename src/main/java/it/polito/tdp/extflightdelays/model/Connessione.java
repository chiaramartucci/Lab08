package it.polito.tdp.extflightdelays.model;

public class Connessione {

	private Airport airportOrigine;
	private Airport airportDestinazione;
	private double peso;
	public Connessione(Airport airportOrigine, Airport airportDestinazione, double peso) {
	
		
		super();
		this.airportOrigine = airportOrigine;
		this.airportDestinazione = airportDestinazione;
		this.peso = peso;
	}
	public Airport getAirportOrigine() {
		return airportOrigine;
	}
	public void setAirportOrigine(Airport airportOrigine) {
		this.airportOrigine = airportOrigine;
	}
	public Airport getAirportDestinazione() {
		return airportDestinazione;
	}
	public void setAirportDestinazione(Airport airportDestinazione) {
		this.airportDestinazione = airportDestinazione;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return airportOrigine.getAirportName() + airportDestinazione.getAirportName()+ this.peso+"\n";
	}
	
}
	
	
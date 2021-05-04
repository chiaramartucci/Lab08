package it.polito.tdp.extflightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		model.creaGrafo(4000);

		for(Connessione c: model.stampa()) {
			System.out.println(c.toString() );
		}
	}

}

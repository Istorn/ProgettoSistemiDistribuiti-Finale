package com.test.progetto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiocatoreService {
	private Map<Long, Giocatore> giocatori=DatabaseClass.getGiocatori();
	
	public GiocatoreService(){
		Giocatore g1=new Giocatore("Marco",9099,"194.84.32.1");
		g1.setId(giocatori.size()+1);
		giocatori.put(1L, g1);
	}
	public List<Giocatore> getAllGiocatori(){
		return new ArrayList<Giocatore>(giocatori.values());
		
	}
	
	public synchronized Giocatore getGiocatore(long id){
		return giocatori.get(id);
	}
	//Dobbiamo verificare che tutti gli attributi siano passati senza spazi
	public synchronized Giocatore addGiocatore(Giocatore giocatore){
		if ( (giocatore.getNome().contains(" ")!=true) && (giocatore.getIndirizzoIP().contains(" ")!=true) ){
			giocatore.setId(giocatori.size()+1);
			giocatori.put(giocatore.getID(), giocatore);
			return giocatore;
			
		}
		return null;
	}
	
	public synchronized Giocatore updateGiocatore(Giocatore giocatore){
		if (giocatore.getID()<=0){
			return null;
		}
		giocatori.put(giocatore.getID(), giocatore);
		return giocatore;
	}
	
	public synchronized Giocatore removeGiocatore(long id){
		return giocatori.remove(id);
	}
}

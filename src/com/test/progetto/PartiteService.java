package com.test.progetto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartiteService {
	private Map<Long, Partita> partite=DatabaseClass.getPartite();
	
	public PartiteService(){
		
	}
	//Aggiunge una partita 
	public synchronized Partita aggiungiPartita(Partita partitaDaAggiungere){
		if (this.indexPartitaPerNome(partitaDaAggiungere.getNome())>-1){
			return null;
		}
		if (partitaDaAggiungere.getNome().contains(" ")==true){
			return null;
		}
		partitaDaAggiungere.setId(this.partite.size()+1);
		this.partite.put(partitaDaAggiungere.getId(), partitaDaAggiungere);
		return partitaDaAggiungere;
	}
	//Fa aottenere tutti i dettagli di tutte le partite
	public synchronized List<Partita> getTuttePartite(){
		return new ArrayList<Partita>(this.partite.values());
	}
	//Fa ottenere tutti i dettagli di una specifica partita
	public synchronized Partita getPartita(long id){
		return this.partite.get(id);
	}
	//Rimuove una partita specifica
	public synchronized Partita rimuoviPartita(long id){
		if (id>-1){
			return this.partite.remove(id);
			
		}
		return null;
	}
	//Aggiunge un giocatore alla partita data in input se la partita esiste ed è possibile aggiungervi un giocatore
	public synchronized Partita aggiungiGiocatorePartita(Giocatore giocatore, Partita partita){
		if (this.indexPartitaPerNome(partita.getNome())>-1){
			if (this.partite.get(this.indexPartitaPerNome(partita.getNome())).aggiungiGiocatore(giocatore)==true){
				return this.partite.get(this.indexPartitaPerNome(partita.getNome()));
			}
			return null;
		}else{
			return null;
		}
	}
	//Rimozione di un giocatore dalla partita
	public synchronized Giocatore rimuoviGiocatorePartita(Giocatore giocatore, Partita partita){
		if (this.indexPartitaPerNome(partita.getNome())>-1){
			if (this.partite.get(this.indexPartitaPerNome(partita.getNome())).rimuoviGiocatore(giocatore)==true){
				return giocatore;
			}
		}
		return null;
	}
	//Se la partita non esiste torno -1
	public synchronized long indexPartitaPerNome(String nomePartita){
		for (int i=0;i<this.partite.size();i++){
			if (this.partite.get(i).getNome()==nomePartita){
				return i;
			}
		}
		return -1;
	}
	
}

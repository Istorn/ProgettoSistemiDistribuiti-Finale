package com.test.progetto;
import java.util.*;

//Classe per inviare i messaggi agli altri peer
//Si baseranno sull'operazione effettuata dal peer stesso e gli annessi 
//Parametri da comunicare
public class Messaggio {
	/*
	 * Costanti relative alla tipologia di messaggio invitato
	 * Possiamo avere:
	 * -Introdursi alla rete peer
	 * -Uscire dalla rete peer
	 * -Muoversi
	 * -Morire
	 * -Piazzare una bomba
	 * -Farla esplodere
	 * -Vincere la partita
	 * */
	public const IN:int=0;
	public const OUT:int=1;
	public const MOV:int=2;
	public const DIE:int=3;
	public const BOMB:int=4;
	public const EXPL:int=4;
	public const WIN:int=5;
	//Attributo di controllo affinchè si garantisca l'ack del messaggio da parte degli altri peer
	private int ACK;
	//Timestamp da associare a ogni messaggio
	private Date timestamp;
	//In base al numero impostato nel costruttore, sarà capita la tipologia di messaggio
	private int tipeMsg;
	//Bisogna impostare in maniera immediata il timestamp
	public Messaggio(int typeOfMsg){
		this.ACK=0;
		this.timestamp=new Date();
		this.tipeMsg=typeOfMsg;
		
	}
	//Verifica se un messaggio è stato letto dagli altri peer
	public boolean isACK(){
		if (this.ACK==1){
			return true;
		}
		return false;
	}
	
	
	
}

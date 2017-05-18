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
	public const EXPL:int=5;
	public const WIN:int=6;
	//Attributo di controllo affinchè si garantisca l'ack del messaggio da parte degli altri peer
	private int ACK;
	//Timestamp da associare a ogni messaggio
	private Date timestamp;
	//In base al numero impostato nel costruttore, sarà capita la tipologia di messaggio
	private int tipeMsg;
	//Nel caso di spostamento o bomba bisogna specificarne i dettagli
	private int x,y,bombType;
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
	
	//In base alla tipologia di messaggio individutata dal parametro "TypeOfMsg" dobbiamo impostarne gli attributi necessari
	public Messaggio builderMsg(int x, int y, int bombType){
		switch(this.tipeMsg){
			case 0:
				return this;
				
			case 1:
				return this;
				
			case 2:
				this.x=x;
				this.y=y;
				return this;
			case 2:
				return this;
			case 3:
				
				return this;
			case 4:
				this.bombType=bombType;
				return this;
			case 5:
				return this;
			case 6:
				return this;
			default:
				return null;
			
		}
	}
	
	public void setACK(){
		this.ACK=1;
		
	}
	
	
	
}

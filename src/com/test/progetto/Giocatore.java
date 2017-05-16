package com.test.progetto;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Giocatore {
	//Se seleziono la porta 0 tramite classe serversocket ne verrà associata una a caso liberamente
	private long id;
	private String nome;
	private String indirizzoIP;
	private long porta;
	public synchronized void setId(long id){
		this.id=id;
	}
	public synchronized long getID(){
		return this.id;
	}
	public Giocatore(){
		
	}
	public Giocatore(String nomeUtente,long portaDaSettare, String indirizzo){
		this.nome=nomeUtente;
		this.porta=portaDaSettare;
		this.indirizzoIP=indirizzo;
	}
	public synchronized String getNome() {
		return nome;
	}
	public synchronized void setNome(String nome) {
		this.nome = nome;
	}
	public synchronized String getIndirizzoIP() {
		return this.indirizzoIP;
	}
	public synchronized void setIndirizzoIP(String indirizzo){
		this.indirizzoIP=indirizzo;
	}
	public synchronized long getPorta() {
		return porta;
	}
	public synchronized void setPorta(long porta) {
		this.porta = porta;
	}
}

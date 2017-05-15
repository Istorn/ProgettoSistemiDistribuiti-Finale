package com.test.progetto;
import java.util.HashMap;
import java.util.Map;
public class DatabaseClass {
	private static Map<Long, Partita> partite=new HashMap<Long, Partita>();
	private static Map<Long, Giocatore> giocatori=new HashMap<Long, Giocatore>();
	
	public static synchronized Map<Long, Partita> getPartite(){
		return partite;
	}
	
	public static synchronized Map<Long, Giocatore> getGiocatori(){
		return giocatori;
	}
}

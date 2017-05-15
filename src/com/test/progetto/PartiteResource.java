package com.test.progetto;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.*;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationPath("/*")
@Path("/partite")
public class PartiteResource {
	PartiteService partiteService=new PartiteService();
	
	//Ottenimento del listato di tutte le partite
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public synchronized Response  getAllPartite(){
		List<Partita> partite= this.partiteService.getTuttePartite();
		if (partite.size()>0){
			return Response.ok(partite, MediaType.APPLICATION_XML).build();
		}
		return Response.serverError().entity("Non ci sono partite in corso!").build();
	}
	//Ottenimento di una partita in base all'id
	@GET
	@Path("/{partitaid}")
	@Produces(MediaType.APPLICATION_XML)
	public synchronized Response getPartita(@PathParam("partitaid") String id){
		
		Partita partita= this.partiteService.getPartita(Long.parseLong(id));
		if (partita!=null){
			return Response.ok(partita,MediaType.APPLICATION_XML).build();
		}
		return Response.serverError().entity("Partita non trovata").build();
	}
	//Rimozione di una specifica partita
	@DELETE
	@Path("/{partitaid}")
	@Produces(MediaType.APPLICATION_XML)
	public synchronized Response removePartita(@PathParam("partitaid") String id){
		if (this.partiteService.getPartita(Long.parseLong(id))!=null){
			Partita partita=this.partiteService.getPartita(Long.parseLong(id));
			this.partiteService.rimuoviPartita(Long.parseLong(id));
			return Response.ok(partita,MediaType.APPLICATION_XML).build();
		}
		return Response.serverError().entity("Partita non trovata!").build();
	}
	//Aggiunta di una partita con il giocatore che la crea: in base ai parametri dobbiamo dare delle risposte adeguate
	@POST
	@Path("/{nomepartita}/{punteggio}/{lato}/{giocatorenome}/{giocatoreip}/{giocatoreporta}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public synchronized Response addPartita(@PathParam("nomepartita") String nomePartita, @PathParam("giocatorenome") String giocatorenome, 
			@PathParam("giocatoreip") String giocatoreip, @PathParam("giocatoreporta")int giocatoreporta,
			@PathParam("punteggio") int punteggio, @PathParam("lato") int lato){
		//Se esiste già una partita con lo stesso nome, restituiamo errore
		if (this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita))!=null){
			return Response.status(403).entity("Partita già esistente").build();
		}
		else{
			//Aggiungiamo la partita con il giocatore annesso
			this.partiteService.aggiungiPartita(new Partita(nomePartita, new Giocatore(giocatorenome, giocatoreporta, giocatoreip), lato, punteggio));
			return Response.status(200).entity("Partita "+nomePartita+" creata!").build();
		}
		
	}
	//Aggiunta di un giocatore a una partita
	@PUT
	@Path("/{nomepartita}/{giocatorenome}/{giocatoreip}/{giocatoreporta}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public synchronized Response addGiocatorePartita(@PathParam("nomepartita") String nomePartita, @PathParam("giocatorenome") String giocatorenome, 
			@PathParam("giocatoreip") String giocatoreip, @PathParam("giocatoreporta")int giocatoreporta){
			//Se la partita esiste proseguiamo
			if (this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita))!=null){
			//Se non esiste un giocatore con lo stesso nome proseguiamo
				List<Giocatore> giocatori=this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita)).getGiocatori();
				for (int i=0;i<giocatori.size();i++){
					if (giocatori.get(i).getNome()==giocatorenome){
						return Response.status(403).entity("Esiste un giocatore con il nome inserito").build();
					}
				}
				this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita)).aggiungiGiocatore(new Giocatore(giocatorenome, giocatoreporta, giocatoreip));
				return Response.status(200).entity("Giocatore aggiunto!").build();
			}else{
				return Response.status(404).entity("Partita non trovata").build();
			}
	}	
	//Rimozione di un giocatore da una partita
	@PUT
	@Path("/rimuovigio/{nomepartita}/{giocatorenome}/{giocatoreip}/{giocatoreporta}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public synchronized Response rimuoviGiocatorePartita(@PathParam("nomepartita") String nomePartita, @PathParam("giocatorenome") String giocatorenome, 
			@PathParam("giocatoreip") String giocatoreip, @PathParam("giocatoreporta")int giocatoreporta){
			//Se la partita esiste proseguiamo
			if (this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita))!=null){
			//Se esiste un giocatore con lo stesso nome lo eliminiamo
				List<Giocatore> giocatori=this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita)).getGiocatori();
				for (int i=0;i<giocatori.size();i++){
					if (giocatori.get(i).getNome()==giocatorenome){
						Partita partita=this.partiteService.getPartita(this.partiteService.indexPartitaPerNome(nomePartita));
						this.partiteService.rimuoviGiocatorePartita(giocatori.get(i),partita);
						return Response.status(200).entity("Giocatore rimosso!").build(); 
					}
				}
				
				return Response.status(404).entity("Giocatore non trovato!").build();
			}else{
				return Response.status(404).entity("Partita non trovata").build();
			}
	}	
}

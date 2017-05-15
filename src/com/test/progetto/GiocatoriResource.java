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
import javax.ws.rs.core.Response.Status;



@ApplicationPath("/*")
@Path("/giocatori")
public class GiocatoriResource extends Application{
	GiocatoreService giocatoriService=new GiocatoreService();
	
	//Otteniamo il listato dei giocatori presenti nel server
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getGiocatori(){
		List<Giocatore> giocatori=giocatoriService.getAllGiocatori();
		if (giocatori.size()>0){
			return Response.ok(giocatori,MediaType.APPLICATION_XML).build();
		}
		return Response.serverError().entity("Non ci sono giocatori per questa partita!").build();
	}
	//Otteniamo un giocatore dato il suo id
	@GET
	@Path("/{giocatoreid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getGiocatore(@PathParam("giocatoreid") String id){
		
		Giocatore giocatore=giocatoriService.getGiocatore(Long.parseLong(id));
		if (giocatore!=null){
			return Response.ok(giocatore, MediaType.APPLICATION_XML).build();
		}
		return Response.serverError().entity("Giocatore non trovato!").build();

	}
	//Aggiunta di un giocatore
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addGiocatore(Giocatore giocatoreDaAggiungere){
		Giocatore nuovoGiocatore=giocatoriService.addGiocatore(giocatoreDaAggiungere);
		if (giocatoriService.getGiocatore(nuovoGiocatore.getID())==giocatoreDaAggiungere)
			return Response.ok(nuovoGiocatore, MediaType.APPLICATION_XML).build();
		return Response.serverError().entity("Giocatore già presente!").build();
		
	}
	//Modifichiamo i dati associati a uno specifico giocatore
	@PUT
	@Path("/{giocatoreid}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response aggiornaGiocatore(Giocatore giocatoreDaAggiornare){
		
		Giocatore giocatore= giocatoriService.updateGiocatore(giocatoreDaAggiornare);
		if (giocatore!=null){
			return Response.ok(giocatoreDaAggiornare,MediaType.APPLICATION_XML).build();
		}
		return Response.serverError().entity("Giocatore non trovato!").build();
	}
	
	//Eliminiamo un giocatore
	@DELETE
	@Path("/{giocatoreid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response eliminaGiocatore(@PathParam("giocatoreId") long id){
		if (giocatoriService.getGiocatore(id)!=null){
			giocatoriService.removeGiocatore(id);
			return Response.ok().build();
		}
		return Response.serverError().entity("Giocatore non trovato!").build();
		
	}
	
}

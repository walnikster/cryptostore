package com.uleos.cryptotools.server.btc.boundary;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.uleos.cryptotools.server.btc.control.BtcAdressService;
import com.uleos.cryptotools.server.btc.entity.BtcAdress;

@Stateless
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
@Path("btcadresses")
public class BtcAdressResource {

	@Inject
	BtcAdressService btcAdressService;

	@POST
	public Response addAdress(JsonObject json, @Context HttpServletRequest request) {
		BtcAdress btcAdress = new BtcAdress();
		btcAdress.setAdress(json.getString("adress"));
		btcAdress.setIp(request.getRemoteAddr());
		btcAdress = btcAdressService.create(btcAdress);
		return Response.created(URI.create("/" + btcAdress.getId())).build();
	}

	@GET
	public Response adresses() {
		List<BtcAdress> findAll = btcAdressService.findAll();

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		findAll.forEach(a -> jsonArrayBuilder.add(a.toJson()));
		return Response.ok(jsonArrayBuilder.build()).build();
	}

	@GET
	@Path("{id}")
	public BtcAdress adress(@PathParam("id") Long id) {
		return btcAdressService.findById(id);
	}

}

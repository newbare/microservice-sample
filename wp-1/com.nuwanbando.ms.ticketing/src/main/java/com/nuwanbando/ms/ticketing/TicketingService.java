/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nuwanbando.ms.ticketing;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

/**
 * This is a ticketing microservice
 * This service add new tickets to a Redis store
 * validate them and remove them
 */
@Path("/ticketingService")
public class TicketingService {
	
	private String TKT_EXPIRED = "EXPIRED";
	private String TKT_VALID = "VALID";

    @GET
    @Path("/tickets")
    public Response getAllTickets() {
        Jedis jedis = TicketingUtil.getJedis();
        Map<String, String> tickets = jedis.hgetAll("park-tickets");
        Gson gson = new Gson(); 
        //return gson.toJson(tickets);
        return Response.status(Response.Status.OK).entity(gson.toJson(tickets)).build();
    }

    @POST
    @Path("/ticket/{number}")
    public Response post(@PathParam("number") String number) throws URISyntaxException {
    	Jedis jedis = TicketingUtil.getJedis();
        jedis.hset("park-tickets", number, "VALID");
        return Response.status(Response.Status.CREATED).entity(number).build();
    }

    @PUT
    @Path("/validate/{number}")
    public Response put(@PathParam("number") String number) {
    	Jedis jedis = TicketingUtil.getJedis();
    	String tktStatus = jedis.hget("park-tickets", number);
    	if(tktStatus.equals(TKT_EXPIRED)){
    		return Response.status(Response.Status.NOT_FOUND).entity("").build();
    	}
        jedis.hset("park-tickets", number, "EXPIRED");
        return Response.ok().entity("").build();
    }

    @DELETE
    @Path("/ticket/{number}")
    public Response delete(@PathParam("number") String number) {
    	Jedis jedis = TicketingUtil.getJedis();
        jedis.hdel("park-tickets", number);
        return Response.ok().entity("").build();
    }
}

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

package com.orlando.ms.listing;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.orlando.ms.listing.TicketingUtil;

/**
 * This is the ticket listing service
 */
@Path("/ticketing")
public class ListService {

    @GET
    @Path("/tickets")
    public Response getAllTickets() {
        Jedis jedis = TicketingUtil.getJedis();
        Map<String, String> tickets = jedis.hgetAll("park-tickets");
        Gson gson = new Gson(); 
        //return gson.toJson(tickets);
        return Response.status(Response.Status.OK).entity(gson.toJson(tickets)).build();
    }
}

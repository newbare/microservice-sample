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

package com.orlando.ms.add;

import java.net.URISyntaxException;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import redis.clients.jedis.Jedis;

import com.orlando.ms.add.TicketingUtil;

/**
 * This is the ticket add/remove service
 */
@Path("/ticketing")
public class AddRemoveService {


    @POST
    @Path("/ticket/{number}")
    public Response post(@PathParam("number") String number) throws URISyntaxException {
    	Jedis jedis = TicketingUtil.getJedis();
        jedis.hset("park-tickets", number, "VALID");
        return Response.status(Response.Status.CREATED).entity(number).build();
    }
    
    @DELETE
    @Path("/ticket/{number}")
    public Response delete(@PathParam("number") String number) {
    	Jedis jedis = TicketingUtil.getJedis();
        jedis.hdel("park-tickets", number);
        return Response.ok().entity("").build();
    }
}

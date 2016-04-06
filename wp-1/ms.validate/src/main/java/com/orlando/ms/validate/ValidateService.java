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

package com.orlando.ms.validate;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import redis.clients.jedis.Jedis;

import com.orlando.ms.validate.TicketingUtil;

/**
 * This is the ticket validation service
 */
@Path("/ticketing")
public class ValidateService {
	
	private String TKT_EXPIRED = "EXPIRED";
	
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
}

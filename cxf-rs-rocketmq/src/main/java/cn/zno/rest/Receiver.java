package cn.zno.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.zno.pojo.ReceiverRequest;
import cn.zno.pojo.ReceiverResponse;

@Path("/receiver")
public interface Receiver {

	@POST
	@Path("/rece")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ReceiverResponse rece(ReceiverRequest receiverRequest);
}

package cn.zno.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.zno.pojo.News;
import cn.zno.pojo.Param;

@Path("/news")
public interface NewsService {

	@POST
	@Path("/requestNews")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public News requestNews(Param param);
}

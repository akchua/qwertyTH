package com.thesis.monitor.rest.endpoint;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.monitor.beans.ResultBean;
import com.thesis.monitor.beans.WatchlistFormBean;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.entity.UserBoardWatchlist;
import com.thesis.monitor.objects.ObjectList;
import com.thesis.monitor.rest.handler.UserBoardWatchlistHandler;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
@Path("/userboard")
public class UserBoardWatchlistEndpoint {

	@Autowired
	private UserBoardWatchlistHandler userBoardWatchlistHandler;
	
	@GET
	@Path("/boardlist")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Account> getBoardObjectList(@QueryParam("pageNumber") Integer pageNumber) {
		return userBoardWatchlistHandler.getBoardObjectList(pageNumber);
	}
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public UserBoardWatchlist getUserBoardWatchlist(@QueryParam("userBoardWatchlistId") Long userBoardWatchlistId) {
		return userBoardWatchlistHandler.getUserBoardWatchlist(userBoardWatchlistId);
	}
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean addBoardToWatchlist(@FormParam("boardUsername") String boardUsername) throws IOException {
		final WatchlistFormBean username = new ObjectMapper().readValue(boardUsername, WatchlistFormBean.class);
		return userBoardWatchlistHandler.addBoardToWatchlist(username.getBoardUsername());
	}
	
	@POST
	@Path("/remove")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean removeBoardFromWatchlist(@FormParam("boardId") Long boardId) {
		return userBoardWatchlistHandler.removeBoardFromWatchlist(boardId);
	}
}

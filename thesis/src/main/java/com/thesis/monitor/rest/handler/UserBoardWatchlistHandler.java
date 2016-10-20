package com.thesis.monitor.rest.handler;

import com.thesis.monitor.beans.ResultBean;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.entity.UserBoardWatchlist;
import com.thesis.monitor.objects.ObjectList;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
public interface UserBoardWatchlistHandler {

	ObjectList<Account> getBoardObjectList(Integer pageNumber);
	
	ObjectList<Account> getUserObjectList(Integer pageNumber, Long boardId);
	
	UserBoardWatchlist getUserBoardWatchlist(Long userBoardWatchlistId);
	
	ResultBean addBoardToWatchlist(String boardUsername);
	
	ResultBean removeBoardFromWatchlist(Long boardId);
}

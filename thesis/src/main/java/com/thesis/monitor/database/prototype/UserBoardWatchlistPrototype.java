package com.thesis.monitor.database.prototype;

import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.objects.ObjectList;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
public interface UserBoardWatchlistPrototype {

	ObjectList<Account> findAllBoardsWithPaging(int pageNumber, int resultsPerPage, Long userId);
	
	ObjectList<Account> findAllUsersWithPaging(int pageNumber, int resultsPerPage, Long boardId);
}

package com.thesis.monitor.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesis.monitor.database.dao.UserBoardWatchlistDAO;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.entity.UserBoardWatchlist;
import com.thesis.monitor.database.service.UserBoardWatchlistService;
import com.thesis.monitor.objects.ObjectList;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
@Service
public class UserBoardWatchlistServiceImpl
		extends AbstractService<UserBoardWatchlist, Long, UserBoardWatchlistDAO>
		implements UserBoardWatchlistService {

	@Autowired
	protected UserBoardWatchlistServiceImpl(UserBoardWatchlistDAO dao) {
		super(dao);
	}

	@Override
	public ObjectList<Account> findAllBoardsWithPaging(int pageNumber, int resultsPerPage, Long userId) {
		return dao.findAllBoardsWithPaging(pageNumber, resultsPerPage, userId);
	}

	@Override
	public ObjectList<Account> findAllUsersWithPaging(int pageNumber, int resultsPerPage, Long boardId) {
		return dao.findAllUsersWithPaging(pageNumber, resultsPerPage, boardId);
	}
}

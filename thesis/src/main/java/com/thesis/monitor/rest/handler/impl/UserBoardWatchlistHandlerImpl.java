package com.thesis.monitor.rest.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.monitor.AccountContextHolder;
import com.thesis.monitor.beans.ResultBean;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.entity.UserBoardWatchlist;
import com.thesis.monitor.database.service.AccountService;
import com.thesis.monitor.database.service.UserBoardWatchlistService;
import com.thesis.monitor.enums.AccountType;
import com.thesis.monitor.objects.ObjectList;
import com.thesis.monitor.rest.handler.UserBoardWatchlistHandler;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
@Transactional
@Component
public class UserBoardWatchlistHandlerImpl implements UserBoardWatchlistHandler {
	
	@Autowired
	private UserBoardWatchlistService userBoardWatchlistService;
	
	@Autowired
	private AccountService accountService;

	@Override
	public ObjectList<Account> getBoardObjectList(Integer pageNumber) {
		return userBoardWatchlistService.findAllBoardsWithPaging(pageNumber, AccountContextHolder.getItemsPerPage()
				, AccountContextHolder.getAccount().getAccountId());
	}

	@Override
	public ObjectList<Account> getUserObjectList(Integer pageNumber, Long boardId) {
		return userBoardWatchlistService.findAllUsersWithPaging(pageNumber, AccountContextHolder.getItemsPerPage(), boardId);
	}

	@Override
	public UserBoardWatchlist getUserBoardWatchlist(Long userBoardWatchlistId) {
		return userBoardWatchlistService.find(userBoardWatchlistId);
	}

	@Override
	public ResultBean addBoardToWatchlist(String boardUsername) {
		final ResultBean result;
		
		final Long userId = AccountContextHolder.getAccount().getAccountId();
		final Account board = accountService.findByUsername(boardUsername);
		
		if(board != null && board.getAccountType().equals(AccountType.BOARD)) {
			if(userBoardWatchlistService.isExistByBoardAndUserId(board.getId(), userId)) {
				result = new ResultBean(Boolean.FALSE, "Board \"" + boardUsername + "\" already in your watchlist.");
			} else {
				result = new ResultBean();
				
				UserBoardWatchlist ubw = new UserBoardWatchlist();
				ubw.setBoard(board);
				ubw.setUser(AccountContextHolder.getAccount().getAccountEntity());
				
				result.setSuccess(userBoardWatchlistService.insert(ubw) != null);
				
				if(result.isSuccess()) {
					result.setMessage("Successfully added board \"" + boardUsername + "\" to your watchlist.");
				} else {
					result.setMessage("Failed to add board \"" + boardUsername + "\" to your watchlist.");
				}
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "Board \"" + boardUsername + "\" does not exist.");
		}
		
		return result;
	}

	@Override
	public ResultBean removeBoardFromWatchlist(Long boardId) {
		final ResultBean result;
		
		final Long userId = AccountContextHolder.getAccount().getAccountId();
		final UserBoardWatchlist ubw = userBoardWatchlistService.findByBoardAndUserId(boardId, userId);
		
		if(ubw != null) {
			result = new ResultBean();
			
			result.setSuccess(userBoardWatchlistService.delete(ubw));
			
			if(result.isSuccess()) {
				result.setMessage("Successfully removed Board of id \"" + boardId + "\" from your watchlist.");
			} else {
				result.setMessage("Failed to remove Board of id \"" + boardId + "\" from your watchlist.");
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "Board of id \"" + boardId + "\" not on your watchlist.");
		}
		
		return result;
	}
}

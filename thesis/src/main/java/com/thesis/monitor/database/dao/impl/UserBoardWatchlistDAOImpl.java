package com.thesis.monitor.database.dao.impl;

import java.util.stream.Collectors;

import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thesis.monitor.database.dao.UserBoardWatchlistDAO;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.entity.UserBoardWatchlist;
import com.thesis.monitor.objects.ObjectList;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
@Repository
public class UserBoardWatchlistDAOImpl
		extends AbstractDAO<UserBoardWatchlist, Long>
		implements UserBoardWatchlistDAO {

	@Override
	public ObjectList<Account> findAllBoardsWithPaging(int pageNumber, int resultsPerPage, Long userId) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("userId", userId));
		
		final ObjectList<UserBoardWatchlist> ubws = 
				findAllByCriterion(pageNumber, resultsPerPage, null, null, null, null, conjunction);
		final ObjectList<Account> boardList = new ObjectList<Account>();
		
		boardList.setList(ubws.getList()
							.stream()
							.map(ubw -> ubw.getBoard())
							.collect(Collectors.toList()));
		boardList.setTotal(ubws.getTotal());
		
		return boardList;
	}

	@Override
	public ObjectList<Account> findAllUsersWithPaging(int pageNumber, int resultsPerPage, Long boardId) {
		final Junction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("isValid", Boolean.TRUE));
		conjunction.add(Restrictions.eq("boardId", boardId));
		
		final ObjectList<UserBoardWatchlist> ubws = 
				findAllByCriterion(pageNumber, resultsPerPage, null, null, null, null, conjunction);
		final ObjectList<Account> userList = new ObjectList<Account>();
		
		userList.setList(ubws.getList()
							.stream()
							.map(ubw -> ubw.getUser())
							.collect(Collectors.toList()));
		userList.setTotal(ubws.getTotal());
		
		return userList;
	}
}

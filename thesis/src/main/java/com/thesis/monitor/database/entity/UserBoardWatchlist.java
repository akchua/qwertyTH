package com.thesis.monitor.database.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesis.monitor.database.entity.base.BaseObject;
import com.thesis.monitor.serializer.json.AccountSerializer;

/**
 * @author Adrian Jasper K. Chua
 * 
 * @version 1.0, Oct 20, 2016
 */
@Entity(name = "UserBoardWatchlist")
@Table(name = UserBoardWatchlist.TABLE_NAME)
public class UserBoardWatchlist extends BaseObject {

	private static final long serialVersionUID = 1839128764535418934L;
	
	public final static String TABLE_NAME = "user_board_watchlist";
	
	@JsonSerialize(using = AccountSerializer.class)
	private Account user;
	
	@JsonSerialize(using = AccountSerializer.class)
	private Account board;

	@ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	@ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	@Where(clause = "valid = 1")
	@NotFound(action = NotFoundAction.IGNORE)
	public Account getBoard() {
		return board;
	}

	public void setBoard(Account board) {
		this.board = board;
	}
}

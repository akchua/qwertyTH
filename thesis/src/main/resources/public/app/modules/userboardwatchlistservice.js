define(['jquery'], function ($) {
	return {
		getBoardList: function(pageNumber) {
			return $.ajax({
				url: '/services/userboard/boardlist',
				data: {
					pageNumber: pageNumber - 1
				}
			});
		},
		
		getUserBoardWatchlist: function(userBoardWatchlistId) {
			return $.ajax({
				url: '/services/userboard/get',
				data: {
					userBoardWatchlistId: userBoardWatchlistId
				}
			});
		},
		
		addBoardToWatchlist: function(boardUsername) {
			return $.ajax({
				url: '/services/userboard/save',
				method: 'POST',
				data: {
					boardUsername: boardUsername
				}
			});
		},
		
		removeBoardFromWatchlist: function(boardId) {
			return $.ajax({
				url: '/services/userboard/remove',
				method: 'POST',
				data: {
					boardId: boardId
				}
			});
		}
	};
});
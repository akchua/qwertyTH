define(['durandal/app', 'knockout', 'modules/userboardwatchlistservice', 'viewmodels/user/watchlistForm'], function (app, ko, userBoardWatchlistService, WatchlistForm) {
    var Watchlist = function() {
    	this.boardList = ko.observable();
    	
    	this.itemsPerPage = ko.observable(10);
    	this.totalItems = ko.observable();
    	this.currentPage = ko.observable(1);
    	this.currentPageSubscription = null;
    };
    
    Watchlist.prototype.activate = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshWatchlist();
		});
		
		self.refreshWatchlist();
    };
    
    Watchlist.prototype.refreshWatchlist = function() {
    	var self = this;
    	
    	userBoardWatchlistService.getBoardList(self.currentPage()).done(function(data) {
    		self.boardList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Watchlist.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshWatchlist();
    };
    
    Watchlist.prototype.add = function() {
    	var self = this;
    	
		WatchlistForm.show().then(function() {
			self.refreshWatchlist();
		});
    };
    
    Watchlist.prototype.remove = function(boardId, username) {
    	var self = this;
    	
    	app.showMessage('Are you sure you want to remove Board ' + username + '?',
    			'Confirm Remove',
    			[{ text: 'Yes', value: true }, { text: 'No', value: false }])
		.then(function(confirm) {
			if(confirm) {
				userBoardWatchlistService.removeBoardFromWatchlist(boardId).done(function(result) {
					self.refreshWatchlist();
					app.showMessage(result.message);
				});
			}
		})
    };
    
    return Watchlist;
});
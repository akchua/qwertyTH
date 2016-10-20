define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/userboardwatchlistservice'], function(dialog, app, ko, userBoardWatchlistService) {
	var WatchlistForm = function() {
		this.watchlistFormModel = {
			boardUsername: ko.observable()
		};
	};
	
	WatchlistForm.prototype.save = function() {
		var self = this;
		
		userBoardWatchlistService.addBoardToWatchlist(ko.toJSON(self.watchlistFormModel)).done(function(result) {
			if(result.success) {
				dialog.close(self);
			}
			app.showMessage(result.message);
		});
	};
	
	WatchlistForm.show = function() {
		return dialog.show(new WatchlistForm());
	};
	
	WatchlistForm.prototype.cancel = function() {
		dialog.close(this);
	};
	
	return WatchlistForm;
});
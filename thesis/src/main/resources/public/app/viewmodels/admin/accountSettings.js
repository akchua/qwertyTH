define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/accountservice'], function(dialog, app, ko, accountService) {
	var AccountSettings = function(account) {
		this.account = account;
		this.accountList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(10);
    	this.totalItems = ko.observable();
    	this.currentPage = ko.observable(1);
    	this.currentPageSubscription = null;
		
		this.accountSettingsModel = {
			username: ko.observable(),
			password: ko.observable(),
			passwordNew: ko.observable(),
			passwordNewConfirm: ko.observable(),
			itemsPerPage: ko.observable(),
			accountType : ko.observable()
		};
	};
	
	AccountSettings.prototype.activate = function() {
		var self = this;
		self.accountSettingsModel.username(self.account.username);
		self.accountSettingsModel.itemsPerPage(self.account.itemsPerPage);
		self.accountSettingsModel.accountType(self.account.accountType);
		
		
	};
	
	 Account.prototype.refreshAccountList = function() {
	    	var self = this;
	    	
	    	accountService.getAccountList(self.currentPage(), self.searchKey()).done(function(data) {
	    		self.accountList(data.list);
	    		self.totalItems(data.total);
	    	});
	    };
	
	/*AccountSettings.prototype.save = function() {
		var self = this;
		
		accountService.saveAccount(ko.toJSON(self.accountSettingsModel)).done(function(result) {
			if(result.success) {
				dialog.close(self);
			}
			app.showMessage(result.message);
		});
	};*/
	
	
	AccountSettings.show = function(account) {
		return dialog.show(new AccountSettings(account));
	};
	
	AccountSettings.prototype.cancel = function() {
		dialog.close(this);
	};
	
	return AccountSettings;
});
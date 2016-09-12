define(['plugins/dialog', 'durandal/app', 'knockout'], function(dialog, app, ko) {
	var AccountForm = function(account) {
		this.account = account;
		
		this.AccountFormModel = {
			id: ko.observable(),
			username: ko.observable(),
			password: ko.observable(),
			itemsPerPage: ko.observable(),
			accountType: ko.observable()
		};
	};
	
	AccountForm.prototype.activate = function() {
		var self = this;
		
		self.AccountFormModel.id(self.account.id);
		self.AccountFormModel.username(self.account.username);
		self.AccountFormModel.itemsPerPage(self.account.itemsPerPage);
		self.AccountFormModel.accountType(self.account.accountType);
	};
	
	AccountForm.show = function(account) {
		return dialog.show(new AccountForm(account));
	};
	
	AccountForm.prototype.cancel = function() {
		dialog.close(this);
	};
	
	return AccountForm;
});
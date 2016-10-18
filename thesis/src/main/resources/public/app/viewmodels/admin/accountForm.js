define(['plugins/dialog', 'durandal/app', 'knockout', 'modules/accountservice'], function(dialog, app, ko, accountService) {
	var AccountForm = function(preTitle, account) {
		this.preTitle = preTitle;

		this.account = account;
		
		this.accountTypeList = ko.observable();
		
		this.itemsPerPageList = ko.observableArray([5, 10, 15, 20]);
		
		this.accountFormModel = {
			id: ko.observable(),
			username: ko.observable(),
			password: ko.observable(),
			itemsPerPage: ko.observable(),
			accountType: ko.observable()
		};
	};
	
	AccountForm.prototype.activate = function() {
		var self = this;
		
		self.accountFormModel.id(self.account.id);
		self.accountFormModel.username(self.account.username);
		self.accountFormModel.itemsPerPage(self.account.itemsPerPage);
		self.accountFormModel.accountType(self.account.accountType);
		
		accountService.getAccountTypeList().done(function(accountTypeList) {
			self.accountTypeList(accountTypeList);
			self.accountFormModel.accountType(self.account.accountType);
		});
	};
	
	AccountForm.prototype.save = function() {
		var self = this;
		
		accountService.saveAccount(ko.toJSON(self.accountFormModel)).done(function(result) {
			if(result.success) {
				dialog.close(self);
			}
			app.showMessage(result.message);
		});
	};
	
	AccountForm.show = function(preTitle, account) {
		return dialog.show(new AccountForm(preTitle, account));
	};
	
	AccountForm.prototype.cancel = function() {
		dialog.close(this);
	};
	
	return AccountForm;
});
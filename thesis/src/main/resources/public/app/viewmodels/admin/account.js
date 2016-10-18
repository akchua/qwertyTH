define(['knockout', 'modules/accountservice', 'viewmodels/admin/accountForm'], function (ko, accountService, AccountForm) {
    var Account = function() {
    	this.accountList = ko.observable();
    	
    	this.searchKey = ko.observable();
    	
    	this.itemsPerPage = ko.observable(10);
    	this.totalItems = ko.observable();
    	this.currentPage = ko.observable(1);
    	this.currentPageSubscription = null;
    };
    
    Account.prototype.activate = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.currentPageSubscription = self.currentPage.subscribe(function() {
			self.refreshAccountList();
		});
		
		self.refreshAccountList();
    };
    
    Account.prototype.refreshAccountList = function() {
    	var self = this;
    	
    	accountService.getAccountList(self.currentPage(), self.searchKey()).done(function(data) {
    		self.accountList(data.list);
    		self.totalItems(data.total);
    	});
    };
    
    Account.prototype.search = function() {
    	var self = this;
    	
    	self.currentPage(1);
    	self.refreshAccountList();
    };
    
    Account.prototype.add = function() {
    	var self = this;
    	
		AccountForm.show('Create', new Object()).then(function() {
			self.refreshAccountList();
		});
    };
    
    Account.prototype.edit = function(accountId) {
    	var self = this;
    	
    	accountService.getAccount(accountId).done(function(account) {
    		AccountForm.show('Edit', account).then(function() {
    			self.refreshAccountList();
    		});
    	});
    };
    
    return Account;
});
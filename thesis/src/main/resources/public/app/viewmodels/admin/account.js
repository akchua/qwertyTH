define(['knockout', 'modules/accountservice'], function (ko, accountService) {
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
    
    return Account;
});
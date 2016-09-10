define(['knockout'], function (ko) {
    var Account = function() {
    	this.test = ko.observable(4);
    };
    
    Account.prototype.change = function() {
    	var self = this;
    	
    	self.test(self.test() + 1);
    };
    
    return Account;
});
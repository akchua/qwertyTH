define(['knockout'], function (ko) {
    var Board = function() {
    	this.test = ko.observable(4);
    };
    
    Board.prototype.change = function() {
    	var self = this;
    	
    	self.test(self.test() + 1);
    };
    
    return Board;
});
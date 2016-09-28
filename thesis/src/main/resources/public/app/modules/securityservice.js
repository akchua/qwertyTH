define(['jquery'], function ($) {
    return {
    	getAccount: function() {
    		return $.ajax({
    			url: '/services/security/account'
    		});
    	},
    	
    	login: function(username, password) {
    		return $.ajax({
    			url: '/login',
    			method: 'POST',
    			data: {
    				username: username,
    				password: password
    			}
    		});
    	},
    	
    	logout: function() {
    		return $.ajax({
    			url: '/services/security/logout',
    			method: 'POST'
    		});
    	}
    };
});
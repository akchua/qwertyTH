define(['jquery'], function ($) {
	return {
		getAccountList: function(pageNumber, searchKey) {
			return $.ajax({
				url: '/services/account/list',
				data: {
					pageNumber: pageNumber - 1,
					searchKey, searchKey
				}
			});
		},
		
		getAccount: function(accountId) {
			return $.ajax({
				url: '/services/account/get',
				data: {
					accountId: accountId
				}
			});
		},
		
		getAccountTypeList: function() {
			return $.ajax({
				url: '/services/account/accounttype'
			});
		},
		
		saveAccount: function(accountFormData) {
			return $.ajax({
				url: '/services/account/save',
				method: 'POST',
				data: {
					accountFormData: accountFormData
				}
			});
		},
		
		removeAccount: function(accountId) {
			return $.ajax({
				url: '/services/account/remove',
				method: 'POST',
				data: {
					accountId: accountId
				}
			});
		}
	};
});
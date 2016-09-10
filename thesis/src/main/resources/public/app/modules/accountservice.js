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
		}
	};
});
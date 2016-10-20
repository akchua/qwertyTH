define(['plugins/router', 'durandal/app', 'modules/securityservice'], function (router, app, securityService) {
	var homeroute = [
	    { route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: true }
	];
	
	var adminroute = [
	    { route: 'account', moduleId: 'viewmodels/admin/account', title: 'Account', nav: true, hash: '#account' }              
	];
	
    return {
        router: router,
        
        account: app.user,
        
        activate: function () {
        	var self = this;
        	var routes;
        	
        	switch(self.account.accountType) {
        		case 'ADMINISTRATOR':
        			routes = homeroute;
        			routes = routes.concat(adminroute);
        			break;
        		case 'USER':
        			routes = homeroute;
        			break;
        		case 'BOARD':
        			routes = homeroute;
        			break;
        	}
        	
        	$.each(routes, function(index, route) {
                if (route.childRoutes === undefined)
                    return
                $.each(route.childRoutes, function(index, childRoute) {
                    childRoute.route = route.route + '/' + childRoute.route;
                    childRoute.moduleId = route.moduleRootId + '/' + childRoute.moduleId;
                    childRoute.title = childRoute.title;
                    childRoute.hash = route.hash + '/' + childRoute.hash;
                    childRoute.parent = route.moduleRootId;
                });
                routes = routes.concat(route.childRoutes);
            });
        	
            router.map(routes).buildNavigationModel();
            
            return router.activate();
        },
        
        logout: function() {
        	securityService.logout().done(function() {
        		location.href = '/';
        	});
        }
    };
});
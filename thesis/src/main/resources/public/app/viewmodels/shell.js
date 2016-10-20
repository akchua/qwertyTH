define(['plugins/router', 'durandal/app', 'modules/securityservice', 'viewmodels/admin/accountSetting'], function (router, app, securityService, accountSetting) {
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
        	var routes = homeroute;
        	
        	routes = routes.concat(adminroute);
        	
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
        },
        
	    settings: function() {
	    	var self = this;
	    	accountSetting.show(self.account).done(function(){});
	    }
    };
});
define(['plugins/router', 'durandal/app'], function (router, app) {
	var homeroute = [
	    { route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: true }
	];
	
	var adminroute = [
	    { route: 'board', moduleId: 'viewmodels/admin/board', title: 'Board', nav: true, hash: '#board' }              
	];
	
    return {
        router: router,
        
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
        }
    };
});
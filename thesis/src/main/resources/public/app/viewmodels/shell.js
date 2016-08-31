﻿define(['plugins/router', 'durandal/app', 'modules/securityservice', 'modules/userservice', 'viewmodels/usersettings'], function (router, app, securityService, userService, UserSettings) {
	var homeroute = [
	    { route: ['', 'home'], moduleId: 'viewmodels/home', title: 'Home', nav: true }
	];
	
    return {
        router: router,
        
        activate: function () {
        	var self = this;
        	var routes = homeroute;
        	
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
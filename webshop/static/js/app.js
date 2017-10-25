(function(angular) {
	'use strict';
	
	angular.module('webShopApp', ['ngRoute', 'restangular', 'lodash', 'main']).config(configure).run(runBlock);
	configure.$inject = ['$routeProvider', 'RestangularProvider'];
	runBlock.$inject = ['Restangular'];
	
	function configure($routeProvider, RestangularProvider) {
		$routeProvider.when('/', {
			templateUrl : 'views/main.html',
			controller  : 'MainController',
			controllerAs: 'mainCtrl'
		}).when('/login', {
			templateUrl : 'views/login.html'
		}).when('/signup', {
			templateUrl : 'views/signup.html'
		}).when('/profile', {
			templateUrl : 'views/profile.html'
		}).when('/registerSeller', {
			templateUrl : 'views/registerSeller.html'
		}).when('/shoppingCart', {
			templateUrl : 'views/shoppingCart.html'
		}).otherwise({
			redirectTo : '/'
		});
	}
	
	function runBlock(Restangular, $log) {
		Restangular.setBaseUrl('webshop');
		Restangular.setErrorInterceptor(function(response) {
			if (response.status === 500) {
				$log.info("internal server error");
				return true;
			}
			return true;
		});
	}
	
})(angular);
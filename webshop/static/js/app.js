(function(angular) {
	'use strict';
	
	angular.module('webShopApp',
			['ngRoute', 'restangular', 'ngStorage', 'lodash',
			 'main', 'items', 'cart', 'user', 'login', 'seller', 'manager'])
			.config(configure).run(runBlock);
	
	configure.$inject = ['$routeProvider', 'RestangularProvider'];
	runBlock.$inject = ['Restangular', '$log'];
	
	function configure($routeProvider, RestangularProvider) {
		$routeProvider.when('/', {
			templateUrl : 'views/welcome.html'
		}).when('/main', {
			templateUrl : 'views/main.html',
			controller  : 'MainController',
			controllerAs: 'mainCtrl'
		}).when('/login', {
			templateUrl : 'views/login.html',
			controller  : 'LoginController',
			controllerAs: 'loginCtrl'
		}).when('/signup', {
			templateUrl : 'views/signup.html',
			controller  : 'LoginController',
			controllerAs: 'loginCtrl'
		}).when('/profile', {
			templateUrl : 'views/profile.html',
			controller  : 'UserController',
			controllerAs: 'userCtrl'
		}).when('/registerSeller', {
			templateUrl : 'views/registerSeller.html',
			controller  : 'UserController',
			controllerAs: 'userCtrl'
		}).when('/shoppingCart', {
			templateUrl : 'views/shoppingCart.html',
			controller  : 'ShoppingCartController',
			controllerAs: 'cartCtrl'
		}).when('/checkoutCounter', {
			templateUrl : 'views/checkoutCounter.html',
			controller  : 'ShoppingCartController',
			controllerAs: 'cartCtrl'
		}).when('/invoices', {
			templateUrl : 'views/invoices.html',
			controller  : 'SellerController',
			controllerAs: 'sellerCtrl'
		}).when('/order', {
			templateUrl : 'views/order.html',
			controller  : 'SellerController',
			controllerAs: 'sellerCtrl'
		}).when('/preferences', {
			templateUrl : 'views/preferences.html',
			controller  : 'ManagerController',
			controllerAs: 'managerCtrl'
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
(function(angular) {
	'use strict';
	angular.module('user', []).controller('UserController', UserController);
	UserController.$inject = ['$routeParams', '$location', 'UserService'];

	function UserController($routeParams, $location, UserService) {
		var vm = this;
		var seller = {email: "", password: "", name: "", surname: ""};

		vm.registerSeller = function(seller) {
			UserService.registerSeller(seller).then(function(seller) {
				window.location = "/";
		 	});
		}
	};

})(angular);
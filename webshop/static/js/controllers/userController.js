(function(angular) {
	'use strict';
	angular.module('user', []).controller('UserController', UserController);
	UserController.$inject = ['$routeParams', '$location', '$localStorage', 'UserService'];

	function UserController($routeParams, $location, $localStorage, UserService) {
		var vm = this;
		var seller = {email: "", password: "", name: "", surname: ""};
		
		vm.user = $localStorage.user;
		
		var regDate = new Date(vm.user.registrationDate);
		regDate = regDate.toLocaleDateString();
		vm.regDate = regDate;

		vm.registerSeller = function(seller) {
			UserService.registerSeller(seller).then(function() {
				window.location = "/main";
		 	});
		}

		var id = vm.user.id;
		vm.getInvoices = function(id) {
			UserService.getInvoices(id).then(function(invoices) {
				vm.invoices = invoices;
		 	});
		}
		vm.getInvoices(id);
	};

})(angular);
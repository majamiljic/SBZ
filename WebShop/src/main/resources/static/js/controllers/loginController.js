(function(angular) {
	'use strict';
	angular.module('login', []).controller('LoginController', LoginController);
	LoginController.$inject = ['$routeParams', '$location', '$localStorage', 'UserService'];

	function LoginController($routeParams, $location, $localStorage, UserService) {
		var vm = this;
		vm.regUser = {};
		$("#alert").hide();
		$("#regAlert").hide();
		
		vm.loginUser = {
			email: '',
			password: ''
		};
		
		vm.loggedUser = function() {
			var logged = $localStorage.user;
			if(logged == null)
				return true;
			else
				return false;
		}
		
		if($localStorage.user != null){
			var role = $localStorage.user.role;
			vm.seller = function() {
				if(role == "Seller")
					return true;
				else
					return false;
			}
			vm.manager = function() { 
				if(role == "Manager")
					return true;
				else
					return false;
			}
		}
		
		vm.login = function(){
			UserService.login(vm.loginUser).then(function(response){
				if(response != null){
					$localStorage.user = response;
					$location.path('/main');
				}
			});
		}
		
		vm.register = function() {
			vm.regUser.registrationDate = new Date();
			UserService.registrate(vm.regUser).then(function(response) {
				if(response != null){
					$localStorage.user = response;
					$location.path('/login');
				}
			});
		}
		
		vm.logout = function(){
			$localStorage.$reset();
			$location.path('/');
		}

	};

})(angular);
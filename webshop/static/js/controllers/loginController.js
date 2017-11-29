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
			vm.seller = function() {
				if($localStorage.user != null){
					if($localStorage.user.role == "Seller")
						return true;
					else
						return false;
				}
			}
			vm.manager = function() {
				if($localStorage.user != null){
					if($localStorage.user.role == "Manager")
						return true;
					else
						return false;
				}
			}
		}
		
		vm.login = function(){
			console.log(vm.loginUser);
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
					$location.path('/main');
				}
			});
		}
		
		vm.logout = function(){
			$localStorage.$reset();
			$location.path('/');
		}

	};

})(angular);
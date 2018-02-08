angular.module('user').factory('UserService', UserService);
UserService.$inject = ['Restangular'];

function UserService(Restangular) {
	var retVal = {};

	retVal.login = function(loginUser) {
		return Restangular.one('user/login').customPOST(loginUser).then(
			function(response) {
				return response;
			},
			function() {
				$("#alert").show();
				return null;
			});
	};

	retVal.registrate = function(regUser) {
		return Restangular.one('user/registrate').customPOST(regUser).then(
			function(response) {
				return response;
			},
			function() {
				$("#regAlert").show();
				return null;
			});
	};

	retVal.registerSeller = function(user) {
		return Restangular.one('user/registerSeller').put(user);
	};

	retVal.getInvoices = function(id) {
		return Restangular.one('user/getInvoices', id).getList().then(function(results) {
			return results;
		});
	};

	return retVal;
}
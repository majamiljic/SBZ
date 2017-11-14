angular.module('user').factory('UserService', UserService);
UserService.$inject = ['Restangular'];

function UserService(Restangular) {
	var retVal = {};

	retVal.registerSeller = function(user) {
		return Restangular.one('user/registerSeller').put(user).then(function(results) {
			return results;
		});
	};

	return retVal;
}
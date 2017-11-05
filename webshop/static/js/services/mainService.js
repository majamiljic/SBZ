angular.module('main').factory('MainService', MainService);
MainService.$inject = ['Restangular'];

function MainService(Restangular) {
	var retVal = {};

	retVal.getItems = function() {
		return Restangular.all('items/getAll').getList().then(function(results) {
			return results;
		});
	};

	retVal.getCategories = function() {
		return Restangular.all('items/getCategories').getList().then(function(results) {
			return results;
		});
	};

	return retVal;
}
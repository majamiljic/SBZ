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

	retVal.getItemsByName = function(name) {
		return Restangular.one('items/getItemsByName', name).getList().then(
			function(results) {
				return results;
			},
			function() {
				return null;
			});
	};

	retVal.getItemByCode = function(code) {
		return Restangular.one('items/getItemByCode', code).get().then(
			function(results) {
				return results;
			},
			function() {
				return null;
			});
	};

	retVal.getItemsByCategory = function(id) {
		return Restangular.one('items/getItemsByCategory', id).get().then(
			function(results) {
				return results;
			},
			function() {
				return null;
			});
	};

	return retVal;
}
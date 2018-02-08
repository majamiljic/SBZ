angular.module('items').factory('ItemService', ItemService);
ItemService.$inject = ['Restangular'];

function ItemService(Restangular) {
	var retVal = {};

	retVal.getItems = function() {
		return Restangular.all('items/getAll').getList().then(function(results) {
			return results;
		});
	};

	return retVal;
}
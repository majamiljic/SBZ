angular.module('cart').factory('ShoppingCartService', ShoppingCartService);
ShoppingCartService.$inject = ['Restangular'];

function ShoppingCartService(Restangular) {
	var retVal = {};

	retVal.purchase = function(cart) {
		return Restangular.all('items/purchase').post(cart).then(function(results) {
			return results;
		});
	};

	return retVal;
}
angular.module('seller').factory('SellerService', SellerService);
SellerService.$inject = ['Restangular'];

function SellerService(Restangular) {
	var retVal = {};

	retVal.getInvoices = function() {
		return Restangular.all('seller/getInvoices').getList().then(function(results) {
			return results;
		});
	};

	retVal.approve = function(id) {
		return Restangular.one('seller/approve', id).getList().then(
			function(results) {
				return results;
			},
			function() {
				return null;
			});
	};

	retVal.decline = function(id) {
		return Restangular.one('seller/decline', id).getList().then(function(results) {
			return results;
		});
	};

	return retVal;
}
angular.module('seller').factory('SellerService', SellerService);
SellerService.$inject = ['Restangular'];

function SellerService(Restangular) {
	var retVal = {};

	retVal.getAllInvoices = function() {
		return Restangular.all('seller/getAllInvoices').getList().then(function(results) {
			return results;
		});
	};

	retVal.getInvoicesByStatus = function(status) {
		return Restangular.one('seller/getInvoicesByStatus', status).getList().then(function(results) {
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

	retVal.getItemsToRefill = function() {
		return Restangular.all('seller/getItemsToRefill').getList().then(function(results) {
			return results;
		});
	};

	retVal.refill = function(numberOfItems) {
		return Restangular.one('seller/refill').customPOST(numberOfItems);
	};

	return retVal;
}
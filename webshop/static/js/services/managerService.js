angular.module('manager').factory('ManagerService', ManagerService);
ManagerService.$inject = ['Restangular'];

function ManagerService(Restangular) {
	var retVal = {};

	retVal.getCategories = function() {
		return Restangular.all('manager/getCategories').getList().then(function(results) {
			return results;
		});
	};

	retVal.getSpecialOffers = function() {
		return Restangular.all('manager/getOffers').getList().then(function(results) {
			return results;
		});
	};

	retVal.updateItemCategory = function(selected) {
		return Restangular.one('manager/updateItemCategory').customPOST(selected);
	};

	retVal.updateOffer = function(selected) {
		return Restangular.one('manager/updateOffer').customPOST(selected);
	};

	retVal.addItemCategory = function(newCategory) {
		return Restangular.one('manager/addItemCategory').customPOST(newCategory).then(
			function(results) {
				return results;
			},
			function() {
				return null;
			});
	};

	retVal.addOffer = function(newOffer) {
		return Restangular.one('manager/addOffer').customPOST(newOffer).then(
			function(results) {
				return results;
			},
			function() {
				return null;
			});
	};

	return retVal;
}
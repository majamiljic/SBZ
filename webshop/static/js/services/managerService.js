angular.module('manager').factory('ManagerService', ManagerService);
ManagerService.$inject = ['Restangular'];

function ManagerService(Restangular) {
	var retVal = {};

	retVal.getCategories = function() {
		return Restangular.all('manager/getCategories').getList().then(function(results) {
			return results;
		});
	};

	retVal.getParentCategories = function() {
		return Restangular.all('manager/getParentCategories').getList().then(function(results) {
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

	retVal.getUserCategories = function() {
		return Restangular.all('manager/getUserCategories').getList().then(function(results) {
			return results;
		});
	};

	retVal.getSpendingBoundariesByUserCategory = function(id) {
		return Restangular.one('manager/getSpendingBoundariesByUserCategory', id).getList().then(function(results) {
			return results;
		});
	};

	retVal.addSpendingBoundary = function(spendingBoundary) {
		return Restangular.one('manager/addSpendingBoundary').customPOST(spendingBoundary).then(function(results) {
			return results;
		});
	};

	retVal.updateSpendingBoundary = function(spendingBoundary) {
		return Restangular.one('manager/updateSpendingBoundary').customPOST(spendingBoundary).then(function(results) {
			return results;
		});
	};

	return retVal;
}
angular.module('manager').factory('ManagerService', ManagerService);
ManagerService.$inject = ['Restangular'];

function ManagerService(Restangular) {
	var retVal = {};

	retVal.getSpecialOffers = function() {
		return Restangular.all('manager/getAll').getList().then(function(results) {
			return results;
		});
	};

	retVal.updateOffer = function(selected) {
		return Restangular.one('manager/updateOffer').customPOST(selected);
	};

	retVal.updateItemCategory = function(selected) {
		return Restangular.one('manager/updateItemCategory').customPOST(selected);
	};

	return retVal;
}
(function(angular) {
	'use strict';
	angular.module('items', []).controller('ItemController', ItemController);
	ItemController.$inject = ['$scope', '$routeParams', '$location', 'ItemService'];

	function ItemController($scope, $routeParams, $location, ItemService) {

		var vm = this;

	};

})(angular);
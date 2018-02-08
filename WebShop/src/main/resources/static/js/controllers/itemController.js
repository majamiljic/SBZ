(function(angular) {
	'use strict';
	angular.module('items', []).controller('ItemController', ItemController);
	ItemController.$inject = ['$routeParams', '$location', 'ItemService'];

	function ItemController($routeParams, $location, ItemService) {
		var vm = this;

	};

})(angular);
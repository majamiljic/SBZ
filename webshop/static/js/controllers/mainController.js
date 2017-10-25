(function(angular) {
	'use strict';
	angular.module('main', []).controller('MainController', MainController);
	MainController.$inject = ['$scope', 'MainService'];

	function MainController($scope, MainService) {
		var vm = this;
		
		MainService.getItems().then(function(items) {
			$scope.items = items;
	 	});

	};

})(angular);
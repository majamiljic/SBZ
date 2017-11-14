(function(angular) {
	'use strict';
	angular.module('main', []).controller('MainController', MainController);
	MainController.$inject = ['$localStorage', 'MainService'];

	function MainController($localStorage, MainService) {
		var vm = this;
		var cart = [];
		if($localStorage.cart == null)
			$localStorage.cart = [];
		
		MainService.getItems().then(function(items) {
			vm.items = items;
	 	});
		
		MainService.getCategories().then(function(categories) {
			vm.categories = categories;
	 	});
		
		vm.addToCart = function(item) {
			var numberOfItems = document.getElementById("numberOfItems" + item.id).value;
			if (numberOfItems == 0)
				numberOfItems = 1;
			document.getElementById("addToCartButton" + item.id).disabled = true;
			
			console.log($localStorage.cart);
			for (var i = 0; i < $localStorage.cart.length; i++) {
				if ($localStorage.cart[i].item.id == item.id)
					$localStorage.cart[i].numberOfItems++;
			}
			
			$localStorage.cart.push({"item" : item, "numberOfItems" : numberOfItems});
		}

	};

})(angular);
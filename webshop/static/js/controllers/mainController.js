(function(angular) {
	'use strict';
	angular.module('main', []).controller('MainController', MainController);
	MainController.$inject = ['$localStorage', 'MainService'];

	function MainController($localStorage, MainService) {
		var vm = this;
		$("#success").hide();
		
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
			
			if (!itemExists(item.id, numberOfItems))
				$localStorage.cart.push({"item" : item, "numberOfItems" : numberOfItems});
			
			$("#success").show();
			$("#success").fadeTo(2000, 500).slideUp(500, function() {
				$("#success").slideUp(700);
			});
		}
		
		var itemExists = function(id, numberOfItems) {
			var retVals = [];
			for (var i = 0; i < $localStorage.cart.length; i++) {
				var itemsNo = Number($localStorage.cart[i].numberOfItems);
				if ($localStorage.cart[i].item.id == id) {
					itemsNo += Number(numberOfItems);
					$localStorage.cart[i].numberOfItems = itemsNo;
					retVals.push("true");
				}
				else
					retVals.push("false");
			}

			if(retVals.includes("true"))
				return true;
			else
				return false;
		}

	};

})(angular);
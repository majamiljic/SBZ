(function(angular) {
	'use strict';
	angular.module('main', []).controller('MainController', MainController);
	MainController.$inject = ['$localStorage', 'MainService'];

	function MainController($localStorage, MainService) {
		var vm = this;
		$("#success").hide();
		$("#noItem").hide();
		$("#noItems").hide();
		$("#noItemsInCategory").hide();
		$("#priceInput").hide();
		$("#noItemsByPrice").hide();
		
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
		
		vm.search = function(search) {
			if(search == null || search == "") {
				MainService.getItems().then(function(items) {
					vm.items = items;
			 	});
			}
			else {
				if(Number.isInteger(Number(search))) {
					MainService.getItemByCode(Number(search)).then(function(items) {
						if(items != null)
							vm.items = items;
						else{
							$("#noItem").show();
							$("#noItem").fadeTo(2000, 500).slideUp(500, function() {
								$("#noItem").slideUp(700);
							});
						}
				 	});
				}
				else {
					MainService.getItemsByName(search).then(function(items) {
						if(items != null)
							vm.items = items;
						else{
							$("#noItems").show();
							$("#noItems").fadeTo(2000, 500).slideUp(500, function() {
								$("#noItems").slideUp(700);
							});
						}
				 	});
				}
			}
		}
		
		vm.getAll = function() {
			MainService.getItems().then(function(items) {
				vm.items = items;
		 	});
		}
		
		vm.getItemsByCategory = function(id) {
			MainService.getItemsByCategory(id).then(function(items){
				if(items != null)
					vm.items = items;
				else
					$("#noItemsInCategory").show();
			});
		}
		
		vm.getItemsByPrice = function(priceFrom, priceTo) {
			var prices = {
				"priceFrom" : priceFrom,
				"priceTo" : priceTo
			};
			if((priceFrom && priceTo) != null) {
				if(priceFrom > priceTo){
					$("#priceInput").show();
					$("#priceInput").fadeTo(2000, 500).slideUp(500, function() {
						$("#priceInput").slideUp(700);
					});
				}
				else {
					MainService.getItemsByPrice(prices).then(function(items){
						if(items != null)
							vm.items = items;
						else{
							$("#noItemsByPrice").show();
							$("#noItemsByPrice").fadeTo(2000, 500).slideUp(500, function() {
								$("#noItemsByPrice").slideUp(700);
							});
						}
					});
				}
			}			
		};
		
	};

})(angular);
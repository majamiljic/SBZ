(function(angular) {
	'use strict';
	angular.module('cart', []).controller('ShoppingCartController', ShoppingCartController);
	ShoppingCartController.$inject = ['$localStorage', 'ShoppingCartService'];

	function ShoppingCartController($localStorage, ShoppingCartService) {
		var vm = this;
		vm.shoppingCart = $localStorage.cart;

		vm.removeFromCart = function(element) {
			for (var i = 0; i < $localStorage.cart.length; i++) {
				if ($localStorage.cart[i].item.id == element.item.id)
					$localStorage.cart.splice(i, 1);
			}
		}
	};

})(angular);
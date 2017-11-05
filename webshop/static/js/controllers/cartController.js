(function(angular) {
	'use strict';
	angular.module('cart', []).controller('ShoppingCartController', ShoppingCartController);
	ShoppingCartController.$inject = ['$scope', '$localStorage', 'ShoppingCartService'];

	function ShoppingCartController($scope, $localStorage, ShoppingCartService) {
		var vm = this;
		
		$scope.shoppingCart = $localStorage.cart;

		$scope.removeFromCart = function(element) {
			for (var i = 0; i < $localStorage.cart.length; i++) {
				if ($localStorage.cart[i].item.id == element.item.id)
					$localStorage.cart.splice(i, 1);
			}
		}
	};

})(angular);
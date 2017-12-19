(function(angular) {
	'use strict';
	angular.module('seller', []).controller('SellerController', SellerController);
	SellerController.$inject = ['$localStorage', 'SellerService'];

	function SellerController($localStorage, SellerService) {
		var vm = this;
		$("#declined").hide();
		$("#approved").hide();
		$("#cancelled").hide();

		SellerService.getInvoices().then(function(results) {
			vm.invoices = results;
	 	});
		
		vm.approve = function(id) {
			SellerService.approve(id).then(function(results) {
				if(results == null) {
					$("#declined").show();
					$("#declined").fadeTo(2000, 500).slideUp(500, function() {
						$("#declined").slideUp(700);
					});
				}
				else {
					vm.invoices = results;
					$("#approved").show();
					$("#approved").fadeTo(2000, 500).slideUp(500, function() {
						$("#approved").slideUp(700);
					});
				}
			});
		}
		
		vm.decline = function(id) {
			SellerService.decline(id).then(function(results) {
				vm.invoices = results;
				$("#cancelled").show();
				$("#cancelled").fadeTo(2000, 500).slideUp(500, function() {
					$("#cancelled").slideUp(700);
				});
			});
		}

	};

})(angular);
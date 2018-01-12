(function(angular) {
	'use strict';
	angular.module('seller', []).controller('SellerController', SellerController);
	SellerController.$inject = ['$localStorage', 'SellerService'];

	function SellerController($localStorage, SellerService) {
		var vm = this;
		$("#declined").hide();
		$("#approved").hide();
		$("#cancelled").hide();
		$("#refilled").hide();

		vm.getAllInvoices = function() {
			SellerService.getAllInvoices().then(function(results) {
				vm.invoices = results;
		 	});
		};
		vm.getAllInvoices();
		
		vm.getInvoicesByStatus = function(status) {
			SellerService.getInvoicesByStatus(status).then(function(results) {
				vm.invoices = results;
		 	});
		};

		SellerService.getInvoicesByStatus("Ordered").then(function(results) {
			vm.orderedInvoices = results;
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

		SellerService.getItemsToRefill().then(function(results) {
			vm.items = results;
	 	});
		
		vm.refill = function() {
			var numberOfItems = document.getElementById("numberOfItems").value;
			SellerService.refill(numberOfItems).then(function() {
				vm.items = [];
				$("#refilled").show();
				$("#refilled").fadeTo(2000, 500).slideUp(500, function() {
					$("#refilled").slideUp(700);
				});
			});
		}

	};

})(angular);
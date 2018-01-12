(function(angular) {
	'use strict';
	angular.module('manager', []).controller('ManagerController', ManagerController);
	ManagerController.$inject = ['$localStorage', 'SellerService', 'ManagerService'];

	function ManagerController($localStorage, SellerService, ManagerService) {
		var vm = this;
		$("#declined").hide();
		$("#approved").hide();
		$("#cancelled").hide();
		$("#refilled").hide();
		$("#exists").hide();
		$("#offerExists").hide();
		$("#addedBoundary").hide();
		$("#updatedBoundary").hide();

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

		ManagerService.getCategories().then(function(results) {
			vm.categories = results;
	 	});

		ManagerService.getParentCategories().then(function(results) {
			vm.parentCategories = results;
	 	});

		ManagerService.getSpecialOffers().then(function(results) {
			for (var i = 0; i < results.length; i++) {
				results[i].dateFrom = new Date(results[i].dateFrom);
				results[i].dateTo = new Date(results[i].dateTo);
			}
			vm.offers = results;
	 	});
		
		vm.setSelected = function(selected) {
			vm.selected = selected;
		};
		
		vm.updateOffer = function(selected) {
			ManagerService.updateOffer(selected);
		};
		
		vm.updateItemCategory = function(selectedCategory) {
			ManagerService.updateItemCategory(selectedCategory);
		};
		
		vm.setSelectedCategory = function(selectedCategory) {
			vm.selectedCategory = selectedCategory;
		};
		
		vm.addItemCategory = function(newCategory) {
			ManagerService.addItemCategory(newCategory).then(function(results) {
				if(results != null)
					vm.categories = results;
				else {
					$("#exists").show();
					$("#exists").fadeTo(2000, 500).slideUp(500, function() {
						$("#exists").slideUp(700);
					});
				}
		 	});
		};
		
		vm.addOffer = function(newOffer) {
			ManagerService.addOffer(newOffer).then(function(results) {
				if(results != null) {
					for (var i = 0; i < results.length; i++) {
						results[i].dateFrom = new Date(results[i].dateFrom);
						results[i].dateTo = new Date(results[i].dateTo);
					}
					vm.offers = results;
				}
				else {
					$("#offerExists").show();
					$("#offerExists").fadeTo(2000, 500).slideUp(500, function() {
						$("#offerExists").slideUp(700);
					});
				}
		 	});
		};

		ManagerService.getUserCategories().then(function(results) {
			vm.userCategories = results;
	 	});

		vm.getSpendingBoundariesByUserCategory = function(id) {
			ManagerService.getSpendingBoundariesByUserCategory(id).then(function(results) {
				vm.spendingBoundaries = results;
		 	});
		};
		
		vm.addSpendingBoundary = function(spendingBoundary) {
			spendingBoundary.userCategory = vm.selectedCategory;
			ManagerService.addSpendingBoundary(spendingBoundary).then(function(results) {
				$("#addedBoundary").show();
				$("#addedBoundary").fadeTo(2000, 500).slideUp(500, function() {
					$("#addedBoundary").slideUp(700);
				});
		 	});
		};
		
		vm.updateSpendingBoundary = function(spendingBoundary) {
			ManagerService.updateSpendingBoundary(spendingBoundary).then(function(results) {
				$("#updatedBoundary").show();
				$("#updatedBoundary").fadeTo(2000, 500).slideUp(500, function() {
					$("#updatedBoundary").slideUp(700);
				});
		 	});
		};

	};

})(angular);
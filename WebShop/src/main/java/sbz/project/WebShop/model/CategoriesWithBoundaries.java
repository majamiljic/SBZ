package sbz.project.WebShop.model;

import java.util.ArrayList;
import java.util.List;

public class CategoriesWithBoundaries {
	private UsersCategory usersCategory;
	private List<SpendingBoundary> spendingBoundaries = new ArrayList<SpendingBoundary>();
	
	public CategoriesWithBoundaries() {
		super();
	}

	public CategoriesWithBoundaries(UsersCategory usersCategory, List<SpendingBoundary> spendingBoundaries) {
		super();
		this.usersCategory = usersCategory;
		this.spendingBoundaries = spendingBoundaries;
	}

	public UsersCategory getUsersCategory() {
		return usersCategory;
	}

	public void setUsersCategory(UsersCategory usersCategory) {
		this.usersCategory = usersCategory;
	}

	public List<SpendingBoundary> getSpendingBoundaries() {
		return spendingBoundaries;
	}

	public void setSpendingBoundaries(List<SpendingBoundary> spendingBoundaries) {
		this.spendingBoundaries = spendingBoundaries;
	}

	@Override
	public String toString() {
		return "CategoriesWithBoundaries [usersCategory=" + usersCategory + ", spendingBoundaries=" + spendingBoundaries
				+ "]";
	}
	
}

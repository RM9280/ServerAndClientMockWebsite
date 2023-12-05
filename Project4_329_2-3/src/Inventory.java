//Rebecca Mantione: provides the inventory for the customer

public class Inventory {
	private String stockNumber;
	private String description;

	// Has a stock number and description of the item in inventory
	public Inventory(String stockNo, String desc) {
		stockNumber = stockNo;
		description = desc;
	}

	// gets the stock number
	public String getStockNumber() {
		return stockNumber;
	}

	// sets the stock number
	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}

	// gets the description
	public String getDescription() {
		return description;
	}

	// sets the description
	public void setDescription(String description) {
		this.description = description;
	}
}

package files;

import java.util.List;

public class EnrollCustomerPayload {
	private String caller;
	private String orgName;
	private String email;
	private String storageAccountRegion;
	private String retentionInterval;
	private String marketPlace;
	private List<azureCredentials> azureCredentials;
	
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStorageAccountRegion() {
		return storageAccountRegion;
	}
	public void setStorageAccountRegion(String storageAccountRegion) {
		this.storageAccountRegion = storageAccountRegion;
	}
	public String getRetentionInterval() {
		return retentionInterval;
	}
	public void setRetentionInterval(String retentionInterval) {
		this.retentionInterval = retentionInterval;
	}
	public String getMarketPlace() {
		return marketPlace;
	}
	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}
	public List<azureCredentials> getAzureCredentials() {
		return azureCredentials;
	}
	public void setAzureCredentials(List<azureCredentials> azureCredentials) {
		this.azureCredentials = azureCredentials;
	}
	
}

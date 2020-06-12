package files;

import java.util.List;



public class azureCredentials {
	private String azureTenantId;
	private List<String> azureSubscriptionIdList;
	private String azureClientId;
	public String getAzureTenantId() {
		return azureTenantId;
	}
	public void setAzureTenantId(String azureTenantId) {
		this.azureTenantId = azureTenantId;
	}
	public List<String> getAzureSubscriptionIdList() {
		return azureSubscriptionIdList;
	}
	public void setAzureSubscriptionIdList(List<String> azureSubscriptionIdList) {
		this.azureSubscriptionIdList = azureSubscriptionIdList;
	}

	public String getAzureClientId() {
		return azureClientId;
	}
	public void setAzureClientId(String azureClientId) {
		this.azureClientId = azureClientId;
	}

	
	public String getAzureClientKey() {
		return azureClientKey;
	}
	public void setAzureClientKey(String azureClientKey) {
		this.azureClientKey = azureClientKey;
	}
	private String azureClientKey;
}

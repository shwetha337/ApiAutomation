package files;

public class payload {

	
	public static String updateStatus(String version, String state)
	
	{
		return "{             \r\n" + 
        		"                \"revision\": {\r\n" + 
        		"                             \"version\": "+version+"\r\n" + 
        		"                            },\r\n" + 
        		"               \"state\":\""+state+"\",\r\n" + 
        		"               \"disconnectedNodeAcknowledged\":\"true\"\r\n" + 
        		"                }";
		
		
	}
	
	
	public static String enrollCustomerAws(String caller,String orgName,String aisaacTenantID,String accountId,String accessKeyId,String accessKey,String region) throws InterruptedException{
		Thread.sleep(3000);
		return "{\r\n" + 
				"  \"caller\": \""+caller+"\",\r\n" + 
				"  \"orgName\": \""+orgName+"\",\r\n" + 
				"  \"email\": \"a000123@SLK324.net\",\r\n" + 
				"  \"aisaacTenantID\": \""+aisaacTenantID+"\",\r\n" + 
				"  \"awsCredentials\":[\r\n" + 
				"    {\r\n" + 
				"      \"marketPlace\": false,\r\n" + 
				"      \"accountId\":\""+accountId+"\",\r\n" + 
				"      \"accessKeyId\": \""+accessKeyId+"\",\r\n" + 
				"      \"accessKey\": \""+accessKey+"\",\r\n" + 
				"      \"region\": \""+region+"\"\r\n" + 
				"    }\r\n" + 
				"    ]\r\n" + 
				"}";
				
			
				
				}
	
	public static String checkingForMarket(String caller,String orgName,String aisaacTenantID,String marketPlace,String accountId,String accessKeyId,String accessKey,String region){
		return "{\r\n" + 
				"  \"caller\": \""+caller+"\",\r\n" + 
				"  \"orgName\": \""+orgName+"\",\r\n" + 
				"  \"email\": \"Test@Test.net\",\r\n" + 
				"  \"aisaacTenantID\": \""+aisaacTenantID+"\",\r\n" + 
				"  \"awsCredentials\":[\r\n" + 
				"    {\r\n" + 
				"      \"marketPlace\": \""+marketPlace+"\",\r\n" + 
				"      \"accountId\":\""+accountId+"\",\r\n" + 
				"      \"accessKeyId\": \""+accessKeyId+"\",\r\n" + 
				"      \"accessKey\": \""+accessKey+"\",\r\n" + 
				"      \"region\": \""+region+"\"\r\n" + 
				"    }\r\n" + 
				"    ]\r\n" + 
				"}";
	}			
		
		public static String enrollCustomerAWSConnector(String cuId,String accountId,String accessKeyId,String accessKey,String region,String orgName,String aisaacTenantID,String caller){
			return "{\r\n" + 
					"  \"cuId\":\""+cuId+"\",\r\n" + 
					"  \"awsCredentials\": [\r\n" + 
					"    {\r\n" + 
					"      \"accountId\":\""+accountId+"\",\r\n" + 
					"      \"accessKeyId\": \""+accessKeyId+"\",\r\n" + 
					"      \"accessKey\": \""+accessKey+"\",\r\n" + 
					"      \"region\": \""+region+"\"\r\n" + 
					"    }\r\n" + 
					"  ],\r\n" + 
					"  \"orgName\": \""+orgName+"\",\r\n" + 
					"  \"aisaacTenantID\": \""+aisaacTenantID+"\",\r\n" + 
					"  \"caller\": \""+caller+"\"\r\n" + 
					"}";
				
				
	}	
		
		public static String enrollCustomerAzure(String caller,String orgName,String azureTenantId,String azureClientId,String azureClientKey,String storageAccountRegion,String marketPlace,String aisaacTenantID) throws InterruptedException{
			
			return "{\r\n" + 
					"  \"caller\": \""+caller+"\",\r\n" + 
					"  \"orgName\": \""+orgName+"\",\r\n" + 
					"  \"email\": \"admin@test.net\",\r\n" + 
					"  \"azureCredentials\": [\r\n" + 
					"    {\r\n" + 
					"      \"azureTenantId\": \""+azureTenantId+"\",\r\n" + 
					"      \"azureClientId\": \""+azureClientId+"\",\r\n" + 
					"      \"azureClientKey\": \""+azureClientKey+"\",\r\n" + 
					"      \"storageAccountRegion\": \""+storageAccountRegion+"\",\r\n" + 
					"      \"marketPlace\": \""+marketPlace+"\"\r\n" + 
					"    }\r\n" + 
					"  ],\r\n" + 
					"  \"aisaacTenantID\": \""+aisaacTenantID+"\"\r\n" + 
					"}";
					
				
					
					}
	
public static String getAssets(String cuId,String discoveryId,String type) throws InterruptedException{
			
			return "{\r\n" + 
					"  \"cuId\": \""+cuId+"\",\r\n" + 
					"  \"discoveryId\":\""+discoveryId+"\",\r\n" + 
					"  \"type\": \""+type+"\",\r\n" + 
					"  \"hostingProvider\": \"aws\",\r\n" + 
					"  \"pageNumber\": 1\r\n" + 
					"}";
					
				
					
					}
		
	
public static String getCustomerSubscriptions(String orgName,String email,String aisaacTenantID) throws InterruptedException{
			
			return "{\r\n" + 
					"  \"orgName\": \""+orgName+"\",\r\n" + 
					"  \"email\": \""+email+"\",\r\n" + 
					"  \"aisaacTenantID\": \""+aisaacTenantID+"\"\r\n" + 
					"}";
					
				
					
					}

public static String startDiscoveryForAws(String cuId,String type,String accountId) throws InterruptedException{
	
	return "{\r\n" + 
			"  \"cuId\": \""+cuId+"\",\r\n" + 
			"  \"aws\": {\r\n" + 
			"    \"type\": [\r\n" + 
			"      \""+type+"\"\r\n" + 
			"    ]\r\n" + 
			"  },\r\n" + 
			"  \"awsDetailsModel\": [\r\n" + 
			"    {\r\n" + 
			"      \"accountId\": \""+accountId+"\"\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}" ;
			
		
			
			}
	
public static String startDiscoveryForAzure(String cuId,String type,String azureTenantId) throws InterruptedException{
	
	return "{  \r\n" + 
			"  \"cuId\": \""+cuId+"\",\r\n" + 
			"  \"azure\": {\r\n" + 
			"    \"type\": [\r\n" + 
			"      \""+type+"\"\r\n" + 
			"    ]\r\n" + 
			"  },\r\n" + 
			"\r\n" + 
			"  \"azureDetailsModel\": [\r\n" + 
			"    {\r\n" + 
			"      \"azureTenantId\": \""+azureTenantId+"\"\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}";
			
		
			
			}
	
/*public static String getAssetsForAwsOrAzure(String discoveryId,String type) throws InterruptedException{
	
	return "{\r\n" + 
			"  \"discoveryId\":\""+discoveryId+"\",\r\n" + 
			"  \"type\": \""+type+"\"\r\n" + 
			"}";
			
		
			
			}*/
public static String logEnablerEnableLogs(String cuId,String assetList) throws InterruptedException{
	
	return "{\r\n" + 
			"	\"cuId\":\""+cuId+"\",\r\n" + 
			"	\"assetList\":["+assetList+"]\r\n" + 
			"	\r\n" + 
			"}";
			
		
			
			}

public static String registerAgentAws(String aisaacTenantID,String accessKey,String accessKeyId,String accountId,String	marketPlace,String region,String caller,String orgName) throws InterruptedException{
	
	return "{\r\n" + 
			"  \"aisaacTenantID\": \""+aisaacTenantID+"\",\r\n" + 
			"  \"awsCredentials\": [\r\n" + 
			"    {\r\n" + 
			"      \"accessKey\": \""+accessKey+"\",\r\n" + 
			"      \"accessKeyId\": \""+accessKeyId+"\",\r\n" + 
			"      \"accountId\": \""+accountId+"\",\r\n" + 
			"      \"marketPlace\":\""+marketPlace+"\",\r\n" + 
			"      \"region\": \""+region+"\",\r\n" + 
			"      \"remarks\": \"\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"caller\": \""+caller+"\",\r\n" + 
			"  \"email\":\"abcd@net\",\r\n" + 
			"  \r\n" + 
			"  \"orgName\": \""+orgName+"\"\r\n" + 
			"}";
			
		
			
			}

public static String registerAgentAzure(String aisaacTenantID,String azureClientId,String azureClientKey,String azureTenantId,String marketPlace,String storageAccountRegion,String caller,String orgName) throws InterruptedException{
	
	return "{\r\n" + 
			"  \"aisaacTenantID\": \""+aisaacTenantID+"\",\r\n" + 
			"  \"azureCredentials\": [\r\n" + 
			"    {\r\n" + 
			"      \r\n" + 
			"      \"azureClientId\": \""+azureClientId+"\",\r\n" + 
			"      \"azureClientKey\": \""+azureClientKey+"\",\r\n" + 
			"      \"azureTenantId\": \""+azureTenantId+"\",\r\n" + 
			"      \"marketPlace\": false,\r\n" + 
			"      \"resourceGroupName\": \"string\",\r\n" + 
			"      \"retentionInterval\": 2,\r\n" + 
			"      \"storageAccountRegion\": \""+storageAccountRegion+"\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"caller\": \""+caller+"\",\r\n" + 
			"  \"email\": \"admin@test.net\",\r\n" + 
			"  \"orgName\": \""+orgName+"\"\r\n" + 
			"}";
			
		
			
			}
	
}

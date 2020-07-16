package net.oopscraft.study.cloud.google;

import com.google.cloud.bigtable.data.v2.BigtableDataSettings;

public class BigTable {
	
	private static final String GOOGLE_APPLICATION_CREDENTIALS = "D:\\oopscraft\\doc\\google-cloud-oopscraft-5f0a7ece14f8.json";
	
	public static void main(String[] args) throws Exception {
	
		 BigtableDataSettings settings = BigtableDataSettings.newBuilder()
				 .setProjectId(projectId)
				 .setInstanceId(instanceId)
				 .build();


		
	}

}

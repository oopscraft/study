package org.study.kubernetes;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;

import java.io.FileReader;
import java.io.IOException;

public class App {
	
	public static void main(String[] args) throws Exception {
		
	    // KubeConfig의 파일 경로
	    String kubeConfigPath = "~/.kube/config";

	    // 파일시스템에서 클러스터 외부 구성인 kubeconfig 로드
	    ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

	    // 전역 디폴트 api-client를 위에서 정의한 클러스터 내 클라이언트로 설정
	    Configuration.setDefaultApiClient(client);

	    // CoreV1Api는 전역 구성에서 디폴트 api-client를 로드
	    CoreV1Api api = new CoreV1Api();

	    // CoreV1Api 클라이언트를 호출한다
	    V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
	    System.out.println("Listing all pods: ");
	    for (V1Pod item : list.getItems()) {
	      System.out.println(item.getMetadata().getName());
	    }
	}
}

package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExplorer {

	public static void main(String[] args) throws IOException {

		// 순수 자바 코드로 (클라이언트 측 코딩)
		// 준비
		// 1. 서버 측 주소 - 경로
		// http://localhost:8080/test?name=홍길동&age=20
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20 -> url인코딩
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551172/LiverCancerCoperationInfoService1/getLiverCancerCoperationInfo1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=palsG47GgfoxfBI5IvepO%2Bs%2BzSWEnnxl74qGa%2FkxbmgoHz4R%2BNYSYXYxeaPeMmUgYDU1V%2BevDZ3g6IoveoEGHQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수 (최대 값 : 1000)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        // URL 객체에서 문자열 경로를 넣어서 객체 생성
        // URL.openConnection() 데이터 요청 보내기 - 설정하고
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        
        conn.setRequestMethod("GET");	// 서버에게 자원 요청
        conn.setRequestProperty("Content-type", "application/json");
        //성공 200, 실패 404, 405
        System.out.println("Response code: " + conn.getResponseCode());
        
        // 100 ~ 500 의미(약속)
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 301) {
        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    
        
	}// end of main

}// end of class

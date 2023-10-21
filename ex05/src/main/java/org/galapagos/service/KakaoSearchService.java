package org.galapagos.service;

import org.galapagos.domain.kakao.BookResult;
import org.galapagos.domain.kakao.LocalResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoSearchService {
	//인터페이스에서는 멤버변수를 정의할 수 없음. -> 그래서 멤버변수를 선언하면 상수가 됨.
	String BASE_URL = "https://dapi.kakao.com/"; //반드시 '/'로 끝내야 함.
	
	@GET("v3/search/book")
	@Headers({ "Authorization: KakaoAK 13dd9acaaf36422a0233e1bbd7670493" })//중간에 중괄호? 배열이라는 뜻.
	Call<BookResult> searchBook(@Query("query") String query, @Query("size") int size, @Query("page") int page); //Return type : Call
	
	@GET("v2/local/search/keyword")
	@Headers({"Authorization: KakaoAK 13dd9acaaf36422a0233e1bbd7670493"})
	Call<LocalResult> searchLocal(@Query("query") String query, @Query("size") int size, @Query("page") int page);
	
	public static KakaoSearchService getService() {
		Retrofit retrofit = new Retrofit.Builder()
								.baseUrl(BASE_URL)
								.addConverterFactory(GsonConverterFactory.create())
								.build();
		
		return retrofit.create(KakaoSearchService.class);
	}
}

package org.galapagos.service;

import org.galapagos.domain.naver.LocalResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NaverSearchService {
	String BASE_URL="https://openapi.naver.com/";
	
	@GET("v1/search/local")
	@Headers({
		"X-Naver-Client-Id:XyP_JmBA1UvzMR8g6eJO",
		"X-Naver-Client-Secret:Dt0f08E5hX"
	})
	Call<LocalResult> searchLocal(@Query("query") String query, @Query("display") int display, @Query("start") int start);
	
	public static NaverSearchService getService() {
		Retrofit retrofit = new Retrofit.Builder()
								.baseUrl(BASE_URL)
								.addConverterFactory(GsonConverterFactory.create())
								.build();
		
		return retrofit.create(NaverSearchService.class);
	}
}

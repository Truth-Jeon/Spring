import org.galapagos.domain.naver.LocalResult;
import org.galapagos.service.NaverSearchService;

import retrofit2.Call;
import retrofit2.Response;

public class NaverLocalSearchEx1 {

	public static void main(String[] args) {
		NaverSearchService api = NaverSearchService.getService();
		
		try {
			Call<LocalResult> call = api.searchLocal("강남", 5, 2);
			System.out.println(call.request());
			System.out.println(call.request().headers());
			
			Response<LocalResult> res = call.execute();
			if(res.isSuccessful()) {
				LocalResult result = res.body();
				System.out.println(result);
			} else {
				System.out.println("호출 실패");
				System.out.println(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

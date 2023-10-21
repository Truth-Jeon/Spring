import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.Scanner;

import org.galapagos.domain.kakao.Local;
import org.galapagos.domain.kakao.LocalResult;
import org.galapagos.service.KakaoSearchService;

import retrofit2.Call;
import retrofit2.Response;

public class LocalSearchEx1 {

	public static void main(String[] args) {
		KakaoSearchService api = KakaoSearchService.getService();
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("검색어를 입력하세요 : ");
			String search = sc.nextLine();
			Call<LocalResult> call = api.searchLocal(search, 10, 1);
			System.out.println(call.request());
			System.out.println(call.request().headers());
			
			Response<LocalResult> response = call.execute();
			
			if(response.isSuccessful()) {
				LocalResult result = response.body();
				System.out.println(result.getMeta());
				
				for(Local local : result.getLocals()) {
					System.out.println(local.getPlaceName());
				}
			} else {
				System.out.println("호출 실패");
				System.out.println(response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

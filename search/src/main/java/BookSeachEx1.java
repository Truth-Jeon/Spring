import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.Scanner;

import org.galapagos.domain.kakao.Book;
import org.galapagos.domain.kakao.BookResult;
import org.galapagos.service.KakaoSearchService;

import retrofit2.Call;
import retrofit2.Response;

public class BookSeachEx1 {

	public static void main(String[] args) {
		KakaoSearchService api = KakaoSearchService.getService();
		Scanner sc = new Scanner(System.in);
		
		try {	
			System.out.print("검색어를 입력하세요 : ");
			String searching = sc.nextLine();
			Call<BookResult> call = api.searchBook(searching, 10, 1); //요청을 할 수 있는 Call 객체 리턴
			System.out.println(call.request());	//요청 URL 확인
			System.out.println(call.request().headers());	//헤더 확인 - 인증 키
			
			Response<BookResult> res = call.execute();	//서버에 요청 전송 //동기식임. 비동기식은 enqueue(callback - 내부스레드) 형식임.
			if(res.isSuccessful()) { //200
				BookResult result = res.body();	//JSON을 BookResult로 역직렬화
				System.out.println(result.getMeta());
				for(Book book : result.getBooks()) {
					System.out.println(book.getTitle());
				}
			} else {
				System.out.println("호출 실패");
				System.out.println(res);	//실패 시 응답 객체 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

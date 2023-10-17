package mongoex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx01 {

	public static void main(String[] args) {
		//Unchecked Exception : 예외처리가 필수사항은 아니지만, 예외가 발생할 수 있음.
		//컴파일러가 예외를 체크해주지 않음.
		//0으로 나누기, 배열/리스트의 인덱스 오류
		//MongoDB, MyBatis 등의 DB 작업 역시 Unchecked Exception이다.
		
		//Checked Exception : 반드시 예외처리(try~catch)를 해줘야 할 때.
		
		try (
				// 1. 몽고디비에 연결
				MongoClient client = new MongoClient(
						new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));
		){
			// 2. shop2 db에 연결
			MongoDatabase database = client.getDatabase("shop2");
			//MongoCollection<Document> collection = database.getCollection("memo");
			// 3. member collection에 연결
			MongoCollection<Document> collection = database.getCollection("member");
			// 4. crud(insertOne(document))
			Scanner sc = new Scanner(System.in);
			// 자바프로그램 <-- 연결설정(강물,스트림) --- 콘솔
			System.out.print("id입력>>");
			String id = sc.next();
			System.out.print("pw입력>>");
			String pw = sc.next();
			System.out.print("name입력>>");
			String name = sc.next();
			System.out.print("tel입력>>");
			String tel = sc.next();
			sc.close();
			
			Document doc = new Document();
			doc.append("id", "apple");
			doc.append("pw", "1234");
			doc.append("name", "apple");
			doc.append("tel", "011");
			
			Document doc2 = new Document();
			doc2.append("id", id);
			doc2.append("pw", pw);
			doc2.append("name", name);
			doc2.append("tel", tel);
			
//			FindIterable<Document> result = collection.find();
//			System.out.println(result.first());
			
			List<Document> list = new ArrayList<Document>();
			list.add(doc);
			list.add(doc2);
			
			System.out.println(doc);
			System.out.println(doc2);
//			collection.insertOne(doc);
			collection.insertMany(list);
			System.out.println("insertOne 성공");
			System.out.println(doc);
			System.out.println(doc2);
//			client.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}

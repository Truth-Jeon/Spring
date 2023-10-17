package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo5 {

	public static void main(String[] args) {
		try {
			//1. 몽고DB에 연결
			MongoClient client = new MongoClient("localhost", 27017);
			
			//2. shop3 db에 연결
			MongoDatabase database = client.getDatabase("shop2");
			
			//3. member collection에 연결
			MongoCollection<Document> collection = database.getCollection("member");
			
			//4.crud(deleteOne(document))
			//{"id":"apple", "pw":"1234", "name":"apple", "tel":"011"}
			//json --> document(BSON)
			//BSON <-- document
			
			//삭제할 조건
			Document filter = new Document("id", "apple");
			
			collection.deleteOne(filter);
			client.close();
			System.out.println("=========deleteOne 성공=========");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

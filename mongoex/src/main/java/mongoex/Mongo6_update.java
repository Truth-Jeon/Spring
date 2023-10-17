package mongoex;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class Mongo6_update {

	public static void main(String[] args) {
			try {
			// 1. 몽고DB에 연결
			MongoClient client = new MongoClient("localhost", 27017);
			// 2. shop3 db에 연결
			MongoDatabase database = client.getDatabase("shop2");
			// 3. member collection에 연결
			MongoCollection<Document> collection = database.getCollection("member");
			// 4.crud(updateOne(document))
			// 업데이트 할 조건
			Bson filter = new Document("id", "apple2");
			// 업데이트 할 내용
			Bson update = Updates.set("name", "winwinwin");
			
			collection.updateOne(filter, update);
			
			client.close();
			
			System.out.println("======== updateOne 성공 ========");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

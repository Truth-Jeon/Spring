package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo2_deleteOne2 {

	public static void main(String[] args) {
		try {
			// ------- Connecting DataBase -----------
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// ------- Creating DataBase -----------
			MongoDatabase db = mongoClient.getDatabase("shop2");
			MongoCollection<Document> table = db.getCollection("member");
			Document doc = new Document();
			doc.append("id", "apple3");
			doc.append("pw", "1234");
			
			table.deleteOne(doc);
			
			System.out.println("deleteOne success");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

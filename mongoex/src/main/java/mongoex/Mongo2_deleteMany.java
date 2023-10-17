package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Mongo2_deleteMany {

	public static void main(String[] args) {
		try {
			// ------ Connection DataBase ------
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// ------ Creating DataBase ------
			MongoDatabase db = mongoClient.getDatabase("shop2");
			// ------ Creating Collection ------
			MongoCollection<Document> table = db.getCollection("member");
			// ------ Creating Document ------
			Document doc = new Document();
			
			doc.append("pw", "1234");
			
			//모두 다 삭제하라 : db.shop.deleteMany({})
			table.deleteMany(doc);
			System.out.println("deleteMany Success");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

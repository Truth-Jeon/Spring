package mongoex;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MemberManagement {

	public static void main(String[] args) {
		//Bson filter = new Document();
		Bson projectBson = new Document("_id", 0L);
		Bson sortBson = new Document("id", -1L);
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/"));
		MongoDatabase database = mongoClient.getDatabase("shop");
		MongoCollection<Document> collection = database.getCollection("member");
	}

}

package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx02 {

	public static void main(String[] args) {
		// 1. 몽고디비에 연결
		MongoClient client = new MongoClient(
				new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));
		// 2. shop2 db에 연결
		MongoDatabase database = client.getDatabase("shop2");
		// 3. member collection에 연결
		MongoCollection<Document> collection = database.getCollection("member");
		
		//filter.append("id", "apple");
		Document filter = new Document("id", "apple");
		Document proj = new Document("id", 1);
		proj.append("name", 1);
		
		FindIterable<Document> result = collection.find(filter);
		
		for(Document doc : result) {
			System.out.print("ID: " + doc.getString("id") + ", ");
			System.out.print("PW: " + doc.getString("pw") + ", ");
			System.out.print("NAME: " + doc.getString("name") + ", ");
			System.out.println("TEL: " + doc.getString("tel"));
		}
	}

}

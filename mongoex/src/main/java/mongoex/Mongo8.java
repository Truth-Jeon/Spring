package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class Mongo8 {

	public static void main(String[] args) {
		MongoClient client = new MongoClient("localhost", 27017);
		
		MongoDatabase database = client.getDatabase("shop2");
		
		MongoCollection<Document> collection = database.getCollection("member");
		
		Document filter = new Document();
		filter.append("id", "apple4");
		
//		Bson update = Updates.set("name", "happy");
//		Bson update2 = Updates.set("tel", "5555");
		
		
//		List<Bson> list = new ArrayList<>();
//		list.add(update);
//		list.add(update2);
		
//		Bson all = Updates.combine(list);
		
		//가변 매개변수를 이용한 combine
		Bson all = Updates.combine(
				Updates.set("name", "happy"),
				Updates.set("tel", "5555")
		);
		
		collection.updateOne(filter, all);
		client.close();
		System.out.println("====== updateOne성공 ======");
	}

}

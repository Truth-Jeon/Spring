package org.galapagos.domain.kakao;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BookResult {

	//json documents key 를 java에서 books와 매핑시켜줌.
   @SerializedName("documents")
   List<Book> books;

   @SerializedName("meta")
   BookMeta meta;
    
}
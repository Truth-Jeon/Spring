package org.galapagos.domain.kakao;
import java.util.List;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Book {

   @SerializedName("authors")
   List<String> authors;

   @SerializedName("contents")
   String contents;

   @SerializedName("datetime")
   Date datetime;

   @SerializedName("isbn")
   String isbn;

   @SerializedName("price")
   int price;

   @SerializedName("publisher")
   String publisher;

   @SerializedName("sale_price")
   int salePrice;

   @SerializedName("status")
   String status;

   @SerializedName("thumbnail")
   String thumbnail;

   @SerializedName("title")
   String title;

   @SerializedName("translators")
   List<String> translators;

   @SerializedName("url")
   String url;
   
}
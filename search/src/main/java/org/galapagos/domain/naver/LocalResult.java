package org.galapagos.domain.naver;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data 
public class LocalResult {

   @SerializedName("lastBuildDate")
   String lastBuildDate;

   @SerializedName("total")
   int total;

   @SerializedName("start")
   int start;

   @SerializedName("display")
   int display;

   @SerializedName("items")
   List<Local> locals;
   
}
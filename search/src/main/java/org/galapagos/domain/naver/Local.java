package org.galapagos.domain.naver;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Local {

   @SerializedName("title")
   String title;

   @SerializedName("link")
   String link;

   @SerializedName("category")
   String category;

   @SerializedName("description")
   String description;

   @SerializedName("telephone")
   String telephone;

   @SerializedName("address")
   String address;

   @SerializedName("roadAddress")
   String roadAddress;

   @SerializedName("mapx")
   String mapx;

   @SerializedName("mapy")
   String mapy;
}
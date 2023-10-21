package org.galapagos.domain.kakao;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Meta {

   @SerializedName("is_end")
   boolean isEnd;

   @SerializedName("pageable_count")
   int pageableCount;

   @SerializedName("total_count")
   int totalCount;
}
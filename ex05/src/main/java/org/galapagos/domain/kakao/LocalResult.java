package org.galapagos.domain.kakao;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LocalResult {

   @SerializedName("documents")
   List<Local> locals;

   @SerializedName("meta")
   LocalMeta meta;
}
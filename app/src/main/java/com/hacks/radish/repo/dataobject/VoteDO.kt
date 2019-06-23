package com.hacks.radish.repo.dataobject

import com.google.gson.annotations.SerializedName

data class VoteDO(@SerializedName("session_id") val sessionId : String,
                  @SerializedName("image_pair_id") val imagePairId : String,
                  @SerializedName("image_idx") val imageIdx : String) : DataObject() {
}
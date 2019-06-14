package com.hacks.radish.repo.dataobject

import com.google.gson.annotations.SerializedName

data class ImageDO(@SerializedName("image_url") val imageUrl : String,
                   @SerializedName("vote_count") val voteCount : Long) : DataObject()
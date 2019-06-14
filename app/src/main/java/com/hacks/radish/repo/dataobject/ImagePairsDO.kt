package com.hacks.radish.repo.dataobject

import com.google.gson.annotations.SerializedName

data class ImagePairsDO(@SerializedName("image_pairs") val imagePairsDO : List<ImagePairDO>)
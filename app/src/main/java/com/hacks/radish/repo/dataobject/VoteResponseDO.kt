package com.hacks.radish.repo.dataobject

import com.google.gson.annotations.SerializedName

class VoteResponseDO(@SerializedName("success", alternate = ["error"]) val message : String) : DataObject() {}
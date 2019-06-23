package com.hacks.radish.repo.dataobject

import com.google.gson.annotations.SerializedName


data class SessionDO(@SerializedName("session_id") val sessionId : String) : DataObject()
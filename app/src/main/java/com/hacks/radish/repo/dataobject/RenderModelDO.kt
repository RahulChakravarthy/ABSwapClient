package com.hacks.radish.repo.dataobject

data class RenderModelDO(val title: String,
                         val creator: String,
                         val tags: List<RenderModelDO.Tag>,
                         val imageA: RenderModelDO.Image,
                         val imageB: RenderModelDO.Image) {
    data class Image(val url: String, val votes: Long)
    data class Tag(val name: String)
}
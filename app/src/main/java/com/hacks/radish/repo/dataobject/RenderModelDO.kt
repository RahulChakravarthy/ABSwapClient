package com.hacks.radish.repo.dataobject

data class RenderModelDO(val id: String,
                         val creator: String,
                         val tags: List<Tag>,
                         val imageA: ImageDO,
                         val imageB: ImageDO) {
    data class Tag(val name: String)
}
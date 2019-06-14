package com.hacks.radish.repo.dataobject

data class RenderModelDO(val title: String,
                         val creator: String,
                         val tags: List<RenderModelDO.Tag>,
                         val imageA: ImageDO,
                         val imageB: ImageDO) {
    data class Tag(val name: String)
}
package com.hacks.radish.managers

import com.wwdablu.soumya.lottiebottomnav.MenuItem
import com.wwdablu.soumya.lottiebottomnav.MenuItemBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuManager @Inject constructor(){

    fun createDefaultNavigationBar() : List<MenuItem> {
        val postItem = MenuItemBuilder.create("Post", "upload.json", MenuItem.Source.Raw, "post")
            .pausedProgress(100F)
            .autoPlay(false)
            .loop(false)
            .build()

        val feedItem = MenuItemBuilder.create("Feed", "feed.json", MenuItem.Source.Raw, "feed")
            .pausedProgress(100F)
            .autoPlay(false)
            .loop(false)
            .build()

        return arrayOf(postItem, feedItem).asList()
    }
}
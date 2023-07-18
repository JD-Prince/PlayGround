package com.project.playground.util

import android.graphics.Bitmap
import androidx.collection.LruCache

object CacheUtils {

        private val cache: LruCache<String, Bitmap> = LruCache(1024)

        fun saveBitmapToCache(key: String, bitmap: Bitmap) {
            cache.put(key, bitmap)
        }

        fun getBitmapFromCache(key: String): Bitmap? {
            return cache.get(key)
        }


}
package com.pinakin

import android.app.Application
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GifFreshApplication : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {

        return ImageLoader.Builder(applicationContext)
            .componentRegistry {

                if (Build.VERSION.SDK_INT >= 28) {

                    add(ImageDecoderDecoder(applicationContext))

                } else {

                    add(GifDecoder())

                }

            }.build()

    }
}
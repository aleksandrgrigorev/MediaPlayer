package ru.netology.mediaplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val observer = MediaLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(observer)

        findViewById<View>(R.id.play).setOnClickListener {
            observer.apply {
                resources.openRawResourceFd(R.raw.the_one_ring).use { afd ->
                    mediaPlayer?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                }
            }.play()
        }
    }
}
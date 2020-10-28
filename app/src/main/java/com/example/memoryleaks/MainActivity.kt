package com.example.memoryleaks

import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private var myAsyncTask: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {

            if (myAsyncTask != null) {
                finish()
            }
            myAsyncTask = MyAsyncTask(this@MainActivity)
            myAsyncTask!!.execute()
        }
    }

    companion object {
        private class MyAsyncTask(private val context: Context) :
            AsyncTask<Void, Void, Void>() {

            val mContex = WeakReference(context)

            override fun doInBackground(vararg params: Void?): Void? {

                val bitmap = BitmapFactory.decodeResource(
                    mContex.get()?.resources,
                    R.drawable.ic_launcher_background
                )
                // val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background)
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                return null
            }
        }
    }

    override fun onDestroy() {
        // myAsyncTask!!.cancel(true)
        super.onDestroy()
        Log.d("TAG", "onDestroy: activity is Destroyed")
    }
}
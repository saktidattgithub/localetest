package com.example.helloworldlocalize

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworldlocalize.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    var context:Context? = null
    var resource:Resources?=null
    var localeMainObj:LocaleMain?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       CoroutineScope(Dispatchers.Default).launch {
           getLocatedata()
       }
        binding.english.setOnClickListener {
            if(localeMainObj!=null)
            {
                binding.hellow.text = localeMainObj?.en?.get(0)?.hellow

            }else {
                context = LocaleHelper.setLocale(this, "en")
                resource = context?.getResources()
                binding.hellow.text = resource?.getString(R.string.hellow)
            }

        }

        binding.chinese.setOnClickListener {
            if(localeMainObj!=null)
            {
                binding.hellow.text = localeMainObj?.ch?.get(0)?.hellow

            }else {
                context = LocaleHelper.setLocale(this, "zh")
                resource = context?.getResources()
                binding.hellow.text = resource?.getString(R.string.hellow)
            }
        }

        binding.hindi.setOnClickListener {
            if(localeMainObj!=null)
            {
                binding.hellow.text = localeMainObj?.hi?.get(0)?.hellow

            }else {
                context = LocaleHelper.setLocale(this, "hi")
                resource = context?.getResources()
                binding.hellow.text = resource?.getString(R.string.hellow)
            }
                   }
    }

    private fun getLocatedata()
    {
        if(localeMainObj==null) {
            val strFile = StringBuilder()
            try {
                var reader: BufferedReader? = null
                val inputStream: InputStream = getAssets().open("locale.txt")
                reader = BufferedReader(InputStreamReader(inputStream))
                var mLine: String?
                while (reader.readLine().also { mLine = it } != null) {
                    strFile.append(mLine)
                }
                val gson = Gson()
                localeMainObj = gson.fromJson(strFile.toString(), LocaleMain::class.java)

            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
//        val url = URL("https://www.jiocloud.com/share/?s=WuEXVyuN_cZ0bOf61BHsk7mBpMmpOorx4sY0jrzi_uEOe5")
//        val httpConn: HttpURLConnection = url.openConnection() as HttpURLConnection
//        httpConn.setRequestMethod("GET");
//        val inputStream: InputStream = httpConn.inputStream
//        val inputStreamReader = InputStreamReader(inputStream)
//        val bufferedReader = BufferedReader(inputStreamReader)
//        var line: String = bufferedReader.readLine()
//        var buffer=StringBuffer()
//        while(line != null)
//        {
//            buffer.append(line);
//            line = bufferedReader.readLine();
//        }
    }
}
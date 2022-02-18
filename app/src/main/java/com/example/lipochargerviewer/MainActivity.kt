package com.example.lipochargerviewer

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ssidString = checkWifiOnAndConnected(this)
        println(ssidString)
        ssid_textview.text = ssidString

        val wifiOkVal = vyhodnoceniSSID(ssidString)
        println(wifiOkVal)

        if(wifiOkVal == 0){
            enableWifiButton.visibility = View.VISIBLE
        }


    }




    private fun checkWifiOnAndConnected(context: Context): String {
        val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val info = wifiManager.connectionInfo
        val infoUvoz = info.ssid.replace("\"","")
        return infoUvoz
    }

    private fun vyhodnoceniSSID(ssidString: String): Int{
        val res = when (ssidString){
            "Rykalovi" -> 1
            "Rykalovi - patro" ->  1
            else -> 0
        }
        return res
    }
}
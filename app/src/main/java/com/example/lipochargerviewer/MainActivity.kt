package com.example.lipochargerviewer

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    val MAIN_URL = "http://www.example.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkWifiOnAndConnected(this)

        enableWifiButton.setOnClickListener {
            enableWifi(this)
        }

    }

    private fun enableWifi(context: Context){
        var wifiManager: WifiManager? = null
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        wifiManager!!.setWifiEnabled(true)
        Thread.sleep(5_0000)
        checkWifiOnAndConnected(context)
    }

    private fun checkWifiOnAndConnected(context: Context) {
        val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val info = wifiManager.connectionInfo
        val infoUvoz = info.ssid.replace("\"","")
        ssid_textview.text = infoUvoz
        vyhodnoceniSSID(infoUvoz)
    }

    private fun vyhodnoceniSSID(ssidString: String){
        val res = when (ssidString){
            "Rykalovi" -> 1
            "Rykalovi - patro" ->  1
            else -> 0
        }
        if(res == 0){
            enableWifiButton.visibility = View.VISIBLE
            webView.visibility = View.INVISIBLE
        } else {
                enableWifiButton.visibility = View.INVISIBLE
                webView.visibility = View.VISIBLE
                videoLoad()
            }
        }

    private fun videoLoad(){
        webView.loadUrl(MAIN_URL)
    }

    }

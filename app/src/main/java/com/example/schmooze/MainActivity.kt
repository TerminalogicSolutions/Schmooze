package com.example.schmooze


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.telephony.SmsManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.Intent
import android.app.PendingIntent
import android.content.IntentFilter
import android.widget.Toast
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.widget.ArrayAdapter
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import android.widget.TextView
import org.json.JSONArray
import android.widget.Spinner
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.AsyncTask
import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_contacts.*
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    //Request access to read contacts
    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 100
    private val MY_PERMISSIONS_REQUEST_INTERNET = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()
        setupInternetPermissions()





        //val url: String = "http://theplayersearch.com/schmooze/?api=getUsers"

        //var smsNumber = "3362675269"
        //var smsMessage = "Auto Test On Load"

        //var x = 0
        //while (x < 1) {
            //sendSMS(smsNumber, smsMessage)
            //x++ // Same as x += 1
        //}




    }


    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.SEND_SMS)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun setupInternetPermissions(){
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.INTERNET)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeInternetRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),MY_PERMISSIONS_REQUEST_SEND_SMS)
    }

    private fun makeInternetRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.INTERNET), MY_PERMISSIONS_REQUEST_INTERNET)
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        val SENT = "SMS_SENT"
        val DELIVERED = "SMS_DELIVERED"

        val sentPI = PendingIntent.getBroadcast(
            this, 0,
            Intent(SENT), 0
        )

        val deliveredPI = PendingIntent.getBroadcast(
            this, 0,
            Intent(DELIVERED), 0
        )

        //---when the SMS has been sent---
        registerReceiver(object : BroadcastReceiver() {
            override
            fun onReceive(arg0: Context, arg1: Intent) {
                when (resultCode) {
                    Activity.RESULT_OK -> Toast.makeText(
                        baseContext, "SMS sent",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_GENERIC_FAILURE -> Toast.makeText(
                        baseContext, "Generic failure",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_NO_SERVICE -> Toast.makeText(
                        baseContext, "No service",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_NULL_PDU -> Toast.makeText(
                        baseContext, "Null PDU",
                        Toast.LENGTH_SHORT
                    ).show()
                    SmsManager.RESULT_ERROR_RADIO_OFF -> Toast.makeText(
                        baseContext, "Radio off",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }, IntentFilter(SENT))

        //---when the SMS has been delivered---
        registerReceiver(object : BroadcastReceiver() {
            override
            fun onReceive(arg0: Context, arg1: Intent) {
                when (resultCode) {
                    Activity.RESULT_OK -> Toast.makeText(
                        baseContext, "SMS delivered",
                        Toast.LENGTH_SHORT
                    ).show()
                    Activity.RESULT_CANCELED -> Toast.makeText(
                        baseContext, "SMS not delivered",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }, IntentFilter(DELIVERED))

        val sms = SmsManager.getDefault()
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI)
    }





}



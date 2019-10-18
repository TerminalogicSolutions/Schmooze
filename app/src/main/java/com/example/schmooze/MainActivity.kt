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
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T









class MainActivity : AppCompatActivity() {

    //Request access to read contacts
    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPermissions()


        var smsNumber = "3362675269"
        var smsMessage = "Auto Test On Load"

        sendSMS(smsNumber, smsMessage)


    }

    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.SEND_SMS)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),MY_PERMISSIONS_REQUEST_SEND_SMS)
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



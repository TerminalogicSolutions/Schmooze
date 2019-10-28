package com.example.schmooze


import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_main.*
import okhttp3.*
import java.io.IOException



/**
 * A simple [Fragment] subclass.
 */
class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RecyclerView_main.layoutManager = LinearLayoutManager(null)
        //RecyclerView_main.adapter = MainAdapter()



        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to fetch json")

        val url = "http://theplayersearch.com/schmooze/?api=getUsers"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val MainFeed = gson.fromJson(body, MainFeed::class.java)



                activity!!.run {


                    runOnUiThread(Runnable() {
                                RecyclerView_main.adapter = MainAdapter(MainFeed)
                            })
                }


            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}

class MainFeed(val artists: List<Contacts>)

class Contacts(val id: Int, val firstname: String, val lastname: String, val phonenumber: String)




package com.example.schmooze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_list.view.*

class MainAdapter(val mainFeed: MainFeed): RecyclerView.Adapter<CustomViewHolder>() {

    val contactArray = listOf("Test1", "Test2", "Test3")

    override fun getItemCount(): Int {
        return mainFeed.artists.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.contact_list, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val artists = mainFeed.artists.get(position)
        holder?.view?.tv_firstname.text = artists.firstname
        holder?.view?.tv_lastname.text = artists.lastname
        holder?.view?.tv_phonenumber.text = artists.phonenumber
    }



}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}

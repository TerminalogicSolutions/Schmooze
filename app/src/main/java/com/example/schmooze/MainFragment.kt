package com.example.schmooze


import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Linking the navigation
            llScheduler.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainFragment_to_scheduleFragment)
            }
            llParties.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainFragment_to_partiesFragment)
            }
            llTemplates.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainFragment_to_templatesFragment)
            }
            llContacts.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainFragment_to_contactsFragment)
            }
            llSettings.setOnClickListener{
                it.findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
            }
    }


}

package com.android.samples.kotlin.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.android.samples.kotlin.R
import com.android.samples.kotlin.basic.observability.ui.UserActivity
import com.android.samples.kotlin.basic.weather.TestMainActivity

class StartFragment : Fragment() {

    private lateinit var viewModel: StartViewModel

    companion object {
        fun newInstance() = StartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtain ViewModel from ViewModelProviders, using this fragment as LifecycleOwner.
        viewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)

        // Observe data on the ViewModel, exposed as a LiveData
        viewModel.data.observe(this, Observer { data ->
            // Set the text exposed by the LiveData
            view?.findViewById<TextView>(R.id.text)?.text = data
        })

        // Set up a click listener on the login button
        view?.findViewById<Button>(R.id.navigate_bt)?.setOnClickListener {
            // Navigate to the login destination
            view?.let { Navigation.findNavController(it).navigate(R.id.end_action) }
        }

        view?.findViewById<Button>(R.id.nav_user)?.setOnClickListener {
            view?.let { startUserAct(activity as AppCompatActivity) } }

        view?.findViewById<Button>(R.id.test_start_act)?.setOnClickListener { view?.let {
            if(activity is Context)
            TestMainActivity.startAct(activity as Context) }
        }
    }

    private fun startUserAct(context: AppCompatActivity){
        val intent = Intent(context, UserActivity::class.java)
        startActivity(intent)
    }

}
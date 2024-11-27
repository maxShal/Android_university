package com.example.real_num30

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [ConverterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConverterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_converter, container, false)/home/max/AndroidStudioProjects/real_num30

        val message = ConverterFragmentArgs.fromBundle(requireArguments()).massage
        var meow = ""
        repeat(message.length){
            meow += "мяу "
        }
        val translatedText = view.findViewById<TextView>(R.id.converterfragment_text)
        translatedText.text = meow

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = ConverterFragment()
    }
}
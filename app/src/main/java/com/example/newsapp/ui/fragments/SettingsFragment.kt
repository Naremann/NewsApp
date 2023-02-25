package com.example.newsapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.newsapp.LanguagesSettingsHelper
import com.example.newsapp.LocaleHelper
import com.example.newsapp.R
import com.example.newsapp.ui.activities.HomeActivity


class SettingsFragment : Fragment() {

    private lateinit var adapter: ArrayAdapter<String>
    var list: MutableList<String> = ArrayList()
    private lateinit var spinner : Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()

        if (LanguagesSettingsHelper.retreiveDataFromSharedPreferences("lang", requireContext()) == "ar"
        ) {
            setEnglishInSpinner(0, 1)
        } else {
            setEnglishInSpinner(0, 0)
        }

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                convertAppLanguage(position)

            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }


    private fun convertAppLanguage(position:Int) {
        if (list[position] == "اللغة العربية") {
            saveCurrentLanguage("ar")
            showToastSelectedLang("Arabic")
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?, position: Int, id: Long
                ) {
                    convertedAppLangToSelected(position,"English","en")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        } else {
            saveCurrentLanguage("en")
            showToastSelectedLang("English")
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?, position: Int, id: Long
                ) {
                    convertedAppLangToSelected(position,"اللغة العربية","ar")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    private fun convertedAppLangToSelected(position:Int,spinnerItem: String,language: String) {
        if (list[position] == spinnerItem) {
            saveCurrentLanguage(language)
            showToastSelectedLang(language)
            startHomeActivity()
        }
    }

    private fun initSpinner() {

        getLanguageList()
        spinner = requireView().findViewById(R.id.spinner)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

    }



    private fun getLanguageList() {
        list.add(0, "English")
        list.add(1, "اللغة العربية")
    }

    private fun saveCurrentLanguage(language: String) {
        LanguagesSettingsHelper.putData(language, requireContext())
        Log.e("setting", "$language selected")
        LocaleHelper.setLocale(requireContext(), language)
    }

    private fun setEnglishInSpinner(spinnerRemoveIndex: Int, spinnerAddIndex: Int) {
        list.removeAt(spinnerRemoveIndex)
        list.add(spinnerAddIndex, "English")
        adapter.notifyDataSetChanged()
        adapter.setNotifyOnChange(true)

    }

    private fun startHomeActivity() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showToastSelectedLang(language: String) {
        Toast.makeText(requireContext(), language, Toast.LENGTH_LONG).show()

    }


}


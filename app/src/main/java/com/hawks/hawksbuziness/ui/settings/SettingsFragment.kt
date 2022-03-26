package com.hawks.hawksbuziness.ui.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hawks.hawksbuziness.R
import com.hawks.hawksbuziness.databinding.FragmentSettingsBinding
import com.hawks.hawksbuziness.local.dao.ProfileDao
import com.hawks.hawksbuziness.model.places.Data
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.ui.activity.SignupActivity
import com.hawks.hawksbuziness.ui.shops.ShopViewmodel
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    @Inject
    lateinit var preferenceManger: PreferenceManger

    @Inject
    lateinit var dao: ProfileDao

    lateinit var binding: FragmentSettingsBinding

    private val viemodel: ShopViewmodel by viewModels<ShopViewmodel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MainActivity.fragmentName = "SETTIGS"
        binding = FragmentSettingsBinding.inflate(inflater)
        binding.click = ClickHandler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (!preferenceManger.getPlace().isNullOrEmpty()){
            binding.placeData.text=preferenceManger.getPlace()
        }
        if (!preferenceManger.getLangugae().isNullOrEmpty()){
            binding.language.text=preferenceManger.getLangugae()
        }
        binding.logout.setOnClickListener {
            if (preferenceManger.getUserId().isNullOrEmpty()){
                startActivity(Intent(requireContext(), SignupActivity::class.java))
            }else {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.title))
                    .setMessage(resources.getString(R.string.logout_description))

                    .setNegativeButton("No") { dialog, which ->
                        // Respond to negative button press
                        dialog.dismiss()
                    }
                    .setPositiveButton("yes") { dialog, which ->
                        // Respond to positive button press
                        lifecycleScope.launch {
                            dao.clearProfile()
                            preferenceManger.removeAll()
                            findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
                        }
                    }
                    .show()
            }

        }

        binding.locationSelect.setOnClickListener {
            PlaceDialogFragment{
                it->onclicked(it)
            }.show(requireActivity().supportFragmentManager,"placedialog")
        }
        binding.languageSelect.setOnClickListener {
            LanguageDialogFragment{it->selectLanguage(it)}.show(requireActivity().supportFragmentManager,"languageDailog")
        }


    }

    private fun selectLanguage(it: com.hawks.hawksbuziness.model.languages.Data) {

        binding.language.text=it.language
        preferenceManger.saveLanguage(it.language)
    }

    private fun onclicked(it: Data) {
       preferenceManger.savePlace(it.place.toString())
        binding.placeData.text=preferenceManger.getPlace()
    }


    inner class ClickHandler {}

    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onResume: " )
        if (preferenceManger.getUserId().isNullOrEmpty()){
            binding.loginLogut.text="Login"
        }else{
            binding.loginLogut.text="Logout"
        }
    }


}
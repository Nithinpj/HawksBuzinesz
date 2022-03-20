package com.hawks.hawksbuziness

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hawks.hawksbuziness.databinding.FragmentAddUpdateBinding
import com.hawks.hawksbuziness.model.category.Data
import com.hawks.hawksbuziness.model.shop.Shop
import com.hawks.hawksbuziness.preferences.PreferenceManger
import com.hawks.hawksbuziness.ui.activity.MainActivity
import com.hawks.hawksbuziness.ui.shops.ShopViewmodel
import com.hawks.hawksbuziness.utill.ResponceState
import com.hawks.hawksbuziness.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddUpdateFragment : Fragment() {

    private lateinit var binding:FragmentAddUpdateBinding
    private val viemodel:ShopViewmodel by viewModels<ShopViewmodel>()
    private var categoryid:Int?=null
    private var cat_id:Int?=null
    private var place_id:Int?=null
    lateinit var data:Data
    var trademark:String?=null
    var privateLimited:String?=null
    var headOffice:String?=null
    var mulitstore:String?=null
    var shop_id:String?=null
    var updatType=""

    @Inject
    lateinit var preferenceManger: PreferenceManger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.fragmentName="UPDATE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddUpdateBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbar.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.data=viemodel
        binding.click=ClickHandler()
        arguments?.containsKey("type")?.let {
            if (arguments?.getString("type").equals("update")){
                updatType="update"

                val data =arguments?.getSerializable("data") as Shop?
                Log.e("TAG", "onViewCreated: "+data?.gstin )
                viemodel.shop_name.value=if(data?.name.equals("null")) "" else data?.name
                viemodel.address.value=if(data?.address.equals("null")) "" else data?.address
                viemodel.contactNo.value=if(data?.contact.equals("null")) "" else data?.contact
                viemodel.branch.value=if(data?.branch.equals("null")) "" else data?.branch
                viemodel.email.value=if(data?.email.equals("null")) "" else data?.email
                viemodel.person_incharge.value=if(data?.person_in_charge.equals("null")) "" else data?.person_in_charge
                viemodel.office_contact.value=if(data?.office_contact.equals("null")) "" else data?.office_contact
                viemodel.gstn.value=if (data?.gstin.equals("null"))"" else data?.gstin
                viemodel.trade_mark.value=data?.is_tm==1
                viemodel.private_limited.value=data?.is_pvt_ltd==1
                viemodel.whatsapp.value=if(data?.whatsapp.equals("null")) "" else data?.whatsapp
                viemodel.facebook.value=if(data?.facebook.equals("null")) "" else data?.facebook
                viemodel.instagram.value=if(data?.instagram.equals("null")) "" else data?.instagram
                viemodel.linkedin.value=if(data?.linkedin.equals("null")) "" else data?.linkedin
                viemodel.head_office.value=data?.is_head_office==1
                viemodel.multistore.value=data?.is_multi_store==1
                viemodel.webisite.value=if(data?.website.equals("null")) "" else data?.website
                shop_id=data?.id.toString()
                categoryid=data?.category_id
            }else{
                updatType="add"
            }
        }


        observeData()


    }
    private fun observeData(){
        viemodel.getAllCategories().observe(requireActivity(), Observer {
            setData(it)
        })

        viemodel.getAllPlces().observe(requireActivity(), Observer {
            setPlaceData(it)
        })

        viemodel.addLiveData.observe(requireActivity(), Observer {
            when(it){
                is ResponceState.Failiure -> showToast("Failed")
                is ResponceState.Loading -> {}
                is ResponceState.Succes -> {
                    showToast("Success")
                    findNavController().navigateUp()
                }
            }
        })

        viemodel.updateLiveData.observe(requireActivity(), Observer {
            when(it){
                is ResponceState.Failiure -> showToast("Failed")
                is ResponceState.Loading -> {}
                is ResponceState.Succes -> {
                    showToast("Success")
                    findNavController().navigateUp()
                }
            }
        })


    }

    private fun setPlaceData(it: List<com.hawks.hawksbuziness.model.places.Data>?) {
        val  list= it as ArrayList<com.hawks.hawksbuziness.model.places.Data>
        val  adapter=ArrayAdapter(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list)
        binding.placeData.adapter=adapter
    }

    private fun setData(it: List<Data>?) {
        val list=it as ArrayList<Data>
        if (categoryid!=null){
            for (i in list){
               if (i.id.equals(categoryid)){
                   data=i
               }
            }
            list.remove(data)
            list.add(0,data)
        }

        val  adapter=ArrayAdapter(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,list)
        binding.adapter=adapter

    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        val inflater = super.onGetLayoutInflater(savedInstanceState)
        val contextThemeWrapper: Context = ContextThemeWrapper(requireContext(), R.style.profile_page_theme)
        return inflater.cloneInContext(contextThemeWrapper)
    }

    private fun isValidate():Boolean{
        return when{

            viemodel.shop_name.value.isNullOrBlank()->{
                showToast("shop name not empty")
                false
            }

            viemodel.address.value.isNullOrBlank()->{
                showToast("Address not empty")
                false
            }
            viemodel.branch.value.isNullOrBlank()->{
                showToast("Branch not empty")
                false
            }
            viemodel.contactNo.value.isNullOrBlank()->{
                showToast("Contact not empty")
                false
            }
            else -> true
        }
    }



    inner  class ClickHandler{

        fun click(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent!!.selectedItem as Data
            cat_id = item.id

        }

        fun placeClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent!!.selectedItem as com.hawks.hawksbuziness.model.places.Data
            place_id = item.id

        }

        fun submit(view: View){
            if (isValidate()){
                trademark = if (viemodel.trade_mark.value == true) "1" else "0"
                privateLimited = if (viemodel.private_limited.value == true) "1" else "0"
                headOffice = if (viemodel.head_office.value == true) "1" else "0"
                mulitstore = if (viemodel.multistore.value == true) "1" else "0"
                val hashMap=HashMap<String,String>()
                hashMap["shop_name"]=viemodel.shop_name.value.toString().trim()
                hashMap["branch"]=viemodel.branch.value.toString().trim()
                hashMap["address"]=viemodel.address.value.toString().trim()
                hashMap["user_id"]=preferenceManger.getUserId().toString()
                hashMap["contact"]=viemodel.contactNo.value.toString().trim()
                hashMap["email"]=viemodel.email.value.toString().trim()
                hashMap["person_in_charge"]=viemodel.person_incharge.value.toString().trim()
                hashMap["latitude"]=preferenceManger.getLat()
                hashMap["longitude"]=preferenceManger.getLon()
                hashMap["office_contact"]=viemodel.office_contact.value.toString().trim()
                hashMap["category_id"]=cat_id.toString()
//                hashMap["sub_category_id"]=""
                hashMap["place_id"]=place_id.toString()
//                hashMap["country_id"]=""
                hashMap["gstin"]=viemodel.gstn.value.toString().trim()
                hashMap["is_tm"]=trademark.toString()
                hashMap["is_pvt_ltd"]=privateLimited.toString()
                hashMap["whatsapp"]=viemodel.whatsapp.value.toString().trim()
                hashMap["facebook"]=viemodel.facebook.value.toString().trim()
                hashMap["instagram"]=viemodel.instagram.value.toString().trim()
                hashMap["linkedin"]=viemodel.linkedin.value.toString().trim()
                hashMap["website"]=viemodel.webisite.value.toString().trim()
                hashMap["is_head_office"]=headOffice.toString()
                hashMap["is_multi_store"]=mulitstore.toString()
                hashMap["multi_store_name"]=viemodel.multistore_name.value.toString().trim()
                shop_id?.let {
                    hashMap["shop_id"]=shop_id.toString()
                }

                if (updatType == "add"){
                    viemodel.addBusiness(hashMap)
                }else{
                    viemodel.updateBusiness(hashMap)
                }
            }
        }
    }

}
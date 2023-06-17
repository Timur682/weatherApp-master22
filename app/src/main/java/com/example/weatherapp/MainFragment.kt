package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.adapters.AppAdapter
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.service.WeatherService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private var _binding: FragmentMainBinding? = null
    private val model: MainViewModel by activityViewModels()
    private val binding get() = _binding!!
    private val fList = listOf(
        HoursFragment.newInstance(),
        DaysFragment.newInstance()
    )

    private val tList = listOf(

        "Hours",
        "Days"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        CoroutineScope(Dispatchers.Main).launch {
            val api = retrofit.create(WeatherService::class.java)
            val model = api.getWeatherList(
                "a8f0e6f5e7b548bcb3e201553231506",
                "Tel-aviv",
                "4",
                "no",
                "no"
            )
            binding.apply {

            //    val temp = "${model.model.temp_c}°C"
                tvData.text = model.location.localtime
                tvCity.text = model.location.name
               tvCurrentTemp.text = model.current.temp_c.toString()
                tvCondition.text = model.current.condition.text
                Picasso.get().load("https:" + model.current.condition.icon).into(imageWeather)
            }
        }}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        model.liveDataCurrent.observe(viewLifecycleOwner){
            binding.apply {

                val temp = "${it.current.temp_c}°C"
                tvData.text = it.location.localtime
                tvCity.text = it.location.name
                tvCurrentTemp.text = temp
                tvCondition.text = it.current.condition.text
                Picasso.get().load("https:" + it.current.condition.icon).into(imageWeather)
            }}
    }

    private fun init() = with(binding){
        val adapter = AppAdapter(activity as FragmentActivity, fList)
        viewPager.adapter=adapter
        TabLayoutMediator(tabLayout, viewPager){
            tab, pos->tab.text= tList[pos].toString()
        }.attach()
    }
    private  fun permissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission(){
        if (!isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.service.WeatherService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: HourlyWeatherAdapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        fetchDataFromApi()
    }

    private fun initRecyclerView() {
        adapter = HourlyWeatherAdapter(ArrayList())
        binding.rcViewHours.layoutManager = LinearLayoutManager(requireContext())
        binding.rcViewHours.adapter = adapter
    }


    private fun fetchDataFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        CoroutineScope(Dispatchers.Main).launch {
            val api = retrofit.create(WeatherService::class.java)
            val weatherData = api.getWeatherList(
                "a8f0e6f5e7b548bcb3e201553231506",
                "tel-aviv",
                "3",
                "no",
                "no"
            )

            val hourlyWeatherList = weatherData.forecast.forecastday[0].hour
            adapter.setData(hourlyWeatherList)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}

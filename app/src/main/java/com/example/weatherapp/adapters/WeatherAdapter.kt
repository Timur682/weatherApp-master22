import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.models.ForecastDayModel
import com.squareup.picasso.Picasso

class WeatherAdapter(private var forecastList: List<ForecastDayModel>) : RecyclerView.Adapter<WeatherAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    fun setData(data: List<ForecastDayModel>) {
        forecastList = data
        notifyDataSetChanged()
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(item: ForecastDayModel) {
            val temp = "${item.day.maxtemp_c}°C - ${item.day.mintemp_c}°C"
            with(binding) {
                tvDate.text = item.date
                tvTemp.text = temp

                // Bind any other relevant weather data to the views

                // Example: Load and display weather icon using Picasso
                Picasso.get().load(item.day.condition.icon).into(imView)
            }
        }
    }
}

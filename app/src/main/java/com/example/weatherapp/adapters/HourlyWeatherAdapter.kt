import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.models.HourModel
import com.squareup.picasso.Picasso

class HourlyWeatherAdapter(private var data: ArrayList<HourModel>) :
        RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val binding = ListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
                return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val hour = data[position]
                holder.bind(hour)
        }

        override fun getItemCount(): Int {
                return data.size
        }

        fun setData(newData: List<HourModel>) {
                data.clear()
                data.addAll(newData)
                notifyDataSetChanged()
        }

        inner class ViewHolder(private val binding: ListItemBinding) :
                RecyclerView.ViewHolder(binding.root) {

                fun bind(hour: HourModel) {
                        // Bind hour data to the view elements in the layout
                        binding.tvDate.text = hour.time
                        binding.tvCondition.text = hour.condition.text
                        binding.tvTemp.text = hour.temp_c.toString()
                        Picasso.get().load(hour.condition.icon).into(binding.imView)

                }
        }}

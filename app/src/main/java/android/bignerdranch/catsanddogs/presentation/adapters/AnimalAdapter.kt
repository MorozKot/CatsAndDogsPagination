package android.bignerdranch.catsanddogs.presentation.adapters

import android.bignerdranch.catsanddogs.databinding.ItemBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AnimalAdapter(private val movieList: List<String>): RecyclerView.Adapter<AnimalViewHolder>() {

    private var list = mutableListOf<String>()

    init {
        list.addAll(movieList)

        Log.d("проверка AnimalAdapter", "$list")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val dogImageUrl = list[position]

        with(holder) {
            val ivDogImage = binding.itemPoster
            Picasso.get().load(dogImageUrl).into(ivDogImage)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addNewItems(items: List<String>){
        list.addAll(items)
        notifyDataSetChanged()
    }
}

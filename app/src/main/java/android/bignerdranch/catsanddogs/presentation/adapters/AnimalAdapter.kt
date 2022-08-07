package android.bignerdranch.catsanddogs.presentation.adapters

import android.bignerdranch.catsanddogs.databinding.ItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AnimalAdapter(private val dogsList: List<String>) : RecyclerView.Adapter<AnimalViewHolder>() {

    private var mutableDogList = mutableListOf<String>()

    init {
        mutableDogList.addAll(dogsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val dogImageUrl = mutableDogList[position]

        with(holder) {
            val ivDogImage = binding.itemPoster
            Picasso.get().load(dogImageUrl).into(ivDogImage)
        }
    }

    override fun getItemCount(): Int {
        return mutableDogList.size
    }

    fun addNewItems(items: List<String>) {
        mutableDogList.addAll(items)
        notifyDataSetChanged()
    }
}

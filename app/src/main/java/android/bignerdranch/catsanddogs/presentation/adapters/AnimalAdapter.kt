package android.bignerdranch.catsanddogs.presentation.adapters

import android.bignerdranch.catsanddogs.databinding.ItemBinding
import android.bignerdranch.catsanddogs.data.network.model.CatsFactDto
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AnimalAdapter : RecyclerView.Adapter<AnimalViewHolder>() {

/*    var catsFactDtoList = emptyList<String>()

    fun setCatsList(catsResponse: CatsFactDto) {
        catsFactDtoList = listOf(catsResponse.fact)
        notifyDataSetChanged()

        Log.d("listDogs cats AnimalAdapter", "$catsFactDtoList")
    }*/

    var listDogs = emptyList<String>()

    fun setDogList(dogsResponse: String) {
        listDogs = listOf(dogsResponse)
        notifyDataSetChanged()

        Log.d("listDogs AnimalAdapter", "$listDogs")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val dogImageUrl = listDogs[position]

        with(holder) {
            val ivDogImage = binding.itemPoster
            Picasso.get().load(dogImageUrl).into(ivDogImage)

/*            if (catsFactDtoList.isNotEmpty()) { //без этой проверки приложение крашится
                val catFact = catsFactDtoList[position]
                val itemText = binding.itemText
                itemText.text = catFact
            }*/
        }
    }

    override fun getItemCount(): Int {
        return listDogs.size
    }
}

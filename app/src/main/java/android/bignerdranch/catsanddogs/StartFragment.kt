package android.bignerdranch.catsanddogs

import android.bignerdranch.catsanddogs.databinding.FragmentStartBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment() {

    private lateinit var adapter: CatsDogsAdapter
    private lateinit var binding: FragmentStartBinding
    private lateinit var catsDogsViewModel: CatsDogsViewModel
    private lateinit var recyclerViewAnimals: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catsDogsViewModel = ViewModelProvider(this).get(CatsDogsViewModel::class.java)

        adapter = CatsDogsAdapter()

        recyclerViewAnimals = binding.recyclerViewAnimals

        recyclerViewAnimals.adapter = adapter

        val linearlayoutManager = LinearLayoutManager(activity?.baseContext)
        binding.recyclerViewAnimals.layoutManager = linearlayoutManager
        setMovieRecyclerListener(linearlayoutManager)

        recyclerViewAnimals.setHasFixedSize(true)

        catsDogsViewModel.load()

        catsDogsViewModel.dogsList.observe(viewLifecycleOwner) {
            adapter.setDogList(it)
        }

        catsDogsViewModel.catsList.observe(viewLifecycleOwner) {
            adapter.setCatsList(it)
        }
    }

    private fun setMovieRecyclerListener(linearlayoutManager: LinearLayoutManager) {

        recyclerViewAnimals.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearlayoutManager.childCount
                val totalItemCount = linearlayoutManager.itemCount
                val pastVisibleItemCount = linearlayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItemCount >= totalItemCount
                    && pastVisibleItemCount >= 0
                ) {
                    catsDogsViewModel.load()
                }
            }
        })
    }
}
    

 







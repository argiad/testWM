package com.steegler.walmart.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.steegler.walmart.R
import com.steegler.walmart.databinding.ItemCountryBinding
import com.steegler.walmart.domain.model.Country

class CountriesAdapter :
    RecyclerView.Adapter<CountriesAdapter.ItemViewHolder>() {

    private var countries: MutableList<Country> = mutableListOf()

    fun update(countries: List<Country>) {
        this.countries.forEachIndexed { i, _ ->
            this.notifyItemRemoved(i)
        }
        this.countries.clear()
        countries.forEachIndexed { i, country ->
            this.countries.add(country)
            this.notifyItemInserted(i)
        }
    }

    class ItemViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {
            binding.ivFlag.load(country.flag) {
                placeholder(R.mipmap.ic_launcher_round)
                crossfade(true)
            }
            binding.country = country
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(countries[position])
    }

}
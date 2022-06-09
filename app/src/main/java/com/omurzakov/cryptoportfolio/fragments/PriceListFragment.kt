package com.omurzakov.cryptoportfolio.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omurzakov.cryptoportfolio.architecture.BaseFragment
import com.omurzakov.cryptoportfolio.databinding.FragmentPriceListBinding
import com.omurzakov.cryptoportfolio.databinding.RowCryptoListBinding
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.viewmodels.PriceListViewModel

class PriceListFragment :
    BaseFragment<FragmentPriceListBinding, PriceListViewModel>(PriceListViewModel::class) {

    private val priceList: MutableList<Crypto> = mutableListOf()
    private lateinit var adapter: CryptoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    inner class CryptoDiffUtils(
        private val oldList: MutableList<Crypto>,
        private val newList: MutableList<Crypto>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].currencyCode == newList[newItemPosition].currencyCode &&
                    oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
    }

    inner class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

        inner class CryptoViewHolder(val binding: RowCryptoListBinding) :
            RecyclerView.ViewHolder(binding.root) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
            return CryptoViewHolder(
                RowCryptoListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
            val crypto = priceList[position]
            holder.binding.cryptoName.text = crypto.name
            holder.binding.cryptoLogo.setImageResource(
                resources.getIdentifier(
                    crypto.image, "drawable", "com.omurzakov.cryptoportfolio"
                )
            )
            holder.binding.cryptoPrice.text = crypto.price.toString() + " $"

            holder.binding.root.setOnClickListener {
                val action =
                    PriceListFragmentDirections.actionPriceListFragmentToAddCryptoFragment()
                action.cryptoId = priceList[holder.adapterPosition].id!!
                findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int = priceList.size
    }

    override val bindingInflater: (LayoutInflater) -> FragmentPriceListBinding
        get() = FragmentPriceListBinding::inflate

    override fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = CryptoAdapter()
        binding.priceList.layoutManager = layoutManager
        binding.priceList.adapter = adapter

        viewModel
            .getAll()
            .observe(
                viewLifecycleOwner
            ) { t ->
                val diffCallback = CryptoDiffUtils(priceList, t!!)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                diffResult.dispatchUpdatesTo(adapter)
                priceList.clear()
                priceList.addAll(t)
            }
    }

    override fun onActivityCreated() {}
}
package com.omurzakov.cryptoportfolio.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omurzakov.cryptoportfolio.architecture.BaseFragment
import com.omurzakov.cryptoportfolio.databinding.FragmentHistoryListBinding
import com.omurzakov.cryptoportfolio.databinding.RowCryptoListBinding
import com.omurzakov.cryptoportfolio.databinding.RowHistoryListBinding
import com.omurzakov.cryptoportfolio.model.PurchaseHistory
import com.omurzakov.cryptoportfolio.viewmodels.HistoryListViewModel
import kotlinx.coroutines.launch

class HistoryListFragment :
    BaseFragment<FragmentHistoryListBinding, HistoryListViewModel>(HistoryListViewModel::class) {

    private val historyList: MutableList<PurchaseHistory> = mutableListOf()
    private lateinit var adapter: CryptoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    inner class CryptoDiffUtils(
        private val oldList: MutableList<PurchaseHistory>,
        private val newList: MutableList<PurchaseHistory>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].cryptoId == newList[newItemPosition].cryptoId &&
                    oldList[oldItemPosition].amount == newList[newItemPosition].amount
        }

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
    }

    inner class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

        inner class CryptoViewHolder(val binding: RowHistoryListBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
            return CryptoViewHolder(
                RowHistoryListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
            val purchaseHistory = historyList[position]

            holder.binding.cryptoName.text = purchaseHistory.cryptoName
            holder.binding.cryptoLogo.setImageResource(
                resources.getIdentifier(
                    purchaseHistory.cryptoImage, "drawable", "com.omurzakov.cryptoportfolio"
                )
            )

            holder.binding.trash.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.deleteHistory(purchaseHistory)
                }
            }

            if (purchaseHistory.isPurchased) {
                holder.binding.cryptoPrice.setTextColor(Color.GREEN)
                holder.binding.cryptoPrice.text = "+ " + purchaseHistory.amount.toString() + " $"
            } else {
                holder.binding.cryptoPrice.setTextColor(Color.RED)
                holder.binding.cryptoPrice.text = "- " + purchaseHistory.amount.toString() + " $"
            }
        }

        override fun getItemCount(): Int = historyList.size
    }

    override val bindingInflater: (LayoutInflater) -> FragmentHistoryListBinding
        get() = FragmentHistoryListBinding::inflate

    override fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = CryptoAdapter()
        binding.historyList.layoutManager = layoutManager
        binding.historyList.adapter = adapter

        viewModel
            .getAll()
            .observe(viewLifecycleOwner) { t ->
                val diffCallback = CryptoDiffUtils(historyList, t!!)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                diffResult.dispatchUpdatesTo(adapter)
                historyList.clear()
                historyList.addAll(t)
            }
    }

    override fun onActivityCreated() {}
}
package com.omurzakov.cryptoportfolio.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omurzakov.cryptoportfolio.architecture.BaseFragment
import com.omurzakov.cryptoportfolio.databinding.FragmentPortfolioValueBinding
import com.omurzakov.cryptoportfolio.databinding.RowCryptoListBinding
import com.omurzakov.cryptoportfolio.model.Crypto
import com.omurzakov.cryptoportfolio.model.PurchaseHistory
import com.omurzakov.cryptoportfolio.viewmodels.PortfolioValueViewModel

class PortfolioValueFragment :
    BaseFragment<FragmentPortfolioValueBinding, PortfolioValueViewModel>(PortfolioValueViewModel::class) {

    private val assetList: MutableList<Crypto> = mutableListOf()
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
            return oldList[oldItemPosition].price == newList[newItemPosition].price &&
                    oldList[oldItemPosition].purchasedAmount == newList[newItemPosition].purchasedAmount
        }

        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
    }

    inner class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

        inner class CryptoViewHolder(val binding: RowCryptoListBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
            return CryptoViewHolder(
                RowCryptoListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
            val asset = assetList[position]

            holder.binding.cryptoName.text = asset.name
            holder.binding.cryptoLogo.setImageResource(
                resources.getIdentifier(
                    asset.image, "drawable", "com.omurzakov.cryptoportfolio"
                )
            )

            holder.binding.cryptoPrice.text = "${asset.purchasedAmount} $"

            holder.binding.root.setOnClickListener {
                val action =
                    PortfolioValueFragmentDirections.actionPortfolioValueFragmentToRemoveCryptoFragment()
                action.cryptoId = assetList[holder.adapterPosition].id!!
                findNavController().navigate(action)
            }
        }

        override fun getItemCount(): Int = assetList.size
    }

    override val bindingInflater: (LayoutInflater) -> FragmentPortfolioValueBinding
        get() = FragmentPortfolioValueBinding::inflate

    override fun initViews() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = CryptoAdapter()
        binding.assetList.layoutManager = layoutManager
        binding.assetList.adapter = adapter
        binding.portfolioValue.text = "${viewModel.getSumAllPurchasedCrypto()} $"

        viewModel
            .getAllPurchasedCrypto()
            .observe(viewLifecycleOwner) { t ->
                val diffCallback = CryptoDiffUtils(assetList, t!!)
                val diffResult = DiffUtil.calculateDiff(diffCallback)
                diffResult.dispatchUpdatesTo(adapter)
                assetList.clear()
                assetList.addAll(t)
            }
    }

    override fun onActivityCreated() {}
}
package com.omurzakov.cryptoportfolio.fragments

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.omurzakov.cryptoportfolio.architecture.BaseFragment
import com.omurzakov.cryptoportfolio.databinding.FragmentRemoveCryptoBinding
import com.omurzakov.cryptoportfolio.viewmodels.RemoveCryptoViewModel
import kotlinx.coroutines.launch

class RemoveCryptoFragment :
    BaseFragment<FragmentRemoveCryptoBinding, RemoveCryptoViewModel>(RemoveCryptoViewModel::class) {
    private val arguments: RemoveCryptoFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater) -> FragmentRemoveCryptoBinding
        get() = FragmentRemoveCryptoBinding::inflate

    override fun initViews() {
        viewModel.cryptoId = arguments.cryptoId

        lifecycleScope.launch {
            viewModel.crypto = viewModel.findCryptoById()
        }.invokeOnCompletion {
            fillPurchaseHistory()
            fillLayout()
        }

        setInteractionListeners()
    }

    private fun fillPurchaseHistory() {
        viewModel.purchaseHistory.cryptoName = viewModel.crypto.name
        viewModel.purchaseHistory.cryptoPrice = viewModel.crypto.price
        viewModel.purchaseHistory.cryptoImage = viewModel.crypto.image
        viewModel.purchaseHistory.cryptoCurrencyCode = viewModel.crypto.currencyCode
        viewModel.purchaseHistory.isPurchased = false
    }

    private fun fillLayout() {
        if (viewModel.crypto.price != 0.0F) {
            binding.amount.text = "${viewModel.crypto.purchasedAmount} $"
        }
        if (viewModel.crypto.image.isNotEmpty()) {
            binding.cryptoLogo.setImageResource(
                resources.getIdentifier(
                    viewModel.crypto.image, "drawable", "com.omurzakov.cryptoportfolio"
                )
            )
        }
        if (viewModel.crypto.currencyCode.isNotEmpty()) {
            binding.cryptoQuantity.setHint(viewModel.crypto.currencyCode)
        }
    }

    private fun setInteractionListeners() {
        binding.usdQuantity.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() != "") {

                    viewModel.purchaseHistory.amount = p0.toString().toFloat()

                    val amount = viewModel.crypto.purchasedAmount
                    val price = viewModel.crypto.price

                    if (amount < price) {
                        binding.cryptoQuantity.text =
                            "${viewModel.purchaseHistory.amount / viewModel.crypto.price}"
                    } else if ((amount > price)) {
                        binding.cryptoQuantity.text =
                            "${viewModel.crypto.price / viewModel.purchaseHistory.amount}"

                    } else {
                        binding.cryptoQuantity.text = "1"
                    }
                }
            }
        })

        binding.cryptoQuantity.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                viewModel.purchaseHistory.quantity = p0.toString().toFloat()
            }
        })

        binding.saveButton.setOnClickListener {
            if ((binding.usdQuantity.text.isNotEmpty() || binding.cryptoQuantity.text.isNotEmpty())) {
                val newAmount =
                    viewModel.crypto.purchasedAmount - binding.usdQuantity.text.toFloat()
                if (newAmount > 0) {
                    binding.usdQuantity.setError(null)
                    binding.cryptoQuantity.setError(null)

                    viewModel.crypto.purchasedAmount = newAmount

                    lifecycleScope.launch {
                        viewModel.savePurchaseHistory()
                        viewModel.updateCrypto()
                    }.invokeOnCompletion {
                        finishCurrentFragment()
                    }
                } else {
                    binding.usdQuantity.setError("You don't have such an assets")
                }
            } else {
                binding.usdQuantity.setError("Cannot be empty")
                binding.cryptoQuantity.setError("Cannot be empty")
            }
        }
    }

    override fun onActivityCreated() {}
}
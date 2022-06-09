package com.omurzakov.cryptoportfolio.views

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.omurzakov.cryptoportfolio.R
import com.omurzakov.cryptoportfolio.databinding.ViewTextInputBinding

class TextInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs) {

    init {
        init(context, attrs)
    }

    private lateinit var binding: ViewTextInputBinding

    var text: String
        get() = binding.textInputEditText.text.toString()
        set(text) {
            binding.textInputEditText.setText(text)
        }

    private fun init(context: Context, attrs: AttributeSet?) {
        binding = ViewTextInputBinding.inflate(LayoutInflater.from(context), this, true)
        if (attrs != null) {
            loadAttributes(attrs)
        }
    }

    private fun loadAttributes(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TextInputView)
        val hint = attributes.getString(R.styleable.TextInputView_hint)
        binding.textInputLayout.setHint(hint)
        attributes.recycle()
    }

    fun setError(error: String?) {
        binding.textInputLayout.error = error
    }

    fun setHint(hint: String?) {
        binding.textInputLayout.hint = hint
    }

    fun addTextChangeListener(watcher: TextWatcher) {
        binding.textInputEditText.addTextChangedListener(watcher)
    }
}
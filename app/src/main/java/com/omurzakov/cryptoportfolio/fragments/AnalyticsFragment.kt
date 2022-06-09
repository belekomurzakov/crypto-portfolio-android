package com.omurzakov.cryptoportfolio.fragments

import android.graphics.Color
import android.view.LayoutInflater
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.omurzakov.cryptoportfolio.architecture.BaseFragment
import com.omurzakov.cryptoportfolio.databinding.FragmentAnalyticsBinding
import com.omurzakov.cryptoportfolio.viewmodels.AnalyticsViewModel

class AnalyticsFragment :
    BaseFragment<FragmentAnalyticsBinding, AnalyticsViewModel>(AnalyticsViewModel::class) {

    private var pieChart: PieChart? = null

    override val bindingInflater: (LayoutInflater) -> FragmentAnalyticsBinding
        get() = FragmentAnalyticsBinding::inflate

    override fun initViews() {
        pieChart = binding.pieChart
        setupPieChart()
        loadPieChartData()
    }

    private fun setupPieChart() {
        pieChart!!.isDrawHoleEnabled = true
        pieChart!!.setUsePercentValues(true)
        pieChart!!.setEntryLabelTextSize(12f)
        pieChart!!.setEntryLabelColor(Color.BLACK)
        pieChart!!.centerText = "${viewModel.getSumAllPurchasedCrypto()} $"
        pieChart!!.setCenterTextSize(24f)
        pieChart!!.description.isEnabled = false
        pieChart!!.legend.isEnabled = false
    }

    private fun loadPieChartData() {
        val sum = viewModel.getSumAllPurchasedCrypto()
        val list = viewModel.getAllPurchasedCryptoNoLive()

        val entries = ArrayList<PieEntry>()
        list.forEach { entries.add(PieEntry(1/(sum/it.purchasedAmount), it.currencyCode)) }

        val colors = ArrayList<Int>()
        ColorTemplate.MATERIAL_COLORS.forEach { colors.add(it) }
        ColorTemplate.VORDIPLOM_COLORS.forEach { colors.add(it) }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart!!.data = data
        pieChart!!.invalidate()
        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
    }


    override fun onActivityCreated() {}
}
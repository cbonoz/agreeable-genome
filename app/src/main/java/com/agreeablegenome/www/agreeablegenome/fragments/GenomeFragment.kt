package com.agreeablegenome.www.agreeablegenome.fragments


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.agreeablegenome.www.agreeablegenome.R
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import android.widget.TextView
import com.agreeablegenome.www.agreeablegenome.fragments.views.RadarMarkerView
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry


class GenomeFragment : Fragment() {

    private var mChart: RadarChart? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_genome, container, false)

        getGenomeInfo(view)

        return view
    }

    private fun getGenomeInfo(view: View) {
        renderChart(view)
    }


    private fun renderChart(view: View) {
        val tv = view.findViewById(R.id.textView) as TextView
        tv.setTextColor(Color.WHITE)
        tv.setBackgroundColor(Color.rgb(60, 65, 82))

        mChart = view.findViewById(R.id.chart1) as RadarChart
        mChart!!.setBackgroundColor(Color.rgb(60, 65, 82))

        mChart!!.description.isEnabled = false

        mChart!!.webLineWidth = 1f
        mChart!!.webColor = Color.LTGRAY
        mChart!!.webLineWidthInner = 1f
        mChart!!.webColorInner = Color.LTGRAY
        mChart!!.webAlpha = 100

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        val mv = RadarMarkerView(this.context, R.layout.radar_markerview)
        mv.setChartView(mChart) // For bounds control
        mChart!!.marker = mv // Set the marker to the chart

        setData()

        mChart!!.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad)

        val xAxis = mChart!!.xAxis
        xAxis.textSize = 9f
        xAxis.yOffset = 0f
        xAxis.xOffset = 0f
        xAxis.valueFormatter = object : IAxisValueFormatter {

            private val mActivities = arrayOf("Protein", "Carbohydrates", "Vegetables", "Pasta", "Pizza")

            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return mActivities[value.toInt() % mActivities.size]
            }
        }
        xAxis.textColor = Color.WHITE

        val yAxis = mChart!!.yAxis
        yAxis.setLabelCount(5, false)
        yAxis.textSize = 9f
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 80f
        yAxis.setDrawLabels(false)

        val l = mChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 5f
        l.textColor = Color.WHITE
    }

    // TODO: replace with non-simulated data.
    fun setData() {

        val mult = 80f
        val min = 20f
        val cnt = 5

        val entries1 = ArrayList<RadarEntry>()
        val entries2 = ArrayList<RadarEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until cnt) {
            val val1 = (Math.random() * mult).toFloat() + min
            entries1.add(RadarEntry(val1))

            val val2 = (Math.random() * mult).toFloat() + min
            entries2.add(RadarEntry(val2))
        }

        val set1 = RadarDataSet(entries1, "Last Week")
        set1.color = Color.rgb(103, 110, 129)
        set1.fillColor = Color.rgb(103, 110, 129)
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)

        val set2 = RadarDataSet(entries2, "This Week")
        set2.color = Color.rgb(121, 162, 175)
        set2.fillColor = Color.rgb(121, 162, 175)
        set2.setDrawFilled(true)
        set2.fillAlpha = 180
        set2.lineWidth = 2f
        set2.isDrawHighlightCircleEnabled = true
        set2.setDrawHighlightIndicators(false)

        val sets = ArrayList<IRadarDataSet>()
        sets.add(set1)
        sets.add(set2)

        val data = RadarData(sets)
        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)

        mChart!!.setData(data)
        mChart!!.invalidate()
    }
}

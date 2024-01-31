package com.example.mutualfundsapplicaiton.presentation.funds_info.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mutualfundsapplicaiton.domain.model.FundsInfo
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import com.example.mutualfundsapplicaiton.common.Constants.GRAPH_HEIGHT

@Composable
fun Graph(
    info: FundsInfo
){
    val data = info.data

    //create Datapoint
//    val date_dp = mutableListOf<DataPoint>()
    val navData = mutableListOf<DataPoint>()

    var idx = 0

    data.forEach { i ->
//        val date = SimpleDateFormat("dd-MM-yyyy").parse(i.date).time / 1000
//        date_dp.add(idx, DataPoint(idx.toFloat(), date.toFloat()))
        navData.add(idx, DataPoint(idx.toFloat(), i.nav.toFloat()))
        idx += 1
    }

    //Graph
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    navData,
                    LinePlot.Connection(Color.Blue, 2.dp),
                    LinePlot.Intersection(Color.Blue, 5.dp),
                    LinePlot.Highlight(Color.Red, 5.dp),
                    LinePlot.AreaUnderLine(Color.Blue, 0.3f)
                )
            ),
            horizontalExtraSpace = 12.dp,
            xAxis = LinePlot.XAxis(
                unit = 1f,
                roundToInt = false,
                steps = navData.size),
            yAxis = LinePlot.YAxis(steps = 3, roundToInt = true),
            paddingRight = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(GRAPH_HEIGHT.dp)
    )
}
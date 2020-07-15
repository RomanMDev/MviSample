package com.example.mvisample.ui.spisok

import com.example.mvisample.ObservableSourceFragment
import com.example.mvisample.R
import com.example.mvisample.ui.event.PointsUiEvent
import io.reactivex.functions.Consumer

class PointsFragment : ObservableSourceFragment<PointsUiEvent>(R.layout.fragment_points),
    Consumer<PointsViewModel> {



    override fun accept(viewModel: PointsViewModel) {

    }

}
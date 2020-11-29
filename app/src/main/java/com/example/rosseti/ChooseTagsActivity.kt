package com.example.rosseti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rosseti.adapters.ChooseTagsAdapter
import com.example.rosseti.domain.entities.Topic
import com.example.rosseti.presentation.ChooseTagsPresenter
import com.example.rosseti.presentation.ChooseTagsView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_choose_tags.*
import kotlinx.android.synthetic.main.fragment_create_app.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ChooseTagsActivity : MvpAppCompatActivity(), ChooseTagsView {

    @Inject
    lateinit var daggerPresenter: ChooseTagsPresenter

    @InjectPresenter
    lateinit var presenter: ChooseTagsPresenter

    @ProvidePresenter
    fun providePresenter() = daggerPresenter

    private val chooseTagsAdapter = ChooseTagsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_tags)
        tags_recycler_view.layoutManager = LinearLayoutManager(this)
        tags_recycler_view.adapter = chooseTagsAdapter
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    override fun loadTags(tags: List<Topic>) {
        chooseTagsAdapter.setTags(tags)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
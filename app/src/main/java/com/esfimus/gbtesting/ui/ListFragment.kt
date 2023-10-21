package com.esfimus.gbtesting.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.gbtesting.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

private const val LIST_ITEMS = "list items count"

class ListFragment : Fragment() {

    private var _ui: FragmentListBinding? = null
    private val ui get() = _ui!!
    private var itemsCount: Int = 0
    private lateinit var recyclerAdapter: RecyclerAdapter

    companion object {
        fun newInstance(itemsCount: Int) =
            ListFragment().apply {
                arguments = Bundle().apply { putInt(LIST_ITEMS, itemsCount) }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val bundleNumber = it.getInt(LIST_ITEMS)
            itemsCount = if (bundleNumber > 100) {
                100
            } else {
                bundleNumber
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _ui = FragmentListBinding.inflate(inflater, container, false)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.hideKeyboard()
        initView()
    }

    private fun initView() {
        val title = "$itemsCount items"
        ui.fragmentTitle.text = title

        recyclerAdapter = RecyclerAdapter(itemsCount)
        ui.recyclerLayout.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        recyclerAdapter.setClickListener(object : OnListItemClick {
            override fun onClick(position: Int, cardView: View) {
                snackMessage("Card #${position + 1}")
                if (cardView.alpha == .2f) {
                    cardView.alpha = 1f
                } else {
                    cardView.alpha = .2f
                }
            }
        })

        ui.addButton.setOnClickListener { addItem() }
    }

    private fun addItem() {
        itemsCount++
        recyclerAdapter.addItem()
        val updatedTitle = "$itemsCount items"
        ui.fragmentTitle.text = updatedTitle
    }

    private fun View.hideKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
            hideSoftInputFromWindow(windowToken, 0)
        }
    }

    private fun snackMessage(text: String) {
        Snackbar.make(ui.root, text, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }
}
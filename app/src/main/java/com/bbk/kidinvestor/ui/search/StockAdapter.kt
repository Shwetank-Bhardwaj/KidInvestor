package com.bbk.kidinvestor.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbk.kidinvestor.R
import com.bbk.kidinvestor.ui.search.model.Stock
import java.util.*
import kotlin.collections.ArrayList

class StockAdapter(private var context: Context?, var stocks: ArrayList<Stock>) :
    RecyclerView.Adapter<StockAdapter.ViewHolder>(),
    Filterable {

    private var stocksFilterList: ArrayList<Stock>

    init {
        stocksFilterList = stocks
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_stock, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val stock = stocksFilterList[position]
        stock.apply {
            viewHolder.symbol.text = symbol
            viewHolder.companyName.text = name
            viewHolder.amount.text = "\$${open}"
            val change: String = String.format("%.2f", change)
            if (change.contains("-")) {
                viewHolder.profitLossImage.setImageDrawable(context?.getDrawable(R.drawable.ic_loss_red_24))
            } else {
                viewHolder.profitLossImage.setImageDrawable(context?.getDrawable(R.drawable.ic_profit_green_24))
            }
            viewHolder.changePercentage.text = "\$$change"
        }
    }

    override fun getItemCount() = stocksFilterList.size

    fun submitList(updateStocks: List<Stock>) {
        stocks.clear()
        stocks.addAll(updateStocks);
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    stocksFilterList = stocks
                } else {
                    val resultList = ArrayList<Stock>()
                    for (row in stocks) {
                        if (row.symbol.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        } else if (row.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        } else if (row.exchange.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    stocksFilterList = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = stocksFilterList
                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                stocksFilterList = results?.values as ArrayList<Stock>
                notifyDataSetChanged()

            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val symbol: TextView
        val companyName: TextView
        val amount: TextView
        val profitLossImage: ImageView
        val changePercentage: TextView

        init {
            symbol = view.findViewById(R.id.symbol)
            companyName = view.findViewById(R.id.companyName)
            amount = view.findViewById(R.id.amount)
            profitLossImage = view.findViewById(R.id.profitLossImage)
            changePercentage = view.findViewById(R.id.changePercentage)
        }
    }

}
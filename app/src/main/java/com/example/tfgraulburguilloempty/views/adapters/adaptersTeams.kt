package com.example.tfgraulburguilloempty.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.model.Equipos
import com.squareup.picasso.Picasso


class adapterTeams(val context: Context,
                       val layout: Int
) : RecyclerView.Adapter<adapterTeams.ViewHolder>() {

    private var dataList: List<Equipos> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setEquipos(equipos: List<Equipos>) {
        this.dataList = equipos
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Equipos){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivteams = itemView.findViewById(R.id.ivteams) as ImageView
            val tvnombrecategoria = itemView.findViewById(R.id.tvnombreteam) as TextView

            tvnombrecategoria.text = dataItem.Nombre

            // foto de internet a traves de Picasso
            Picasso.get().load("https://a.espncdn.com/combiner/i?img=/i/teamlogos/nba/500/${dataItem.idimagen}.png&h=200&w=200\n").into(ivteams)


            itemView.tag = dataItem

        }

    }
}
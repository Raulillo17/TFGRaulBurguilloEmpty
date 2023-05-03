package com.example.tfgraulburguilloempty.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.model.Respuesta
import com.example.tfgraulburguilloempty.views.model.Team
import com.squareup.picasso.Picasso


class adapterTeams(val context: Context,
                       val layout: Int
) : RecyclerView.Adapter<adapterTeams.ViewHolder>() {

    private var dataList: List<Team> = emptyList()

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

    internal fun setEquipos(equipos: List<Team>) {
        this.dataList = equipos
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Team){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar


            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivteams = itemView.findViewById(R.id.ivteams) as ImageView
            val tvnombrecategoria = itemView.findViewById(R.id.tvnombreteam) as TextView



            if (dataItem.logo == null || dataItem.name == null || dataItem.nbaFranchise == false) {
                        View.GONE
                    } else {
                        Picasso.get().load("${dataItem.logo}").into(ivteams)
                        tvnombrecategoria.text = dataItem.name
                    }
                //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
                //ivteams.setImageResource(id)
                //tvnombrecategoria.text = "Texto predeterminado"
                // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}

package com.example.tfgraulburguilloempty.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.model.Player
import com.squareup.picasso.Picasso

class adapterPlayers(
    val context: Context,
    val layout: Int
) : RecyclerView.Adapter<adapterPlayers.ViewHolder>() {

    private var dataList: List<Player> = emptyList()

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

    internal fun setJugadores(jugadores: List<Player>) {
        // Obtener los datos originales para el RecyclerView
        val dataList = jugadores
        // Filtrar los elementos nulos de la lista de datos
        val filteredDataList = dataList.filter { item ->
            item.firstName != null && item.lastName != null && item.age != null && item.position != null && item.headShotURL != null && item.team != null
        }
        this.dataList = filteredDataList
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar

            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvNombrePlayerAll = itemView.findViewById(R.id.tvNombrePlayerAll) as TextView
            val tvPosicionarellenarAll = itemView.findViewById(R.id.tvPosicionarellenarAll) as TextView
            val tvEdadaRellenarAll = itemView.findViewById(R.id.tvEdadaRellenarAll) as TextView
            val tvnombreTeamAll = itemView.findViewById(R.id.tvnombreTeamAll) as TextView
            val tvLastNameAll = itemView.findViewById(R.id.tvLastNameAll) as TextView

            val ivPlayerAll = itemView.findViewById(R.id.ivPlayerAll) as ImageView


/*
            if (dataItem.headShotURL == null) {
                val id =
                    context.resources.getIdentifier("default_img", "drawable", context.packageName)
                ivPlayerAll.setImageResource(id)

            }*/
            //else if (dataItem.headShotURL != null){
                tvNombrePlayerAll.text = dataItem.firstName
                tvPosicionarellenarAll.text = dataItem.position.toString()
                tvEdadaRellenarAll.text = dataItem.age
                tvnombreTeamAll.text = dataItem.team
                tvLastNameAll.text = dataItem.lastName


                Picasso.get().load("${dataItem.headShotURL}").into(ivPlayerAll)


            //}



            //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
            //ivteams.setImageResource(id)
            //tvnombrecategoria.text = "Texto predeterminado"
            // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}

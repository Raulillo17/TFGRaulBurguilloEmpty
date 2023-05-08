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
        this.dataList = jugadores
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar

            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvNombrePlayer = itemView.findViewById(R.id.tvNombrePlayer) as TextView
            val tvPosicionarellenar = itemView.findViewById(R.id.tvPosicionarellenar) as TextView
            val tvEdadaRellenar = itemView.findViewById(R.id.tvEdadaRellenar) as TextView
            val tvLastName = itemView.findViewById(R.id.tvLastName) as TextView
            val ivPlayer = itemView.findViewById(R.id.ivPlayer) as ImageView


            if (dataItem.team == null ){
                View.GONE
            } else if (dataItem.headShotURL == null) {
                val id =
                    context.resources.getIdentifier("default_img", "drawable", context.packageName)
                ivPlayer.setImageResource(id)
            }
            else if (dataItem.headShotURL != null){
                tvNombrePlayer.text = dataItem.firstName
                tvPosicionarellenar.text = dataItem.position.toString()
                tvEdadaRellenar.text = dataItem.age
                tvLastName.text = dataItem.lastName
                Picasso.get().load("${dataItem.headShotURL}").into(ivPlayer)
            }



            //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
            //ivteams.setImageResource(id)
            //tvnombrecategoria.text = "Texto predeterminado"
            // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}

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

class adapterPlayerPerTeam(
    val context: Context,
    val layout: Int
) : RecyclerView.Adapter<adapterPlayerPerTeam.ViewHolder>() {

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

    internal fun setJugadoresPorEquipo(jugadores: List<Player>) {
        this.dataList = jugadores
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar

            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvNombreJugadorPerTeam = itemView.findViewById(R.id.tvNombreJugadorPerTeam) as TextView
            val tvPosicionarellenarPerTeam = itemView.findViewById(R.id.tvPosicionarellenarPerTeam) as TextView
            val tvEdadaRellenarPerTeam = itemView.findViewById(R.id.tvEdadaRellenarPerTeam) as TextView
            val tvLastNamePerTeam = itemView.findViewById(R.id.tvLastNamePerTeam) as TextView
            val ivPlayerPerTeam = itemView.findViewById(R.id.ivPlayerPerTeam) as ImageView


            if (dataItem.team == null ){
                View.GONE
            } else if (dataItem.headShotURL == null) {
                val id =
                    context.resources.getIdentifier("default_img", "drawable", context.packageName)
                ivPlayerPerTeam.setImageResource(id)
            }
            else if (dataItem.headShotURL != null){
                tvNombreJugadorPerTeam.text = dataItem.firstName
                tvPosicionarellenarPerTeam.text = dataItem.position.toString()
                tvEdadaRellenarPerTeam.text = dataItem.age
                tvLastNamePerTeam.text = dataItem.lastName
                Picasso.get().load("${dataItem.headShotURL}").into(ivPlayerPerTeam)
            }



            //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
            //ivteams.setImageResource(id)
            //tvnombrecategoria.text = "Texto predeterminado"
            // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}
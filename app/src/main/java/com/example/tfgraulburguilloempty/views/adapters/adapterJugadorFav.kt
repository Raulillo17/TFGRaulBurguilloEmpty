package com.example.tfgraulburguilloempty.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.model.Jugador
import com.example.tfgraulburguilloempty.views.model.Player
import com.squareup.picasso.Picasso

class adapterJugadorFav(
    val context: Context,
    val layout: Int
) : RecyclerView.Adapter<adapterJugadorFav.ViewHolder>() {

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

    internal fun setJugadoresFav(jugadores: List<Player>) {
        this.dataList = jugadores
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        @SuppressLint("ResourceAsColor")
        fun bind(dataItem: Player){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar

            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem

            val tvNombreJugadorFavFireBase = itemView.findViewById(R.id.tvNombreJugadorFavFireBase) as TextView
            val tvEquipoNombreJugadorFav = itemView.findViewById(R.id.tvEquipoNombreJugadorFav) as TextView
            val tvPPGFav = itemView.findViewById(R.id.tvPPGFav) as TextView
            val tvAPGFav = itemView.findViewById(R.id.tvAPGFav) as TextView
            val tvRPGFav = itemView.findViewById(R.id.tvRPGFav) as TextView
            /*val tvNombreJugadorFav = itemView.findViewById(R.id.tvNombrePlayerFav) as TextView
            val tvPosicionarellenarFav = itemView.findViewById(R.id.tvPosicionarellenarFav) as TextView
            val tvEdadaRellenarFav = itemView.findViewById(R.id.tvEdadaRellenarAll) as TextView
            val tvnombreTeamFav = itemView.findViewById(R.id.tvnombreTeamFav) as TextView
            val tvLastNameFav = itemView.findViewById(R.id.tvLastNameFav) as TextView*/

            val ivJugadorFireBaseFav = itemView.findViewById(R.id.ivJugadorFireBaseFav) as ImageView


            if (dataItem.headShotURL == null) {
                val id =
                    context.resources.getIdentifier("default_img", "drawable", context.packageName)
                ivJugadorFireBaseFav.setImageResource(id)
            }
            else if (dataItem.headShotURL != null){


                /*tvNombreJugadorFav.text = dataItem.firstName
                tvPosicionarellenarFav.text = dataItem.position.toString()
                tvEdadaRellenarFav.text = dataItem.age
                tvnombreTeamFav.text = dataItem.team
                tvLastNameFav.text = dataItem.lastName*/

                tvNombreJugadorFavFireBase.text = dataItem.lastName
                tvEquipoNombreJugadorFav.text = dataItem.team
                tvPPGFav.text = dataItem.careerPoints.toString()
                tvAPGFav.text = dataItem.carrerAssists.toString()
                tvRPGFav.text = dataItem.careerRebounds.toString()

                Picasso.get().load("${dataItem.headShotURL}").into(ivJugadorFireBaseFav)
            }




            //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
            //ivteams.setImageResource(id)
            //tvnombrecategoria.text = "Texto predeterminado"
            // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}

package com.example.tfgraulburguilloempty.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
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
        @SuppressLint("ResourceAsColor")
        fun bind(dataItem: Player){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar

            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvNombreJugador = itemView.findViewById(R.id.tvNombrePlayer2) as TextView
            val tvPosicionarellenar = itemView.findViewById(R.id.tvPosicionarellenar) as TextView
            val tvEdadaRellenar = itemView.findViewById(R.id.tvEdadaRellenar) as TextView
            val tvnombreteam = itemView.findViewById(R.id.tvnombreTeam) as TextView
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
                tvNombreJugador.text = dataItem.firstName
                tvPosicionarellenar.text = dataItem.position.toString()
                tvEdadaRellenar.text = dataItem.age
                tvnombreteam.text = dataItem.team
                tvLastName.text = dataItem.lastName


                Picasso.get().load("${dataItem.headShotURL}").into(ivPlayer)
            }
            when(dataItem.team){
                "Orlando Magic" -> (itemView as CardView).setCardBackgroundColor(R.color.Orlando)
                "Atlanta Hawks" -> (itemView as CardView).setCardBackgroundColor(R.color.Atlanta)
                "Boston Celtics" -> (itemView as CardView).setCardBackgroundColor(R.color.Boston)
                "Milwaukee Bucks" -> (itemView as CardView).setCardBackgroundColor(R.color.Bucks)
                "Chicago Bulls" -> (itemView as CardView).setCardBackgroundColor(R.color.Bulls)
                "Cleveland Cavaliers" -> (itemView as CardView).setCardBackgroundColor(R.color.Cleveland)
                "LA Clippers" -> (itemView as CardView).setCardBackgroundColor(R.color.Clippers)
                "New York Knicks" -> (itemView as CardView).setCardBackgroundColor(R.color.Nicks)
                "Dallas Mavericks" -> (itemView as CardView).setCardBackgroundColor(R.color.Dallas)
                "Denver Nuggets" -> (itemView as CardView).setCardBackgroundColor(R.color.Denver)
                "Detroit Pistons" -> (itemView as CardView).setCardBackgroundColor(R.color.Detroit)
                "Indiana Pacers" -> (itemView as CardView).setCardBackgroundColor(R.color.Pacers)
                "Philadelphia 76ers" -> (itemView as CardView).setCardBackgroundColor(R.color.Phipadelphia)
                "Memphis Grizzlies" -> (itemView as CardView).setCardBackgroundColor(R.color.Memphis)
                "Miami Heat" -> (itemView as CardView).setCardBackgroundColor(R.color.Miami)
                "Minnesota Timberwolves" -> (itemView as CardView).setCardBackgroundColor(R.color.Minesota)
                "Toronto Raptors" -> (itemView as CardView).setCardBackgroundColor(R.color.Raptors)
                "Houston Rockets" -> (itemView as CardView).setCardBackgroundColor(R.color.Rockets)
                "Golden State Warriors" -> (itemView as CardView).setCardBackgroundColor(R.color.Warriors)
                "Washington Wizards" -> (itemView as CardView).setCardBackgroundColor(R.color.Wizards)
                "Charlotte Hornets" -> (itemView as CardView).setCardBackgroundColor(R.color.Hornets)
                "Utah Jazz" -> (itemView as CardView).setCardBackgroundColor(R.color.Jazz)
                "Brooklyn Nets" -> (itemView as CardView).setCardBackgroundColor(R.color.Nets)
                "Oklahoma City Thunder" -> (itemView as CardView).setCardBackgroundColor(R.color.Oklahoma)
                "New Orleans Pelicans" -> (itemView as CardView).setCardBackgroundColor(R.color.Orleans)
                "Portland Trail Blazers" -> (itemView as CardView).setCardBackgroundColor(R.color.Portland)
                "Los Angeles Lakers" -> (itemView as CardView).setCardBackgroundColor(R.color.Lakers)
                "Sacramento Kings" -> (itemView as CardView).setCardBackgroundColor(R.color.Sacramento)
                "Phoenix Suns" -> (itemView as CardView).setCardBackgroundColor(R.color.Suns)
                "San Antonio Spurs" -> (itemView as CardView).setCardBackgroundColor(R.color.Spurs)
            }



            //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
            //ivteams.setImageResource(id)
            //tvnombrecategoria.text = "Texto predeterminado"
            // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}

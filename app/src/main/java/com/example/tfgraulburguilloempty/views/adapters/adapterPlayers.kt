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
            val tvNombrePlayerAll = itemView.findViewById(R.id.tvNombrePlayerAll) as TextView
            val tvPosicionarellenarAll = itemView.findViewById(R.id.tvPosicionarellenarAll) as TextView
            val tvEdadaRellenarAll = itemView.findViewById(R.id.tvEdadaRellenarAll) as TextView
            val tvnombreTeamAll = itemView.findViewById(R.id.tvnombreTeamAll) as TextView
            val tvLastNameAll = itemView.findViewById(R.id.tvLastNameAll) as TextView

            val ivPlayerAll = itemView.findViewById(R.id.ivPlayerAll) as ImageView



            if (dataItem.headShotURL == null) {
                val id =
                    context.resources.getIdentifier("default_img", "drawable", context.packageName)
                ivPlayerAll.setImageResource(id)

            }
            else if (dataItem.headShotURL != null){
                tvNombrePlayerAll.text = dataItem.firstName
                tvPosicionarellenarAll.text = dataItem.position.toString()
                tvEdadaRellenarAll.text = dataItem.age
                tvnombreTeamAll.text = dataItem.team
                tvLastNameAll.text = dataItem.lastName


                Picasso.get().load("${dataItem.headShotURL}").into(ivPlayerAll)
               /* when(dataItem.team){
                    "Orlando Magic" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Orlando)
                    "Atlanta Hawks" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Atlanta)
                    "Boston Celtics" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Boston)
                    "Milwaukee Bucks" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Bucks)
                    "Chicago Bulls" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Bulls)
                    "Cleveland Cavaliers" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Cleveland)
                    "LA Clippers" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Clippers)
                    "New York Knicks" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Nicks)
                    "Dallas Mavericks" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Dallas)
                    "Denver Nuggets" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Denver)
                    "Detroit Pistons" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Detroit)
                    "Indiana Pacers" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Pacers)
                    "Philadelphia 76ers" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Phipadelphia)
                    "Memphis Grizzlies" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Memphis)
                    "Miami Heat" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Miami)
                    "Minnesota Timberwolves" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Minesota)
                    "Toronto Raptors" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Raptors)
                    "Houston Rockets" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Rockets)
                    "Golden State Warriors" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Warriors)
                    "Washington Wizards" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Wizards)
                    "Charlotte Hornets" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Hornets)
                    "Utah Jazz" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Jazz)
                    "Brooklyn Nets" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Nets)
                    "Oklahoma City Thunder" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Oklahoma)
                    "New Orleans Pelicans" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Orleans)
                    "Portland Trail Blazers" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Portland)
                    "Los Angeles Lakers" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Lakers)
                    "Sacramento Kings" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Sacramento)
                    "Phoenix Suns" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Suns)
                    "San Antonio Spurs" -> (itemView as ConstraintLayout).setBackgroundColor(R.color.Spurs)
                }*/

            }



            //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
            //ivteams.setImageResource(id)
            //tvnombrecategoria.text = "Texto predeterminado"
            // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}

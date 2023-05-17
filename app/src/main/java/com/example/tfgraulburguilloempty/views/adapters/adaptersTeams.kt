package com.example.tfgraulburguilloempty.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.model.Team
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class adapterTeams(
    val context: Context,
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
        @SuppressLint("ResourceAsColor")
        fun bind(dataItem: Team){
            //val dataItemList: List<Respuesta> = listOf(dataItem)// obtén la lista de objetos DataItem
            //val dataItemToCompare: Respuesta = dataItem// obtén el objeto DataItem a comparar

            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            /*val ivteams = itemView.findViewById(R.id.ivteams) as ImageView
            val tvnombrecategoria = itemView.findViewById(R.id.tvnombreteam) as TextView
            val tvRecord = itemView.findViewById(R.id.tvRecord) as TextView
            val tvConferencia = itemView.findViewById(R.id.tvConferencia) as TextView*/


            val ivEquipo = itemView.findViewById(R.id.ivEquipo) as ImageView
            val tvEquipo = itemView.findViewById(R.id.tvEquipo) as TextView



            Picasso.get().load("${dataItem.teamLogoURL}").into(ivEquipo)
            tvEquipo.text = dataItem.name
           /* Picasso.get().load("${dataItem.teamLogoURL}").into(ivteams)
            tvnombrecategoria.text = dataItem.name
            tvRecord.text = dataItem.record
            tvConferencia.text = dataItem.conference.toString()*/
/*            when(dataItem.name){
                "Orlando Magic" -> (itemView as LinearLayout).setBackgroundColor(R.color.Orlando)
                "Atlanta Hawks" -> (itemView as LinearLayout).setBackgroundColor(R.color.Atlanta)
                "Boston Celtics" -> (itemView as LinearLayout).setBackgroundColor(R.color.Boston)
                "Milwaukee Bucks" -> (itemView as LinearLayout).setBackgroundColor(R.color.Bucks)
                "Chicago Bulls" -> (itemView as LinearLayout).setBackgroundColor(R.color.Bulls)
                "Cleveland Cavaliers" -> (itemView as LinearLayout).setBackgroundColor(R.color.Cleveland)
                "LA Clippers" -> (itemView as LinearLayout).setBackgroundColor(R.color.Clippers)
                "New York Knicks" -> (itemView as LinearLayout).setBackgroundColor(R.color.Nicks)
                "Dallas Mavericks" -> (itemView as LinearLayout).setBackgroundColor(R.color.Dallas)
                "Denver Nuggets" -> (itemView as LinearLayout).setBackgroundColor(R.color.Denver)
                "Detroit Pistons" -> (itemView as LinearLayout).setBackgroundColor(R.color.Detroit)
                "Indiana Pacers" -> (itemView as LinearLayout).setBackgroundColor(R.color.Pacers)
                "Philadelphia 76ers" -> (itemView as LinearLayout).setBackgroundColor(R.color.Phipadelphia)
                "Memphis Grizzlies" -> (itemView as LinearLayout).setBackgroundColor(R.color.Memphis)
                "Miami Heat" -> (itemView as LinearLayout).setBackgroundColor(R.color.Miami)
                "Minnesota Timberwolves" -> (itemView as LinearLayout).setBackgroundColor(R.color.Minesota)
                "Toronto Raptors" -> (itemView as LinearLayout).setBackgroundColor(R.color.Raptors)
                "Houston Rockets" -> (itemView as LinearLayout).setBackgroundColor(R.color.Rockets)
                "Golden State Warriors" -> (itemView as LinearLayout).setBackgroundColor(R.color.Warriors)
                "Washington Wizards" -> (itemView as LinearLayout).setBackgroundColor(R.color.Wizards)
                "Charlotte Hornets" -> (itemView as LinearLayout).setBackgroundColor(R.color.Hornets)
                "Utah Jazz" -> (itemView as LinearLayout).setBackgroundColor(R.color.Jazz)
                "Brooklyn Nets" -> (itemView as LinearLayout).setBackgroundColor(R.color.Nets)
                "Oklahoma City Thunder" -> (itemView as LinearLayout).setBackgroundColor(R.color.Oklahoma)
                "New Orleans Pelicans" -> (itemView as LinearLayout).setBackgroundColor(R.color.Orleans)
                "Portland Trail Blazers" -> (itemView as LinearLayout).setBackgroundColor(R.color.Portland)
                "Los Angeles Lakers" -> (itemView as LinearLayout).setBackgroundColor(R.color.Lakers)
                "Sacramento Kings" -> (itemView as LinearLayout).setBackgroundColor(R.color.Sacramento)
                "Phoenix Suns" -> (itemView as LinearLayout).setBackgroundColor(R.color.Suns)
                "San Antonio Spurs" -> (itemView as LinearLayout).setBackgroundColor(R.color.Spurs)

            }*/




                //val id = context.resources.getIdentifier("default_img" , "drawable", context.packageName)
                //ivteams.setImageResource(id)
                //tvnombrecategoria.text = "Texto predeterminado"
                // Si el valor es nulo, ocultamos el CardView

            itemView.tag = dataItem

        }

    }}



package com.example.paddydtb.Fragments

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.paddydtb.R
import kotlinx.android.synthetic.main.fragment_more_dtails.view.*
import kotlinx.android.synthetic.main.header.*


class MoreDtails : Fragment() {

    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_more_dtails, container, false)

        name = arguments!!.getString("NameDetectedImage")


        if (name.equals("Rice Bug")) {
            view.txt_more_detail_heading.setText("Rice Bug")
            view.imageView_more_details.setImageResource(R.drawable.rb)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText (
                    Html.fromHtml(
                        "<p><b><u>Rice Bug</u></p><p>How to manage</p></b></strong><ol><li><p>Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.</p> </li> <li> <p>Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.</p>  </li>  <li>  <p>Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor-intensive.</p></li> <li><p>Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.</p> </li></ol><strong><p>For chemical control</p></strong><p>Before using a pesticide contact a crop protection specialist for suggestions, guidance, and warnings specific to your situation.</p><ol> <li> <p>Begin scouting the field at pre-flowering and continue daily until the hard dough stage. Count rice bugs in the early morning or late afternoon from 20 hills while walking diagonally across a transplanted field. Adults often fly out of the way before you reach the rice plant, so counts may only reveal immature forms. Direct control may be required if there are more than 10 rice bugs/20 hills.</p> </li>  <li> <p>The choice of insecticide depends on many factors such as the application equipment available, the cost of the insecticide, experience of the applicator, or presence of fish. The benefits of using an insecticide must be weighed against the risks to health and the environment.</p></li></ol>",
                        Html.FROM_HTML_MODE_COMPACT
                    )
                )
            } else {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<p><b><u>Rice Bug</u></p><p>How to manage</p></b></strong><ol><li><p>Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.</p> </li> <li> <p>Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.</p>  </li>  <li>  <p>Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor-intensive.</p></li> <li><p>Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.</p> </li></ol><strong><p>For chemical control</p></strong><p>Before using a pesticide contact a crop protection specialist for suggestions, guidance, and warnings specific to your situation.</p><ol> <li> <p>Begin scouting the field at pre-flowering and continue daily until the hard dough stage. Count rice bugs in the early morning or late afternoon from 20 hills while walking diagonally across a transplanted field. Adults often fly out of the way before you reach the rice plant, so counts may only reveal immature forms. Direct control may be required if there are more than 10 rice bugs/20 hills.</p> </li>  <li> <p>The choice of insecticide depends on many factors such as the application equipment available, the cost of the insecticide, experience of the applicator, or presence of fish. The benefits of using an insecticide must be weighed against the risks to health and the environment.</p></li></ol>"
                    )
                )
            }
        } else if (name.equals("BPH")) {
            view.txt_more_detail_heading.setText("Brown Plant Hopper")
            view.imageView_more_details.setImageResource(R.drawable.bph)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<p><b><u>Brown Plant Hopper</u></p><p>How to manage</p></b></strong><ol><li><p>Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.</p> </li> <li> <p>Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.</p>  </li>  <li>  <p>Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor-intensive.</p></li> <li><p>Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.</p> </li></ol><strong><p>For chemical control</p></strong><p>Before using a pesticide contact a crop protection specialist for suggestions, guidance, and warnings specific to your situation.</p><ol> <li> <p>Begin scouting the field at pre-flowering and continue daily until the hard dough stage. Count rice bugs in the early morning or late afternoon from 20 hills while walking diagonally across a transplanted field. Adults often fly out of the way before you reach the rice plant, so counts may only reveal immature forms. Direct control may be required if there are more than 10 rice bugs/20 hills.</p> </li>  <li> <p>The choice of insecticide depends on many factors such as the application equipment available, the cost of the insecticide, experience of the applicator, or presence of fish. The benefits of using an insecticide must be weighed against the risks to health and the environment.</p></li></ol>",
                        Html.FROM_HTML_MODE_COMPACT
                    )
                )
            } else {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<p><b><u>Brown Plant Hopper</u></p><p>How to manage</p></b></strong><ol><li><p>Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.</p> </li> <li> <p>Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.</p>  </li>  <li>  <p>Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor-intensive.</p></li> <li><p>Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.</p> </li></ol><strong><p>For chemical control</p></strong><p>Before using a pesticide contact a crop protection specialist for suggestions, guidance, and warnings specific to your situation.</p><ol> <li> <p>Begin scouting the field at pre-flowering and continue daily until the hard dough stage. Count rice bugs in the early morning or late afternoon from 20 hills while walking diagonally across a transplanted field. Adults often fly out of the way before you reach the rice plant, so counts may only reveal immature forms. Direct control may be required if there are more than 10 rice bugs/20 hills.</p> </li>  <li> <p>The choice of insecticide depends on many factors such as the application equipment available, the cost of the insecticide, experience of the applicator, or presence of fish. The benefits of using an insecticide must be weighed against the risks to health and the environment.</p></li></ol>"
                    )
                )
            }
        } else if (name.equals("Bacterial_Leaf_Streak")) {
            view.txt_more_detail_heading.setText("Bacterial Leaf Streak")
            view.imageView_more_details.setImageResource(R.drawable.bls)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<b><u> Bacterial Leaf Streak: </u></b><br><br/>To revent and effectively manage bacterial leaf streak: <br>  1.Plant resistant varieties.<br>    2.Treat seeds with hot water. <br>3.Keep fields clean—remove weed hosts and plow under rice stubble, straw, rice ratoons, and volunteer seedlings, which may be infected by the bacteria.<br>4.Use balanced amounts of plant nutrients, especially nitrogen.<br>5.Ensure good drainage of fields (in conventionally flooded crops) and nurseries.<br>6.Drain the field during a severe flood.<br>7.Dry the field during the fallow period to kill the bacteria in the soil and in plant residues.<br>8.In cases of severe infection, when yield may be affected, a copper-based fungicide applied at heading can be effective in controlling the disease"))
            } else {
                view.txt_more_details.setText(Html.fromHtml("<b><u> Bacterial Leaf Streak: </u></b><br><br/>To revent and effectively manage bacterial leaf streak: <br>  1.Plant resistant varieties.<br>    2.Treat seeds with hot water. <br>3.Keep fields clean—remove weed hosts and plow under rice stubble, straw, rice ratoons, and volunteer seedlings, which may be infected by the bacteria.<br>4.Use balanced amounts of plant nutrients, especially nitrogen.<br>5.Ensure good drainage of fields (in conventionally flooded crops) and nurseries.<br>6.Drain the field during a severe flood.<br>7.Dry the field during the fallow period to kill the bacteria in the soil and in plant residues.<br>8.In cases of severe infection, when yield may be affected, a copper-based fungicide applied at heading can be effective in controlling the disease"))

            }
        } else if (name.equals("Black Bug")) {
            view.txt_more_detail_heading.setText("Black Bug")
            view.imageView_more_details.setImageResource(R.drawable.bb)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<B> <u>Black Bug </B> <br> To prevent black bug infestation:</U><br> <br/> 1. Use resistant varieties.<br> 2. Maintain a clean field by removing the weeds and drying the rice field after plowing. <br>3. Plant rice varieties of the same maturity date to break the insect’s cycle. <br>4. Use of mercury bulbs as light traps for egg-laying adults, light trapping of insects should start 5 days before and after the full moon. <br> 5. Encourage biological control agents, such as small wasps (parasitize the eggs), ground beetles, spiders, crickets, and red ants (attack the eggs, nymphs, and adults), coccinellid beetles, ducks, toads (feed on eggs and nymphs), fungi species (attacks nymphs and adults).<br/><br><u>To control black bug infestation: <br/></u><br>1. During the early infestation, raise the water level in the field for 2−3 days to force the insects to move upwards. <br>2. Flood the fields. This can cause higher egg mortality.<br>3.After harvest, plow fields to remove remaining insects."
                    )
                )

            } else {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<B> <u>Black Bug </B> <br> To prevent black bug infestation:</U><br> <br/> 1. Use resistant varieties.<br> 2. Maintain a clean field by removing the weeds and drying the rice field after plowing. <br>3. Plant rice varieties of the same maturity date to break the insect’s cycle. <br>4. Use of mercury bulbs as light traps for egg-laying adults, light trapping of insects should start 5 days before and after the full moon. <br> 5. Encourage biological control agents, such as small wasps (parasitize the eggs), ground beetles, spiders, crickets, and red ants (attack the eggs, nymphs, and adults), coccinellid beetles, ducks, toads (feed on eggs and nymphs), fungi species (attacks nymphs and adults).<br/><br><u>To control black bug infestation: <br/></u><br>1. During the early infestation, raise the water level in the field for 2−3 days to force the insects to move upwards. <br>2. Flood the fields. This can cause higher egg mortality.<br>3.After harvest, plow fields to remove remaining insects."
                    )
                )
            }
        } else if (name.equals("Brown_Spot")) {
            view.txt_more_detail_heading.setText("Brown Spot")
            view.imageView_more_details.setImageResource(R.drawable.brownspot)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<b><u>  Brown Spot </u></b><br><br/> Improving soil fertility is the first step in managing a brown spot. To do this:<br> 1.monitor soil nutrients regularly<br> 2.apply required fertilizers<br> 3.for soils that are low in silicon, apply calcium silicate slag before planting,<br> Fertilizers, however, can be costly and may take many cropping seasons before becoming effective. More economical management options include:<br>1.Use resistant varieties.<br>Contact your local agriculture office for up-to-date lists of varieties available.<br>2.Use fungicides (e.g., iprodione, propiconazole, azoxystrobin, trifloxystrobin, and carbendazim) as seed treatments.<br> 3.Treat seeds with hot water (53−54°C) for 10−12 minutes before planting, to control primary infection at the seedling stage. To increase the effectiveness of treatment, pre-soak seeds in cold water for eight hours.<br>"))
            } else {
                view.txt_more_details.setText(Html.fromHtml("<b><u>  Brown Spot </u></b><br><br/> Improving soil fertility is the first step in managing a brown spot. To do this:<br> 1.monitor soil nutrients regularly<br> 2.apply required fertilizers<br> 3.for soils that are low in silicon, apply calcium silicate slag before planting,<br> Fertilizers, however, can be costly and may take many cropping seasons before becoming effective. More economical management options include:<br>1.Use resistant varieties.<br>Contact your local agriculture office for up-to-date lists of varieties available.<br>2.Use fungicides (e.g., iprodione, propiconazole, azoxystrobin, trifloxystrobin, and carbendazim) as seed treatments.<br> 3.Treat seeds with hot water (53−54°C) for 10−12 minutes before planting, to control primary infection at the seedling stage. To increase the effectiveness of treatment, pre-soak seeds in cold water for eight hours.<br>"))

            }
        } else if (name.equals("Goda Wella")) {
            view.txt_more_detail_heading.setText("Spodoptera mauritia")
            view.imageView_more_details.setImageResource(R.drawable.saaa)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<b><u>Spodoptera mauritia</u></b><br><br/>1. Crop rotation in endemic pockets helps.<br> 2. Deep ploughing the field in summer exposes the larvae and pupae for predation by birds. <br>3. Remove excess nurseries and weeds from the field and bunds <br>4. Flooding the nurseries and small fields brings out the larvae to the surface, which get predated by the birds. Ducks if let into field, will feed on the caterpillars. A herd of ducks can easily destroy these caterpillars if let in to the fields. <br>5. Use of bamboo perches facilitates predation by birds.<br> 6. In case of severe infestation, small plots can be isolated and the movement of the caterpillars can be prevented by digging a trench around the infested field wherever possible."))

            } else {
                view.txt_more_details.setText(Html.fromHtml("<b><u>Spodoptera mauritia</u></b><br><br/>1. Crop rotation in endemic pockets helps.<br> 2. Deep ploughing the field in summer exposes the larvae and pupae for predation by birds. <br>3. Remove excess nurseries and weeds from the field and bunds <br>4. Flooding the nurseries and small fields brings out the larvae to the surface, which get predated by the birds. Ducks if let into field, will feed on the caterpillars. A herd of ducks can easily destroy these caterpillars if let in to the fields. <br>5. Use of bamboo perches facilitates predation by birds.<br> 6. In case of severe infestation, small plots can be isolated and the movement of the caterpillars can be prevented by digging a trench around the infested field wherever possible."))
            }
        } else if (name.equals("Leaf_Blast")) {
            view.txt_more_detail_heading.setText("Leaf Blast")
            view.imageView_more_details.setImageResource(R.drawable.leafblast)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<b><u> Leaf Blast </u></b><br><br/> The primary control option for the blast is to plant resistant varieties. Contact your local agriculture office for up-to-date lists of varieties available.<br> Other crop management measures can also be done, such as:<br><br/> 1.Adjust the planting time. Sow seeds early, when possible, after the onset of the rainy season.<br> 2.Split nitrogen fertilizer application in two or more treatments. Excessive use of fertilizer can increase blast intensity.<br> 3.Flood the field as often as possible.<br> Silicon fertilizers (e.g., calcium silicate) can be applied to soils that are silicon deficient to reduce blast. However, because of its high cost, silicon should be applied efficiently. Cheap sources of silicon, such as straws of rice genotypes with high silicon content, can be an alternative. Care should be taken to ensure that the straw is free from the blast as the fungus can survive on rice straw and the use of infected straw as a silicon source can spread the disease further. "
                    )
                )
            } else {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<b><u> Leaf Blast </u></b><br><br/> The primary control option for the blast is to plant resistant varieties. Contact your local agriculture office for up-to-date lists of varieties available.<br> Other crop management measures can also be done, such as:<br><br/> 1.Adjust the planting time. Sow seeds early, when possible, after the onset of the rainy season.<br> 2.Split nitrogen fertilizer application in two or more treatments. Excessive use of fertilizer can increase blast intensity.<br> 3.Flood the field as often as possible.<br> Silicon fertilizers (e.g., calcium silicate) can be applied to soils that are silicon deficient to reduce blast. However, because of its high cost, silicon should be applied efficiently. Cheap sources of silicon, such as straws of rice genotypes with high silicon content, can be an alternative. Care should be taken to ensure that the straw is free from the blast as the fungus can survive on rice straw and the use of infected straw as a silicon source can spread the disease further. "
                    )
                )
            }
        } else if (name.equals("Leaf Hopper")) {
            view.txt_more_detail_heading.setText("Leaf Hopper")
            view.imageView_more_details.setImageResource(R.drawable.leafhopper)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<p><b><u>Leaf Hopper</u></p><p>How to manage</p></b></strong><ol><li><p>Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.</p> </li> <li> <p>Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.</p>  </li>  <li>  <p>Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor-intensive.</p></li> <li><p>Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.</p> </li></ol><strong><p>For chemical control</p></strong><p>Before using a pesticide contact a crop protection specialist for suggestions, guidance, and warnings specific to your situation.</p><ol> <li> <p>Begin scouting the field at pre-flowering and continue daily until the hard dough stage. Count rice bugs in the early morning or late afternoon from 20 hills while walking diagonally across a transplanted field. Adults often fly out of the way before you reach the rice plant, so counts may only reveal immature forms. Direct control may be required if there are more than 10 rice bugs/20 hills.</p> </li>  <li> <p>The choice of insecticide depends on many factors such as the application equipment available, the cost of the insecticide, experience of the applicator, or presence of fish. The benefits of using an insecticide must be weighed against the risks to health and the environment.</p></li></ol>",
                        Html.FROM_HTML_MODE_COMPACT
                    )
                )
            } else {
                view.txt_more_details.setText(
                    Html.fromHtml(
                        "<p><b><u>Leaf Hopper</u></p><p>How to manage</p></b></strong><ol><li><p>Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.</p> </li> <li> <p>Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.</p>  </li>  <li>  <p>Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor-intensive.</p></li> <li><p>Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.</p> </li></ol><strong><p>For chemical control</p></strong><p>Before using a pesticide contact a crop protection specialist for suggestions, guidance, and warnings specific to your situation.</p><ol> <li> <p>Begin scouting the field at pre-flowering and continue daily until the hard dough stage. Count rice bugs in the early morning or late afternoon from 20 hills while walking diagonally across a transplanted field. Adults often fly out of the way before you reach the rice plant, so counts may only reveal immature forms. Direct control may be required if there are more than 10 rice bugs/20 hills.</p> </li>  <li> <p>The choice of insecticide depends on many factors such as the application equipment available, the cost of the insecticide, experience of the applicator, or presence of fish. The benefits of using an insecticide must be weighed against the risks to health and the environment.</p></li></ol>"
                    )
                )
            }
        } else if (name.equals("Girapala")) {
            view.txt_more_detail_heading.setText("Girapala")
            view.imageView_more_details.setImageResource(R.drawable.girapala)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<b><u> Girapala </u></b><br><br/><b>1.Cultural control:</b><br>Girapala is very difficult to control manually as the stolons are cut into small pieces that easily regenerate. The weed should be removed from the field and then desiccated for more than 2 weeks. Most of the smallholder farmers in Asia and Africa use hand hoeing, hand pulling and use of animal-drawn cultivators <br>   <b>2.Chemical control:</b><br>    Control using herbicides is variable depending on the herbicide, accuracy of leaf coverage and environmental conditions.  many standard herbicides have relatively low activity on species of Commelina: these include 2,4-D, propanil, butachlor, trifluralin, and pendimethalin. Herbicides with good activity on C. diffusa in rice include bentazone, molinate, oxyfluorfen, and biphenol. Propanil alone gives poor control but combinations with the first three and others such as 2,4-D, thiobencarb, oxadiazon, and pendimethalin have given good results <br>"))
            } else {
                view.txt_more_details.setText(Html.fromHtml("<b><u> Girapala </u></b><br><br/><b>1.Cultural control:</b><br>Girapala is very difficult to control manually as the stolons are cut into small pieces that easily regenerate. The weed should be removed from the field and then desiccated for more than 2 weeks. Most of the smallholder farmers in Asia and Africa use hand hoeing, hand pulling and use of animal-drawn cultivators <br>   <b>2.Chemical control:</b><br>    Control using herbicides is variable depending on the herbicide, accuracy of leaf coverage and environmental conditions.  many standard herbicides have relatively low activity on species of Commelina: these include 2,4-D, propanil, butachlor, trifluralin, and pendimethalin. Herbicides with good activity on C. diffusa in rice include bentazone, molinate, oxyfluorfen, and biphenol. Propanil alone gives poor control but combinations with the first three and others such as 2,4-D, thiobencarb, oxadiazon, and pendimethalin have given good results <br>"))
            }
        } else if (name.equals("JapanJabara")) {
            view.txt_more_detail_heading.setText("Japan Jabara")
            view.imageView_more_details.setImageResource(R.drawable.japan)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<b><u>Japan Jabara</b></u><br><br/>  When harvesting or otherwise removing JapanJabara, do not discard it in a natural waterway, instead put it into a compost pile. The herbicide that recommends using for JapanJabara control is Glyphosate 5.4. This herbicide should be sprayed onto the plants. "))

            } else {
                view.txt_more_details.setText(Html.fromHtml("<b><u>Japan Jabara</b></u><br><br/>  When harvesting or otherwise removing JapanJabara, do not discard it in a natural waterway, instead put it into a compost pile. The herbicide that recommends using for JapanJabara control is Glyphosate 5.4. This herbicide should be sprayed onto the plants. "))
            }
        } else if (name.equals("Bajiri")) {
            view.txt_more_detail_heading.setText("Bajiri")
            view.imageView_more_details.setImageResource(R.drawable.bagiri)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<b><u> Bajiri </u></b><br><br/><b>1.Cultural control:</b><br>   flooding; hand weeding or use of a hoe during early growth stages.<br>   <b>2.Chemical control:</b><br>   preemergence application of oxadiazon or pendimethalin or postemergence application of cyhalofop, butachlor, and fenoxaprop can be effective.<br>"))
            } else {
                view.txt_more_details.setText(Html.fromHtml("<b><u> Bajiri </u></b><br><br/><b>1.Cultural control:</b><br>   flooding; hand weeding or use of a hoe during early growth stages.<br>   <b>2.Chemical control:</b><br>   preemergence application of oxadiazon or pendimethalin or postemergence application of cyhalofop, butachlor, and fenoxaprop can be effective.<br>"))
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.txt_more_details.setText(Html.fromHtml("<p><b><u>Something went Wrong</u></p>"))
            } else {
                view.txt_more_details.setText(Html.fromHtml("<p><b><u>Something went Wrong</u></p>"))
            }
        }
        return view
    }
}



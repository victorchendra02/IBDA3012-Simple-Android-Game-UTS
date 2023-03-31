package id.ac.ibda.pads.proyekuts

import androidx.lifecycle.ViewModel

class myViewModel : ViewModel() {
    var player_name = "";
    var quiz_score = 0;

    var soalHangman = ""

    fun dosomth(): String {
        return player_name
    }
}
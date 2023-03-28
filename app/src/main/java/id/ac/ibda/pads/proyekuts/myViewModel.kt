package id.ac.ibda.pads.proyekuts

import androidx.lifecycle.ViewModel

class myViewModel : ViewModel() {
    var player_name = "";

    fun dosomth(): String {
        return player_name
    }
}
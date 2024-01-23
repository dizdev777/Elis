package com.elisym.bigtise.elysiumrise

import android.text.BoringLayout
import com.elisym.bigtise.elysiumrise.topic.handful.Grade

class Punitive(val stable:Boolean): Grade {
    override fun pipe(): Boolean {
       return stable
    }

    override fun restraint(): String {
        return stable.toString()
    }
}
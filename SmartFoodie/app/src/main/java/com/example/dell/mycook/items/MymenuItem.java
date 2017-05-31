package com.example.dell.mycook.items;

import android.view.View;

/**
 * Created by dell on 3/5/2017.
 */

public class MymenuItem {
    String items;
    String icon;
    View.OnClickListener onclick;

    public MymenuItem(String items, String icon, View.OnClickListener onclick) {
        this.items = items;
        this.icon = icon;
        this.onclick = onclick;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public View.OnClickListener getOnclick() {
        return onclick;
    }

    public void setOnclick(View.OnClickListener onclick) {
        this.onclick = onclick;
    }
}

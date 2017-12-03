package educs399.nau.myapplication;

import android.graphics.drawable.Drawable;

import java.util.UUID;

/**
 * Created by Chandler on 12/1/2017.
 */

public class Icon {
    private UUID id;
    private String name;
    private Drawable iconPic;

    public Icon(String name, Drawable iconPic) {
        this.name = name;
        this.iconPic = iconPic;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIconPic() {
        return iconPic;
    }

    public void setIconPic(Drawable iconPic) {
        this.iconPic = iconPic;
    }
}

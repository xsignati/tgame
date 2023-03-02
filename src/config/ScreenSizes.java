package config;

import java.awt.*;

public class ScreenSizes {
    public static final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static final int WIDTH_SIZE = (int)(gd.getDisplayMode().getWidth() * 0.5);
    public static final int HEIGHT_SIZE = (int)(gd.getDisplayMode().getHeight() * 0.5);
    public static final int BASIC_UNIT_SIZE = (int)(gd.getDisplayMode().getWidth() * 0.005);
}

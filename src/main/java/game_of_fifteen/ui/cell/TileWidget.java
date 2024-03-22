package game_of_fifteen.ui.cell;

import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.ui.utils.GameWidgetsUtils;
import game_of_fifteen.ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileWidget extends CellItemWidget {

    private final Tile tile;

    private final Color color;

    public TileWidget(Tile tile, Color color) {
        super();
        this.tile = tile;
        this.color = color;
        setFocusable(true);
    }

    @Override
    protected BufferedImage getImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getImageFile());
            image = ImageUtils.resizeImage(image, 60, 96);
            // Вычисляем координаты для размещения текста по центру изображения
            image = addTextToImage(image, tileNumber(), Color.BLACK, 18, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    private BufferedImage addTextToImage(BufferedImage image, String text, Color textColor, int x, int y) {
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(textColor);
        g2d.setFont(new Font("Arial", Font.PLAIN, 26));
        g2d.drawString(text, x, y);
        g2d.dispose();
        return image;
    }
    @Override
    public CellWidget.Layer getLayer() {
        return CellWidget.Layer.BOTTOM;
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(60, 120);
    }

    public Color getColor() {
        return color;
    }

    private String tileNumber() {
        return tile.getNumber() + "";
    }

    private Color tileNumberColor() {
        return GameWidgetsUtils.numberMovesTextColor(tile.getNumber());
    }

    private File getImageFile() {
        return new File(ImageUtils.IMAGE_PATH + "tile.png");
    }

}

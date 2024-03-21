package goat_cabbage.ui.cell;

import goat_cabbage.model.field.cell_objects.Tile;
import goat_cabbage.ui.utils.GameWidgetsUtils;
import goat_cabbage.ui.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class TileWidget extends CellItemWidget {
    /**
     *
     */
    private final Tile tile;

    /**
     *
     */
    private final Color color;

    /**
     * @param tile
     * @param color
     */
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
            // image = tileImageWithChargeText(image);
            // Вычисляем координаты для размещения текста по центру изображения
            image = addTextToImage(image, tileNumber(), Color.BLACK, 18, 50); // Измените текст, цвет и позицию по вашему желанию
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    private BufferedImage addTextToImage(BufferedImage image, String text, Color textColor, int x, int y) {
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(textColor);
        g2d.setFont(new Font("Arial", Font.PLAIN, 26)); // Измените шрифт и размер по вашему желанию
        g2d.drawString(text, x, y);
        g2d.dispose();
        return image;
    }
    @Override
    public CellWidget.Layer getLayer() {
        return CellWidget.Layer.BOTTOM;
    }

    /**
     * @param state
     */
    public void setActive(boolean state) {
        setFocusable(state);
        requestFocus();
        repaint();
    }

    @Override
    protected Dimension getDimension() {
        return new Dimension(60, 120);
    }

    /**
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param tileImage
     * @return
     */
    private BufferedImage tileImageWithChargeText(BufferedImage tileImage) {
        BufferedImage img = new BufferedImage(tileImage.getWidth(), 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.drawImage(tileImage, 0, 0, null);

        if (cellItemState == State.DEFAULT) {
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.setColor(tileNumberColor());
            g.drawString(tileNumber(), 5, 112);
        }

        return img;
    }

    private String tileNumber() {
        return tile.getNumber() + "";
    }
    /**
     * @return
     */
    private Color tileNumberColor() {
        return GameWidgetsUtils.numberMovesTextColor(tile.getNumber());
    }

    /**
     * @return
     */
    private File getImageFile() {
        return new File(ImageUtils.IMAGE_PATH + "tile.png");
    }


//    private class KeyController implements KeyListener {
//        @Override
//        public void keyTyped(KeyEvent arg0) {
//            // No implements yet.
//        }
//
//        @Override
//        public void keyPressed(KeyEvent ke) {
//            int keyCode = ke.getKeyCode();
//
//            moveAction(keyCode);
//
//            repaint();
//        }
//
//        @Override
//        public void keyReleased(KeyEvent arg0) {
//            // No implements yet&
//        }
//
//        private void moveAction(int keyCode) {
//            Direction direction = directionByKeyCode(keyCode);
//            System.out.println("keyCode = " + keyCode + ", goat go to " + direction);
//            if (direction != null) {
//                tile.move(direction);
//            }
//        }
//
//        private Direction directionByKeyCode(int keyCode) {
//            Direction direction = null;
//            switch (keyCode) {
//                case KeyEvent.VK_W:
//                    direction = Direction.NORTH;
//                    break;
//                case KeyEvent.VK_S:
//                    direction = Direction.SOUTH;
//                    break;
//                case KeyEvent.VK_A:
//                    direction = Direction.WEST;
//                    break;
//                case KeyEvent.VK_D:
//                    direction = Direction.EAST;
//                    break;
//            }
//            return direction;
//        }
//
//
//    }
}

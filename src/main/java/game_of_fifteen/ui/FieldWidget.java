package game_of_fifteen.ui;

import game_of_fifteen.model.Direction;
import game_of_fifteen.model.Orientation;
import game_of_fifteen.model.Point;
import game_of_fifteen.model.event.TileActionEvent;
import game_of_fifteen.model.event.TileActionListener;
import game_of_fifteen.model.field.Cell;
import game_of_fifteen.model.field.Field;
import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.ui.cell.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class FieldWidget extends JPanel {
    private final Field field;
    private final WidgetFactory widgetFactory;

    public FieldWidget(@NotNull Field field, @NotNull WidgetFactory widgetFactory) {
        this.field = field;
        this.widgetFactory = widgetFactory;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fillField();
        addMouseListener(new MouseController());
        subscribeOnRobots();
    }

    private void fillField() {
        if (field.getHeight() > 0) {
            JPanel startRowWalls = createRowWalls(0, Direction.NORTH);
            add(startRowWalls);
        }

        for (int i = 0; i < field.getHeight(); ++i) {
            JPanel row = createRow(i);
            add(row);
            JPanel rowWalls = createRowWalls(i, Direction.SOUTH);
            add(rowWalls);
        }
    }

    private JPanel createRow(int rowIndex) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < field.getWidth(); ++i) {
            Point point = new Point(i, rowIndex);

            Cell cell = field.getCell(point);
            CellWidget cellWidget = widgetFactory.create(cell);

            if (i == 0) {
                BetweenCellsWidget westCellWidget = new BetweenCellsWidget(Orientation.VERTICAL);
                row.add(westCellWidget);
            }

            row.add(cellWidget);

            BetweenCellsWidget eastCellWidget = new BetweenCellsWidget(Orientation.VERTICAL);

            row.add(eastCellWidget);
        }
        return row;
    }

    private JPanel createRowWalls(int rowIndex, Direction direction) {
        if (direction == Direction.EAST || direction == Direction.WEST) throw new IllegalArgumentException();
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for (int i = 0; i < field.getWidth(); ++i) {
            Point point = new Point(i, rowIndex);
            Cell cell = field.getCell(point);

            BetweenCellsWidget southCellWidget = new BetweenCellsWidget(Orientation.HORIZONTAL);

            row.add(southCellWidget);
        }
        return row;
    }

    private void subscribeOnRobots() {
        List<Tile> tiles = field.getTilesOnField();
        for(var i : tiles) {
            i.addTileActionListener(new TileController());
        }
    }

    private class TileController implements TileActionListener {
        @Override
        public void tileIsMoved(@NotNull TileActionEvent event) {
            CellItemWidget tileWidget = widgetFactory.getWidget(event.getTile());
            CellWidget from = widgetFactory.getWidget(event.getFromCell());
            CellWidget to = widgetFactory.getWidget(event.getToCell());
            from.removeItem(tileWidget);
            to.addItem(tileWidget);
            tileWidget.repaint();
        }



        @Override
        public void tileActivityChanged(@NotNull TileActionEvent event) {
            Tile tile = event.getTile();
            TileWidget tileWidget = (TileWidget) widgetFactory.getWidget(tile);
            tileWidget.setActive(tile.isActive());
        }
    }
    public class MouseController implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int mouseX = e.getX(); // координата X в пикселях
            int mouseY = e.getY(); // координата Y в пикселях
            System.out.println("Press Cell x: = " + mouseX + ", y: " + mouseY);

            // Определите координаты клетки, в которую было произведено нажатие мыши
            int matrixWidth = 4; // количество столбцов
            int matrixHeight = 4; // количество строк

            int matrixPixelWidth = 600; // ширина матрицы в пикселях
            int matrixPixelHeight = 600; // высота матрицы в пикселях

            // Вычисляем масштаб по осям X и Y
            double scaleX = (double) matrixPixelWidth / matrixWidth;
            double scaleY = (double) matrixPixelHeight / matrixHeight;

            // Находим индексы ячейки в матрице
            int matrixX = (int) (mouseX / scaleX);
            int matrixY = (int) (mouseY / scaleY);

                Point mousePoint = new Point(matrixX, matrixY);
                Cell mouseCell = field.getCell(mousePoint);
                Tile mouseTile = (Tile) mouseCell.getMobileCellObject();

                // Перемещение костяшки в указанную клетку
                mouseTile.move();

            System.out.println("наличие тайла в ячейке после "+ mouseCell.getMobileCellObject());
        }

        @Override
        public void mousePressed(MouseEvent arg0) {

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


//        private void moveTile(int cellX, int cellY) {
//            // Получите костяшку, которая находится в указанной клетке
//            Tile tile = getTileAt(cellX, cellY);
//
//            // Проверяем, если клетка пуста и костяшка может быть перемещена
//            if (tile != null && tile.canMove()) {
//                // Перемещаем костяшку
//                tile.move();
//            }
//        }
//
//        private Tile getTileAt(int cellX, int cellY) {
//            // Получите объект клетки по ее координатам cellX и cellY
//            // Верните костяшку, которая находится в этой клетке
//            return
//        }

}

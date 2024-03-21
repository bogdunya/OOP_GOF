package game_of_fifteen.ui;

import game_of_fifteen.model.field.Cell;
import game_of_fifteen.model.field.CellObject;
import game_of_fifteen.model.field.MobileCellObject;
import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.ui.cell.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WidgetFactory {
    private final Map<Cell, CellWidget> cells = new HashMap<>();
    private final Map<CellObject, CellItemWidget> cellObjects = new HashMap<>();
    private final ArrayList<Color> usedColors = new ArrayList<>();

    public CellWidget create(@NotNull Cell cell) {
        if (cells.containsKey(cell)) return cells.get(cell);

        CellWidget item = new CellWidget();

        MobileCellObject mobileCellObject = cell.getMobileCellObject();
        if (mobileCellObject != null) {
            CellItemWidget tileWidget = create(mobileCellObject);
            item.addItem(tileWidget);
        }

        cells.put(cell, item);
        return item;
    }

    public CellWidget getWidget(@NotNull Cell cell) {
        return cells.get(cell);
    }

    public void remove(@NotNull Cell cell) {
        cells.remove(cell);
    }

    public CellItemWidget create(@NotNull CellObject cellObject) {
        if (cellObjects.containsKey(cellObject)) return cellObjects.get(cellObject);

        CellItemWidget createdWidget = null;
        if (cellObject instanceof Tile) {
            Color color = Color.BLUE;
            usedColors.add(color);
            createdWidget = new TileWidget((Tile) cellObject, color);
        } else {
            throw new IllegalArgumentException();
        }

        cellObjects.put(cellObject, createdWidget);
        return createdWidget;
    }

    public CellItemWidget getWidget(@NotNull CellObject cellObject) {
        return cellObjects.get(cellObject);
    }

    public void remove(@NotNull CellObject cellObject) {
        cellObjects.remove(cellObject);
    }
}
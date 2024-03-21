package goat_cabbage.ui;

import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.CellObject;
import goat_cabbage.model.field.MobileCellObject;
import goat_cabbage.model.field.cell_objects.Cabbage;
import goat_cabbage.model.field.cell_objects.Tile;
import goat_cabbage.model.field.cell_objects.Wall;
import goat_cabbage.ui.cell.*;
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

        if (cell instanceof Cell && mobileCellObject == null) {
            if (cell.getCellObject() instanceof Wall) {
                CellItemWidget wallWidget = create(cell.getCellObject());
                item.addItem(wallWidget);
            } else if (cell.getCellObject() instanceof Cabbage) {
                CellItemWidget cabbageWidget = create(cell.getCellObject());
                item.addItem(cabbageWidget);
            }
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
        } else if (cellObject instanceof Wall) {
            createdWidget = new WallWidget((Wall) cellObject);
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
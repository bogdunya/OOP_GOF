package goat_cabbage.model.event;

import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.cell_objects.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.EventObject;

/**
 * Объект события класса коза.
 */
public class TileActionEvent extends EventObject {
    /**
     * Костяшка.
     */
    private Tile tile;

    /**
     * Ячейка откуда переместилась костяшка.
     */
    private Cell fromCell;

    /**
     * Ячейка куда переместилась костяшка
     */
    private Cell toCell;

    /**
     * Конструктор объекта события класса костяшка.
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TileActionEvent(Object source) {
        super(source);
    }

    /**
     * Установить ячейку откуда переместилась костяшка.
     * @param fromCell ячейка откуда переместилась костяшка.
     */
    public void setFromCell(Cell fromCell) {
        this.fromCell = fromCell;
    }

    /**
     * Получить ячейку откуда переместилась костяшка.
     * @return ячейка откуда переместилась костяшка.
     */
    public Cell getFromCell() {
        return fromCell;
    }

    /**
     * Установить ячейку куда переместилась костяшка.
     * @param toCell ячейка куда переместилась костяшка.
     */
    public void setToCell(Cell toCell) {
        this.toCell = toCell;
    }

    /**
     * Получить ячейку куда переместилась костяшка.
     * @return ячейка куда переместилась костяшка.
     */
    public Cell getToCell() {
        return this.toCell;
    }

    /**
     * Установить костяшку.
     * @param tile костяшка.
     */
    public void setTile(@NotNull Tile tile) {
        this.tile = tile;
    }

    /**
     * Получить коcтяшку.
     * @return костяшку.
     */
    public Tile getTile() {
        return tile;
    }
}

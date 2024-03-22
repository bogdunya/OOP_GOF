package game_of_fifteen.model.field.cell_objects;

import game_of_fifteen.model.Direction;
import game_of_fifteen.model.event.TileActionEvent;
import game_of_fifteen.model.event.TileActionListener;
import game_of_fifteen.model.field.Cell;
import game_of_fifteen.model.field.MobileCellObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Класс Костяшка
 */
public class Tile extends MobileCellObject {

    /**
     * Конструктор
     * @param number порядковый номер костяшки
     */
    public Tile(@NotNull int number) {
        this.number = number;
    }

    @Override
    public boolean canLocaleAtPosition(@NotNull Cell newPosition) {
        return newPosition.getMobileCellObject() == null || newPosition.getMobileCellObject() instanceof Tile;
    }

    @Override
    public void move() {
        Cell oldPosition = position;
        //System.out.println("наличие тайла в ячейке до "+ position.getMobileCellObject());
        Cell newPosition = canMove();
        //System.out.println("canMove: "+newPosition);
        if (newPosition != null) {
            position.takeObject(position.getMobileCellObject());
            //System.out.println("№ пятнашки "+this.number);
            newPosition.addObject(this);
            fireTileIsMoved(oldPosition, newPosition);
            //System.out.println("наличие тайла в новой ячейке после "+ newPosition.getMobileCellObject());
        }
    }

    @Override
    protected Cell canMove() {
        Cell result = null;
        if (position.getNeighborCell(Direction.SOUTH)!=null && position.getNeighborCell(Direction.SOUTH).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.SOUTH);
             result = neighborCell;
        } else if (position.getNeighborCell(Direction.NORTH)!=null && position.getNeighborCell(Direction.NORTH).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.NORTH);
             result = neighborCell;
        } else if (position.getNeighborCell(Direction.WEST)!=null && position.getNeighborCell(Direction.WEST).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.WEST);
             result = neighborCell;
        } else if (position.getNeighborCell(Direction.EAST)!=null && position.getNeighborCell(Direction.EAST).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.EAST);
             result = neighborCell;
        }
        return result;
    }


    /**
     * Список слушателей события костяшек
     */
    private final ArrayList<TileActionListener> tileListListener = new ArrayList<>();

    /**
     * Добавить нового слушателя за событиями костяшки
     *
     * @param listener слушатель.
     */
    public void addTileActionListener(TileActionListener listener) {
        tileListListener.add(listener);
    }

    /**
     * Удалить слушателя за событиями костяшки.
     *
     * @param listener слушатель.
     */
    public void removeTileActionListener(TileActionListener listener) {
        tileListListener.remove(listener);
    }
    private void fireMouseClick(){
    }
    /**
     * Оповестить слушателей, что костяшка переместилась
     *
     * @param oldPosition ячейка откуда переместилась костяшка
     * @param newPosition ячейка куда переместилась костяшка
     */
    private void fireTileIsMoved(@NotNull Cell oldPosition, @NotNull Cell newPosition) {
        for (TileActionListener listener : tileListListener) {
            TileActionEvent event = new TileActionEvent(listener);
            event.setTile(this);
            event.setFromCell(oldPosition);
            event.setToCell(newPosition);
            listener.tileIsMoved(event);
        }
    }
}

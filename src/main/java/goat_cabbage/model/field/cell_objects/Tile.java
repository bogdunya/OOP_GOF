package goat_cabbage.model.field.cell_objects;

import goat_cabbage.model.Direction;
import goat_cabbage.model.event.TileActionEvent;
import goat_cabbage.model.event.TileActionListener;
import goat_cabbage.model.field.Cell;
import goat_cabbage.model.field.MobileCellObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Коза.
 */
public class Tile extends MobileCellObject {

    /**
     * Состояние активности козы.
     */
    private boolean isActive;
    private int number;
    /**
     * Конструктор.
     * @param number порядковый номер костяшки
     */
    public Tile(@NotNull int number) {
        this.number = number;
    }

    // Тут проверить все 4 стороны света вокруг костяшки, шоб она сама проверяла куда ей идти
    @Override
    public boolean canLocaleAtPosition(@NotNull Cell newPosition) {
        return newPosition.getMobileCellObject() == null || newPosition.getMobileCellObject() instanceof Tile;
    }

    @Override
    public void move() {

        Cell oldPosition = position;
        //System.out.println("наличие тайла в ячейке до "+ position.getMobileCellObject());
        Cell newPosition = canMove();
        System.out.println("canMove: "+newPosition);
        if (newPosition != null) {
            fireTileIsMoved(oldPosition, newPosition);

            position.takeObject(position.getMobileCellObject());
            //System.out.println("№ пятнашки "+this.number);

            newPosition.addObject(this);


            //System.out.println("наличие тайла в новой ячейке после "+ newPosition.getMobileCellObject());
        }
    }

    @Override
    protected Cell canMove() {
        Cell result = null;
        if (position.getNeighborCell(Direction.SOUTH)!=null && position.getNeighborCell(Direction.SOUTH).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.SOUTH);
            return result = neighborCell;
        } else if (position.getNeighborCell(Direction.NORTH)!=null && position.getNeighborCell(Direction.NORTH).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.NORTH);
            return result = neighborCell;
        } else if (position.getNeighborCell(Direction.WEST)!=null && position.getNeighborCell(Direction.WEST).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.WEST);
            return result = neighborCell;
        } else if (position.getNeighborCell(Direction.EAST)!=null && position.getNeighborCell(Direction.EAST).getMobileCellObject()==null) {
            Cell neighborCell = position.getNeighborCell(Direction.EAST);
            return result = neighborCell;
        } else return result;

    }



    /**
     * Получить состояние активности костяшки.
     *
     * @return состояние активности костяшки.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Список слушателей, подписанныз на события костяшки.
     */
    private final ArrayList<TileActionListener> tileListListener = new ArrayList<>();

    /**
     * Добавить нового слушателя за событиями костяшки.
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

    /**
     * Оповестить слушателей, что костяшка переместилась.
     *
     * @param oldPosition ячейка откуда переместилась козы.
     * @param newPosition ячейка куда переместилась козы.
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


    /**
     * Оповестить слушателей, что состояние активности козы изменилось.
     */ //Вообще это надо удалить, у костяшки нет активности!
    private void fireTileChangeActive() {
        for (TileActionListener listener : tileListListener) {
            TileActionEvent event = new TileActionEvent(listener);
            event.setTile(this);
            listener.tileActivityChanged(event);
        }
    }

    public int getNumber() {return this.number;}
}

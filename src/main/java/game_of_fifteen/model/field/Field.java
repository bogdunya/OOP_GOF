package game_of_fifteen.model.field;

import game_of_fifteen.model.Direction;
import game_of_fifteen.model.Point;
import game_of_fifteen.model.field.cell_objects.Cabbage;
import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.model.field_formation.SmallFieldFormation;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Поле.
 */
public class Field {
    /**
     * Ячейки поля.
     */
    private final Map<Point, Cell> cells = new HashMap<>();

    /**
     * Ширина поля.
     */
    private final int width = 4;

    /**
     * Высота поля.
     */
    private final int height = 4;

//    private final Gabbage gabbage;

    /**
     * Конструктор
     *
     * @param width        ширина. Должна быть > 0.
     * @param height       высота. Должна быть > 0.
     * @param pointGabbage координата ячейки с капустой.
     * @throws IllegalArgumentException если ширина, высота или координата ячейки переданы некорректные.
     */
    public Field(@NotNull Point pointGabbage, int typeOfField) {
        if (width <= 0) throw new IllegalArgumentException("Field width must be more than 0");
        if (height <= 0) throw new IllegalArgumentException("Field height must be more than 0");
        if (pointGabbage.getX() >= width || pointGabbage.getY() >= height) {
            throw new IllegalArgumentException("exit point coordinates must be in range from 0 to weight or height");
        }
        buildField(typeOfField);
//        this.gabbage = (Gabbage) getCell(pointGabbage);
//        ((Gabbage) getCell(pointGabbage)).addExitCellActionListener(new ExitCellObserver());
    }

    /**
     * Построить игровое поле.
     */
    private void buildField(int typeOfField) {

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                Point p = new Point(x, y);
                Cell cell = new Cell();
                if (x > 0) cell.setNeighborCells(getCell(p.to(Direction.WEST, 1)), Direction.WEST);
                if (y > 0) cell.setNeighborCells(getCell(p.to(Direction.NORTH, 1)), Direction.NORTH);
                switch (typeOfField) {
                    case 1:
                        //сюда дописать дописывать соседей для типа игры 1
                        break;
                    case 2:
                        //сюда дописать дописывать соседей для типа игры 2
                        break;
                }
                cells.put(p, cell);

            }
        }
    }

    /**
     * Получить ширину поля.
     *
     * @return ширина поля.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Получить высоту поля.
     *
     * @return высота поля.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Получить ячейку по заданной координате.
     *
     * @param point координата.
     * @return ячейка.
     */
    public Cell getCell(@NotNull Point point) {
        return cells.get(point);
    }

    /**
     * Получить козу на поле.
     *
     * @return коза на поле.
     */

    public List<Tile> getTilesOnField() {
        List<Tile> result = new ArrayList<>();
        for (var i : cells.entrySet()) {
            List<CellObject> objectList = i.getValue().objectList;
            for (var j : objectList) {
                if (j instanceof Tile) {
                    result.add((Tile) j);
                }
            }
        }
        //System.out.println("Список по порядку: "+result);
        return result;
    }

    public Cabbage getCabbageOnField() {
        for (var i : cells.entrySet()) {
            if (i.getValue().getCellObject() instanceof Cabbage) {
                CellObject cellObject = i.getValue().getCellObject();
                if (cellObject instanceof Cabbage) return (Cabbage) cellObject;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return width == field.width &&
                height == field.height &&
                Objects.equals(cells, field.cells);
//                Objects.equals(gabbage, field.gabbage);
    }

    @Override
    public String toString() {
        return "Field{" +
                "cells=" + cells +
                ", width=" + width +
                ", height=" + height +
//                ", gabbage=" + gabbage +
                "}";
    }
}

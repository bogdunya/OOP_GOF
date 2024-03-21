package game_of_fifteen.model.field_formation;

import game_of_fifteen.model.Point;
import game_of_fifteen.model.field.Field;
import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.model.field.cell_objects.Wall;
import org.jetbrains.annotations.NotNull;

/**
 * Лабиринт маленького поля.
 */
public class SmallFieldFormation extends FieldFormation {
    /**
     * Высота поля.
     */
    private static final int FIELD_HEIGHT = 4;

    /**
     * Ширина поля.
     */
    private static final int FIELD_WIDTH = 4;

    @Override
    protected int fieldHeight() {
        return FIELD_HEIGHT;
    }

    @Override
    protected int fieldWidth() {
        return FIELD_WIDTH;
    }

    protected void addTile(@NotNull Field field) {
        int tileValue = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileValue <= 15) {
                    Tile tile = new Tile(tileValue);
                    field.getCell(new Point(i,j)).addObject(tile);
                    tileValue++;
                }
            }
        }
    }

    @Override
    protected void addWalls(@NotNull Field field) {
        field.getCell(new Point(1, 0)).addObject(new Wall());
//        field.getCell(new Point(1, 1)).addObject(new Wall());
        field.getCell(new Point(1, 2)).addObject(new Wall());
        field.getCell(new Point(3, 1)).addObject(new Wall());
        field.getCell(new Point(3, 2)).addObject(new Wall());
        field.getCell(new Point(3, 3)).addObject(new Wall());
    }

}

package game_of_fifteen.model.field_formation;

import game_of_fifteen.model.Point;
import game_of_fifteen.model.field.Field;
import game_of_fifteen.model.field.cell_objects.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Лабиринт маленького поля.
 */
public class ClassicFieldFormationRandom extends FieldFormation {
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
        List<Integer> tileValues = new ArrayList<>();
        // Заполняем список значениями от 1 до 15
        for (int i = 1; i <= 15; i++) {
            tileValues.add(i);
        }
        // Перемешиваем список
        Collections.shuffle(tileValues);

        // Размещаем костяшки на поле
        int index = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                Point point = new Point(i, j);
                if (index < tileValues.size()) {
                    int tileValue = tileValues.get(index);
                    Tile tile = new Tile(tileValue);
                    field.getCell(point).addObject(tile);
                    index++;
                }
            }
        }
    }
}

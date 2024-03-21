package game_of_fifteen.model.field.cell_objects;

import game_of_fifteen.model.field.Cell;
import game_of_fifteen.model.field.CellObject;
import org.jetbrains.annotations.NotNull;

public class Wall extends CellObject {
    @Override
    public boolean canLocaleAtPosition(@NotNull Cell cell) {
        return position == null && cell.getCellObject() == null;
    }
}

package game_of_fifteen.ui.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import game_of_fifteen.model.Orientation;
import game_of_fifteen.ui.cell.CellWidget.Layer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Виджет контейнера для виджетов между ячейками {@link BetweenCellsWidget}.
 */
public class BetweenCellsWidget extends JPanel { // !!! До конца предназначение виджета не понял
    // DONE: Контейнер для расположения элементов между ячейками.

    /**
     * Ориентация.
     */
    private final Orientation orientation;

    /**
     * Конструктор.
     * @param orientation ориентация.
     */
    public BetweenCellsWidget(@NotNull Orientation orientation) {
        super(new BorderLayout());
        this.orientation = orientation;
        setPreferredSize(getDimensionByOrientation());
        setBackground(Color.BLACK);
    }

    /**
     * Установить элемент.
     * @param blockWidget элемент.
     * @throws IllegalArgumentException если ориентация объекта не совпадает с ориентацией контейнера.
     */
    public void setItem(@NotNull BlockWidget blockWidget) { // !!! Плохое название - add обозначает множественное добавление, а здесь предполагается задание одного элемента
        // DONE: Переименовал addItem -> setItem.
        if(blockWidget.getOrientation() != orientation) throw new IllegalArgumentException();
        add(blockWidget);                                   // !!! Что будет, если ориентация BetweenCellsWidget и BlockWidget не совпадают??
        // DONE: Добавил проверку на несовпадение ориентаций.
    }

    /**
     * Получить размеры виджета по ориентации {@link BetweenCellsWidget#orientation}
     * @return размеры.
     */
    private Dimension getDimensionByOrientation() {
        return (orientation == Orientation.VERTICAL) ? new Dimension(5, 120) : new Dimension(125, 5);
    }
}

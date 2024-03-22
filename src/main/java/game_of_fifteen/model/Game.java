package game_of_fifteen.model;

import game_of_fifteen.model.event.*;
import game_of_fifteen.model.field.Field;
import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.model.field_formation.FieldFormation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Игра.
 */
public class Game {
    /**
     * Статус игры.
     */
    private GameStatus gameStatus;

    /**
     * Костяшка
     */
    private List<Tile> tile;
    /**
     * Игровое поле.
     */
    private Field gameField;

    /**
     * Конструктор.
     * @param fieldFormation лабиринт, содержащий расстановку элементов на поле.
     */
    public Game(FieldFormation fieldFormation) {
        startGame(fieldFormation);
    }

    /**
     * Старт новой игры.
     * @param fieldFormation лабиринт, содержащий расстановку элементов на поле.
     */
    public void startGame(@NotNull FieldFormation fieldFormation) {
        setStatus(GameStatus.GAME_IS_ON);

        buildField(fieldFormation);
        setTile(gameField.getTilesOnField());

        //gameField.getTilesOnField().addTileActionListener(new TileObserver()); //Возможно это важная строчка!
        List<Tile> tiles = gameField.getTilesOnField();
        TileObserver observer = new TileObserver(); // Создайте экземпляр вашего слушателя
// Добавьте слушателя к каждому объекту Tile в списке
        for (Tile tile : tiles) {
            tile.addTileActionListener(observer);
        }
    }

    /**
     * Прервать игру
     */
    public void abort() {
        setStatus(GameStatus.GAME_ABORTED);
    }

    /**
     * Получить текущий статус игры.
     * @return текующий статус игры.
     */
    public GameStatus getStatus() {
        return gameStatus;
    }

    /**
     * Установить текущий статус игры.
     * @param status текущий статус игры.
     */
    private void setStatus(GameStatus status) {
        if (gameStatus != status) {
            gameStatus = status;
            fireGameStatusIsChanged(gameStatus);
        }
    }

    /**
     * Получить Костяшка.
     * @return Костяшка.
     */
    public List<Tile> getTile() {
        return tile;
    }
    public void setTile(List<Tile> tile) {
        this.tile = tile;
    }
    /**
     * Получить игровое поле.
     * @return игровое поле.
     */
    public Field getGameField() {
        return gameField;
    }

    /**
     * Обновить состояние игры.
     */
    public void updateGameStatus() {
        GameStatus status = determineOutcomeGame();
        setStatus(status);
    }

    /**
     * Определить исход игры.
     * @return статус игры.
     */
    /**
     * Classic variation:
     * <pre>
     * 1 2 3
     * 4 5 6
     * 7 8 -
     * </pre>
     */
    private GameStatus determineOutcomeGame() {
        GameStatus result = GameStatus.GAME_IS_ON;

        int height = gameField.getHeight();
        int width = gameField.getWidth();

        int tileValue = 1;
        System.out.println("не ЗАШЁЛ");
        if(gameField.getCell(new Point(height-1,width-1)).getCellObject()==null){
            System.out.println("ЗАШЁЛ");
            boolean Win = false;
            for (int y = 0; y < gameField.getHeight(); ++y) {
                for (int x = 0; x < gameField.getWidth(); ++x) {
                    if (tileValue <= 15) {
                        Point p = new Point(x, y);
                        if (gameField.getCell(new Point(x, y)).getCellObject().getNumber() !=tileValue){
                            Win = true;
                        }
                            tileValue++;
                    }
                }
            }
            if (Win == false){
                result = GameStatus.WIN;
            }
        }

//        if (goatOnField.getNumberMoves() <= 0) {
//            result = GameStatus.GOAT_NOT_HAVE_MOVES;
//        } else if (cabbage.getPosition() == goatOnField.getPosition()) {
//            result = GameStatus.WIN;
//        }


        return result;
    }

    /**
     * Построить игровое поле.
     * @param fieldFormation лабиринт, содержащий расстановку элементов на поле.
     */
    private void buildField(@NotNull FieldFormation fieldFormation) {
        gameField = fieldFormation.buildField();
    }

    /**
     * Класс, реализующий наблюдение за событиями.
     */

    private class TileObserver implements TileActionListener {
        @Override
        public void tileIsMoved(@NotNull TileActionEvent event) {

            fireTileIsMoved(event.getTile());

        }
    }

    /**
     * Список слушателей, подписанных на события игры.
     */
    private final ArrayList<GameActionListener> gameActionListeners = new ArrayList<>();

    /**
     * Добавить нвоого слушателя за событиями игры.
     * @param listener слушатель.
     */
    public void addGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.add(listener);
    }

    /**
     * Удалить слушателя за событиями игры.
     * @param listener слушатель.
     */
    public void removeGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.remove(listener);
    }

    /**
     * Оповестить слушателей, что коза переместилась.
     */

    public void fireTileIsMoved(@NotNull Tile tile) {

        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setTile(tile);
            listener.tileIsMoved(event);
        }
        updateGameStatus();
    }

    /**
     * Оповестить сулшателей {@link Game#gameActionListeners}, что статус игры изменился.
     * @param status статус игры.
     */
    public void fireGameStatusIsChanged(@NotNull GameStatus status) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setStatus(status);
            listener.gameStatusChanged(event);
        }
    }
}

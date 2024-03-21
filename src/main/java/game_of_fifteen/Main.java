package game_of_fifteen;

import game_of_fifteen.model.Game;
import game_of_fifteen.model.GameStatus;
import game_of_fifteen.model.event.GameActionEvent;
import game_of_fifteen.model.event.GameActionListener;
import game_of_fifteen.model.field_formation.ClassicFieldFormationRandom;
import game_of_fifteen.model.field_formation.ClassicFieldFormation_1test;
import game_of_fifteen.ui.FieldWidget;
import game_of_fifteen.ui.WidgetFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GamePanel::new);
    }

    static class GamePanel extends JFrame {
        private Game game;
        private WidgetFactory widgetFactory;

        public GamePanel() throws HeadlessException {
            setVisible(true);
            startGame();
            setResizable(false);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(createGameMenu());
            setJMenuBar(menuBar);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        private JMenu createGameMenu() {
            JMenu gameMenu = new JMenu("Игра");
            JMenuItem newGameMenuItem = new JMenuItem(new NewGameAction());
            gameMenu.add(newGameMenuItem);
            return gameMenu;
        }

        private void startGame() {
            widgetFactory = new WidgetFactory();
            game = new Game(new ClassicFieldFormationRandom());

            game.addGameActionListener(new GameController());

            JPanel content = (JPanel) this.getContentPane();
            content.removeAll();

            content.add(new FieldWidget(game.getGameField(), widgetFactory));
            //System.out.println(game.getTile());
            //System.out.println(widgetFactory.getWidget(game.getTile())); // ТУТ ВОЗМОЖНО НАДО БУДЕТ НАЧАЛЬНЫЙ ФОКУС КУДА-ТО ПРИКЛЕПАТЬ
            content.getComponent(0).requestFocus();



            pack();

        }

        private class NewGameAction extends AbstractAction {
            public NewGameAction() {
                putValue(NAME, "Новая");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(GamePanel.this,
                        "Начать новую игру?", "Новая игра",JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION) startGame();
            }
        }

        private final class GameController implements GameActionListener {
            @Override
            public void tileIsMoved(@NotNull GameActionEvent event) {
                // No implements yet.
            }

            @Override
            public void gameStatusChanged(@NotNull GameActionEvent event) {
                GameStatus status = event.getStatus();
                if (status == GameStatus.WIN) {
                    // Запускаем диалоговое окно в отдельном потоке
                    SwingUtilities.invokeLater(() -> {
                        String message = "Вы собрали пятнашки!";
                        String[] options = {"OK"};
                        int result = JOptionPane.showOptionDialog(GamePanel.this, message, "Игра окончена", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        // Проверяем, нажата ли кнопка "OK"
                        if (result == 0) {
                            startGame();
                        }
                    });

                }
            }
        }
    }
}

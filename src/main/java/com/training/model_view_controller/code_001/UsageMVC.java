package main.java.com.training.model_view_controller.code_001;

import static main.java.com.training.model_view_controller.code_001.Article.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

class UsageMVC {

    static class Constants {

        static final Object[] tableHeaderMVC = tableHeader;
        static final Object[][] articleDataMVC = articleData;

    }

    static class Controller implements ActionListener {
        // Объявляем класс для редактирования строки в графическом интерфейсе
        private final JTextField searchTermTextField;
        // Реализуем TableModel для хранения объектов в ячейках
        private final DefaultTableModel model;

        Controller (JTextField searchTermTextField, DefaultTableModel model) {
            // Создаём новые объекты с помощью метода super(), обновляя данные,
            // передав их по ссылкам this.*
            super();
            this.searchTermTextField = searchTermTextField;
            this.model = model;
        }

        @Override
        // Передаём семантические события объектам в методе actionPerformed()
        // с помощью класса ActionEvent и реализации интерфейса ActionListener
        public void actionPerformed (ActionEvent e) {
            // Возвращаем текст, содержащийся в компоненте searchTermTextField
            String searchTerm = searchTermTextField.getText();
            if (searchTerm != null && !searchTerm.isEmpty()) {
                // Создаём массив с данными из классов Constants.articleDataMVC и
                // Constants.tableHeaderMVC
                Object[][] newData = new Object[Constants.articleDataMVC.length][];
                int idx = 0;
                for (Object[] o: Constants.articleDataMVC) {
                    if ("*".equals (searchTerm
                            .trim())) {
                        newData[idx ++] = o;
                    } else {
                        if (String.valueOf (o[0])
                                .startsWith (searchTerm.toUpperCase()
                                        .trim())) {
                            newData[idx ++] = o;
                        }
                    }
                }
                // Получаем новый массив newData для возможности замены значений массива
                // articleDataMVC во время функционирования графической оболочки
                model.setDataVector (newData, Constants.tableHeaderMVC);
            } else {
                JOptionPane.showMessageDialog (null,
                        "Search term is empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    static class Model extends DefaultTableModel {

        Model() {
            // Инициализация таблицы, передающей данные articleDataMVC и
            // tableHeaderMVC классу Controller и сообщаем об этом классу
            // UsageMVC.Constants
            super (Constants.articleDataMVC, Constants.tableHeaderMVC);
        }

    }

    static class View {

        View() {
            // Создание компонента для ввода текста
            JTextField searchTermTextField = new JTextField (20);
            JButton filterButton = new JButton ("search filter / *.");
            // Объявляем универсальный контейнер для получения данных из двойного буфера
            JPanel ctrlPane = new JPanel();
            JScrollPane tableScrollPane;
            // Создание табличной модели
            Model model = new Model();
            // Класс JTable для отображения и редактирования двумерных таблиц
            JTable table = new JTable();
            table.setModel (model);
            // Создание контроллера
            Controller controller = new Controller (searchTermTextField, model);
            filterButton.addActionListener (controller);
            // Добавление объектов в контейнер ctrlPane
            ctrlPane.add (searchTermTextField);
            ctrlPane.add (filterButton);
            // Создаём JScrollPane для отображения содержимого указанного компонента
            tableScrollPane = new JScrollPane (table);
            // Устанавливаем предпочтительный размер этого компонента
            tableScrollPane.setPreferredSize (new Dimension (640, 182));
            // Устанавливаем границу этого компонента и его расположение
            tableScrollPane.setBorder (BorderFactory.createTitledBorder
                    (BorderFactory.createEtchedBorder(), "Article",
                    TitledBorder.CENTER, TitledBorder.TOP));
            // Разделение компонентов графического интерфейса
            JSplitPane splitPane = new JSplitPane
                    (JSplitPane.VERTICAL_SPLIT, ctrlPane, tableScrollPane);
            // Устанавливаем положение делителя
            splitPane.setDividerLocation(35);
            // Присутствие реакции на ввод пользователя
            splitPane.setEnabled (false);
            // Добавление поддержки архитектуры компонентов JFC/Swing
            JFrame frame = new JFrame ("UsageMVC");
            // Установка операции выхода по умолчанию при закрытии приложения с
            // графическим интерфейсом
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.add (splitPane);
            // Изменяем размер окна и расположение графических элементов
            // JAVA-приложения
            frame.pack();
            // Окно помещается в центр экрана
            frame.setLocationRelativeTo (null);
            // Отменяем скрытность окна
            frame.setVisible (true);
        }

    }

    static void mainMVC() {
        // Реализация интерфейса Runnable, где отсутствует синхронизация в потоке
        // диспетчера событий AWT - Abstract Window Toolkit - инструменты работы
        // с графическим интерфейсом, где используется коллекция методов Swing
        SwingUtilities.invokeLater (UsageMVC::createAndShowGUI);
    }

    static void createAndShowGUI() {
        // Объявляем метод для создания графического интерфейса
        new View();
    }

}
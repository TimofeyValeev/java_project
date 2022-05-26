package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Welcome to JavaFX Application!");
    }


    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToScene1(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Scene1.fxml"));
        Scene scene = new Scene (fxmlLoader.load(),770,533);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public class Controller {

        // Поля, что ссылаются на объекты внутри дизайна
        @FXML
        private ToggleGroup answers;

        @FXML
        private Text question_text;

        @FXML
        private RadioButton radio_btn_1;

        @FXML
        private RadioButton radio_btn_2;

        @FXML
        private RadioButton radio_btn_3;

        @FXML
        private RadioButton radio_btn_4;

        @FXML
        private Button answerBtn;

        // Массив на основе класса Questions. Каждый объект – вопрос с набором возможных ответов
        private Questions[] questions = new Questions[] {
                new Questions("В каком из вариантов представлен корректный формат вывода информации на экран?", new String[] {"Console.Write()", "console.log()", "print()", "System.out.println()"}),
                new Questions("Какой тип данных отвечает за целые числа?", new String[] {"String", "Float", "Boolean", "Integer"}),
                new Questions("Где правильно присвоено новое значение к многомерному массиву?", new String[] {"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
                new Questions("Какой метод позволяет запустить программу на Java?", new String[] {"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
                new Questions("Каждый файл должен называется...", new String[] {"по имени первой библиотеки в нём", "по имени названия пакета", "как вам захочется", "по имени класса в нём"}),
                new Questions("Сколько параметров может принимать функция?", new String[] {"5", "10", "20", "неограниченное количество"})
        };

        // Переменные для установки текущего номера вопроса и для подсчета количества верных ответов
        private int nowQuestion = 0, correctAnswers;
        // В эту переменную будет устанавливаться корректный ответ текущего вопроса
        private String nowCorrectAnswer;

        @FXML
        public void initialize() {
            // Берем корректный ответ для текущего вопроса
            nowCorrectAnswer = questions[nowQuestion].correctAnswer();

            // Отслеживание нажатия на кнопку "Ответить"
            answerBtn.setOnAction(e -> {
                // Получаем выбранную кнопку пользователем
                RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
                // Код будет выполняться только если пользователь выбрал ответ
                if(selectedRadioButton != null) {
                    // Получаем текст ответа
                    String toogleGroupValue = selectedRadioButton.getText();

                    // Сверяем ответ с корректным
                    if(toogleGroupValue.equals(nowCorrectAnswer)) {
                        // Выводим информацию и увеличиваем количество верных ответов
                        System.out.println("Верный ответ");
                        correctAnswers++;
                    } else
                        System.out.println("Не верный ответ");

                    // Если сейчас был последний вопрос, то скрываем все поля
                    if(nowQuestion + 1 == questions.length) {
                        radio_btn_1.setVisible(false); // Скрываем все поля для выбора
                        radio_btn_2.setVisible(false);
                        radio_btn_3.setVisible(false);
                        radio_btn_4.setVisible(false);
                        answerBtn.setVisible(false); // Скрываем кнопку ответа

                        // Показываем текст в конце
                        question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + questions.length + " вопросов!");
                    } else { // Если еще есть вопросы...
                        // Увеличиваем номер текущего вопроса
                        nowQuestion++;
                        // Указываем новый текст верного ответа
                        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

                        // Меняем текст вопроса в программе
                        question_text.setText(questions[nowQuestion].getQuestion());
                        // Получаем массив ответов
                        String[] answers = questions[nowQuestion].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Сортируем в случайном порядке
                        Collections.shuffle(intList);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Снимаем выделение, что указал пользователь ранее
                        selectedRadioButton.setSelected(false);
                    }

                }
            });
        }

    }



}
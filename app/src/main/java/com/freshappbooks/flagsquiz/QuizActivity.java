package com.freshappbooks.flagsquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";

    private  ConstraintLayout layout;
    private  ArrayList<String> countries;
    private  ArrayList<String> capitals;
    private  ArrayList<Button> buttons;
    private  ArrayList<ImageView> images;

    private  ImageView imageView0, imageView1, imageView2;

    private int gameCounter;
    public static int rightAnswerCounter;
    private int numberOfQuestion;
    private int numberOfRightAnswer;
    private  TextView textViewQuestionText, textViewGameCounter;
    private  Button button1, button2, button3, button0;

    private MediaPlayer rightSound;
    private MediaPlayer wrongSound;

    private int counterLives;
    String rightAnswerText;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        layout = findViewById(R.id.constraint_layout);
        textViewQuestionText = findViewById(R.id.question_textView);
        textViewGameCounter = findViewById(R.id.textView_game_counter);

        initGoogleBanner();
        initGoogleInter();

        button1 = findViewById(R.id.button0);
        button2 = findViewById(R.id.button1);
        button3 = findViewById(R.id.button2);
        button0 = findViewById(R.id.button3);
        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);


        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        images = new ArrayList<>();
        images.add(imageView0);
        images.add(imageView1);
        images.add(imageView2);
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setVisibility(View.INVISIBLE);
        }

        rightSound = MediaPlayer.create(this, R.raw.right);
        wrongSound = MediaPlayer.create(this, R.raw.wrong);


        gameCounter = 0;
        rightAnswerCounter = 0;
        counterLives = 3;
        initArrays();
            playGame();

    }

    private void initGoogleBanner() {
        //Banner
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(MainActivity.BANNER);
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void initGoogleInter() {
        // Initialize google ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(MainActivity.INTERSTITIAL);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    void showGoogleInter() {
        if (counterLives != 1) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }
    }

    private void playGame() {
        if (counterLives > 0 ) {
            setButtonsActive();
            showLives();
            setRandomBackground();
            generateQuestion();
            fillButtons();
            resetButtonColors();
        } else {
            intentToGameOverActivity();
        }
    }

    private void intentToGameOverActivity() {
        Intent intent = new Intent(this, GameOverActivity.class);
        startActivity(intent);
    }

    private void fillButtons() {
        ArrayList<Integer> listOfReplays = new ArrayList<>();
        listOfReplays.add(numberOfQuestion);
        for (int i = 0; i < buttons.size(); i++) {
            if (i == numberOfRightAnswer) {
                buttons.get(i).setText(rightAnswerText);
                Log.d(TAG, "fillButtons rightAnswer is : " + buttons.get(i).getTag());
            } else {
                while (true) {
                    int wrong = generateWrongAnswer();
                    if (!listOfReplays.contains(wrong)) {
                        listOfReplays.add(wrong);
                        buttons.get(i).setText(capitals.get(wrong));
                        break;
                    }
                }
            }
            textViewGameCounter.setText(rightAnswerCounter + "/" + gameCounter);
        }
    }

    private void generateQuestion() {
        numberOfQuestion = (int) (Math.random() * countries.size());
        numberOfRightAnswer = (int) (Math.random() * buttons.size());
        rightAnswerText = capitals.get(numberOfQuestion);
        textViewQuestionText.setText("Выберите столицу страны:\n" + countries.get(numberOfQuestion));
    }

    private int generateWrongAnswer() {
        return (int) (Math.random() * capitals.size());
    }


    private void setRandomBackground() {
        Random rand = new Random();
        int random = rand.nextInt(6);
        switch (random) {
            case 0:
                layout.setBackgroundColor(getResources().getColor(R.color.color0));
                break;
            case 1:
                layout.setBackgroundColor(getResources().getColor(R.color.color1));
                break;
            case 2:
                layout.setBackgroundColor(getResources().getColor(R.color.color2));
                break;
            case 3:
                layout.setBackgroundColor(getResources().getColor(R.color.color3));
                break;
            case 4:
                layout.setBackgroundColor(getResources().getColor(R.color.color4));
                break;
            case 5:
                layout.setBackgroundColor(getResources().getColor(R.color.color5));
                break;
            default:
                layout.setBackgroundColor(getResources().getColor(R.color.color0));
                break;
        }
    }

    private void resetButtonColors() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackgroundColor(getResources().getColor(R.color.resetColor));
        }
    }

    private void initArrays() {
        countries = new ArrayList<>();
        countries.add("Австралия");
        countries.add("Австрия");
        countries.add("Азербайджан");
        countries.add("Албания");
//        countries.add("Алжир");
//        countries.add("Ангола");
//        countries.add("Андорра");
//        countries.add("Антигуа и Барбуда");
//        countries.add("Аргентина");
//        countries.add("Армения");
//        countries.add("Афганистан");
//        countries.add("Багамы");
//        countries.add("Бангладеш");
//        countries.add("Барбадос");
//        countries.add("Бахрейн");
//        countries.add("Беларусь");
//        countries.add("Белиз");
//        countries.add("Бельгия");
//        countries.add("Бенин");
//        countries.add("Болгария");
//        countries.add("Боливия");
//        countries.add("Босния и Герцеговина");
//        countries.add("Ботсвана");
//        countries.add("Бразилия");
//        countries.add("Бруней");
//        countries.add("Буркина Фасо");
//        countries.add("Бурунди");
//        countries.add("Бутан");
//        countries.add("Вануату");
//        countries.add("Ватикан");
//        countries.add("Великобритания");
//        countries.add("Венгрия");
//        countries.add("Венесуэла");
//        countries.add("Восточный Тимор");
//        countries.add("Вьетнам");
//        countries.add("Габон");
//        countries.add("Гаити");
//        countries.add("Гайана");
//        countries.add("Гамбия");
//        countries.add("Гана");
//        countries.add("Гватемала");
//        countries.add("Гвинея");
//        countries.add("Гвинея-Бисау");
//        countries.add("Германия");
//        countries.add("Гондурас");
//        countries.add("Гренада");
//        countries.add("Греция");
//        countries.add("Грузия");
//        countries.add("Дания");
//        countries.add("Джибути");
//        countries.add("Доминика");
//        countries.add("Доминиканская Республика");
//        countries.add("Египет");
//        countries.add("Замбия");
//        countries.add("Зимбабве");
//        countries.add("Израиль");
//        countries.add("Индия");
//        countries.add("Индонезия");
//        countries.add("Иордания");
//        countries.add("Ирак");
//        countries.add("Иран");
//        countries.add("Ирландия");
//        countries.add("Исландия");
//        countries.add("Испания");
//        countries.add("Италия");
//        countries.add("Йемен");
//        countries.add("Кабо-Верде");
//        countries.add("Казахстан");
//        countries.add("Камбоджа");
//        countries.add("Камерун");
//        countries.add("Канада");
//        countries.add("Катар");
//        countries.add("Кения");
//        countries.add("Кипр");
//        countries.add("Киргизия");
//        countries.add("Кирибати");
//        countries.add("Китай");
//        countries.add("Колумбия");
//        countries.add("Коморы");
//        countries.add("Конго, демократическая республика");
//        countries.add("Конго, республика");
//        countries.add("Коста-Рика");
//        countries.add("Кот-д’Ивуар");
//        countries.add("Куба");
//        countries.add("Кувейт");
//        countries.add("Лаос");
//        countries.add("Латвия");
//        countries.add("Лесото");
//        countries.add("Либерия");
//        countries.add("Ливан");
//        countries.add("Ливия");
//        countries.add("Литва");
//        countries.add("Лихтенштейн");
//        countries.add("Люксембург");
//        countries.add("Маврикий");
//        countries.add("Мавритания");
//        countries.add("Мадагаскар");
//        countries.add("Македония");
//        countries.add("Малави");
//        countries.add("Малайзия");
//        countries.add("Мали");
//        countries.add("Мальдивы");
//        countries.add("Мальта");
//        countries.add("Марокко");
//        countries.add("Маршалловы Острова");
//        countries.add("Мексика");
//        countries.add("Мозамбик");
//        countries.add("Молдавия");
//        countries.add("Монако");
//        countries.add("Монголия");
//        countries.add("Мьянма");
//        countries.add("Намибия");
//        countries.add("Науру");
//        countries.add("Непал");
//        countries.add("Нигер");
//        countries.add("Нигерия");
//        countries.add("Нидерланды");
//        countries.add("Никарагуа");
//        countries.add("Новая Зеландия");
//        countries.add("Норвегия");
//        countries.add("Объединенные Арабские Эмираты");
//        countries.add("Оман");
//        countries.add("Пакистан");
//        countries.add("Палау");
//        countries.add("Панама");
//        countries.add("Папуа - Новая Гвинея");
//        countries.add("Парагвай");
//        countries.add("Перу");
//        countries.add("Польша");
//        countries.add("Португалия");
//        countries.add("Россия");
//        countries.add("Руанда");
//        countries.add("Румыния");
//        countries.add("Сальвадор");
//        countries.add("Самоа");
//        countries.add("Сан-Марино");
//        countries.add("Сан-Томе и Принсипи");
//        countries.add("Саудовская Аравия");
//        countries.add("Свазиленд");
//        countries.add("Северная Корея");
//        countries.add("Сейшелы");
//        countries.add("Сенегал");
//        countries.add("Сент-Винсент и Гренадины");
//        countries.add("Сент-Китс и Невис");
//        countries.add("Сент-Люсия");
//        countries.add("Сербия");
//        countries.add("Сингапур");
//        countries.add("Сирия");
//        countries.add("Словакия");
//        countries.add("Словения");
//        countries.add("Соединенные Штаты Америки");
//        countries.add("Соломоновы Острова");
//        countries.add("Сомали");
//        countries.add("Судан");
//        countries.add("Суринам");
//        countries.add("Сьерра-Леоне");
//        countries.add("Таджикистан");
//        countries.add("Таиланд");
//        countries.add("Танзания");
//        countries.add("Того");
//        countries.add("Тонга");
//        countries.add("Тринидад и Тобаго");
//        countries.add("Тувалу");
//        countries.add("Тунис");
//        countries.add("Туркмения");
//        countries.add("Турция");
//        countries.add("Уганда");
//        countries.add("Узбекистан");
//        countries.add("Украина");
//        countries.add("Уругвай");
//        countries.add("Федеративные штаты Микронезии");
//        countries.add("Фиджи");
//        countries.add("Филиппины");
//        countries.add("Финляндия");
//        countries.add("Франция");
//        countries.add("Хорватия");
//        countries.add("Центрально-Африканская Республика");
//        countries.add("Чад");
//        countries.add("Черногория");
//        countries.add("Чехия");
//        countries.add("Чили");
//        countries.add("Швейцария");
//        countries.add("Швеция");
//        countries.add("Шри-Ланка");
//        countries.add("Эквадор");
//        countries.add("Экваториальная Гвинея");
//        countries.add("Эритрея");
//        countries.add("Эстония");
//        countries.add("Эфиопия");
//        countries.add("Южная Корея");
//        countries.add("Южно-Африканская Республика");
//        countries.add("Ямайка");
//        countries.add("Япония");
        capitals = new ArrayList<>();
        capitals.add("Канберра");
        capitals.add("Вена");
        capitals.add("Баку");
        capitals.add("Тирана");
//        capitals.add("Алжир");
//        capitals.add("Луанда");
//        capitals.add("Андорра-ла-Велья");
//        capitals.add("Сент-Джонс");
//        capitals.add("Буэнос-Айрес");
//        capitals.add("Ереван");
//        capitals.add("Кабул");
//        capitals.add("Нассау");
//        capitals.add("Дакка");
//        capitals.add("Бриджтаун");
//        capitals.add("Манама");
//        capitals.add("Минск");
//        capitals.add("Бельмопан");
//        capitals.add("Брюссель");
//        capitals.add("Порто-Ново");
//        capitals.add("София");
//        capitals.add("Сукре");
//        capitals.add("Сараево");
//        capitals.add("Габороне");
//        capitals.add("Бразилиа");
//        capitals.add("Бандар-Сери-Багаван");
//        capitals.add("Уагадугу");
//        capitals.add("Бужумбура");
//        capitals.add("Тхимпху");
//        capitals.add("Порт-Вила");
//        capitals.add("Ватикан");
//        capitals.add("Лондон");
//        capitals.add("Будапешт");
//        capitals.add("Каракас");
//        capitals.add("Дили");
//        capitals.add("Ханой");
//        capitals.add("Либревиль");
//        capitals.add("Порт-о-Пренс");
//        capitals.add("Джорджтаун");
//        capitals.add("Банжул");
//        capitals.add("Аккра");
//        capitals.add("Гватемала");
//        capitals.add("Конакри");
//        capitals.add("Бисау");
//        capitals.add("Берлин");
//        capitals.add("Тегусигальпа");
//        capitals.add("Сент-Джорджес");
//        capitals.add("Афины");
//        capitals.add("Тбилиси");
//        capitals.add("Копенгаген");
//        capitals.add("Джибути");
//        capitals.add("Розо");
//        capitals.add("Санто-Доминго");
//        capitals.add("Каир");
//        capitals.add("Лусака");
//        capitals.add("Хараре");
//        capitals.add("Иерусалим");
//        capitals.add("Нью-Дели");
//        capitals.add("Джакарта");
//        capitals.add("Амман");
//        capitals.add("Багдад");
//        capitals.add("Тегеран");
//        capitals.add("Дублин");
//        capitals.add("Рейкьявик");
//        capitals.add("Мадрид");
//        capitals.add("Рим");
//        capitals.add("Сана");
//        capitals.add("Прая");
//        capitals.add("Астана");
//        capitals.add("Пномпень");
//        capitals.add("Яунде");
//        capitals.add("Оттава");
//        capitals.add("Доха");
//        capitals.add("Найроби");
//        capitals.add("Никосия");
//        capitals.add("Бишкек");
//        capitals.add("Южная Тарава");
//        capitals.add("Пекин");
//        capitals.add("Санта-Фе-де-Богота");
//        capitals.add("Морони");
//        capitals.add("Киншаса");
//        capitals.add("Браззавиль");
//        capitals.add("Сан-Хосе");
//        capitals.add("Ямусукро");
//        capitals.add("Гавана");
//        capitals.add("Эль-Кувейт");
//        capitals.add("Вьентьян");
//        capitals.add("Рига");
//        capitals.add("Масеру");
//        capitals.add("Монровия");
//        capitals.add("Бейрут");
//        capitals.add("Триполи");
//        capitals.add("Вильнюс");
//        capitals.add("Вадуц");
//        capitals.add("Люксембург");
//        capitals.add("Порт-Луи");
//        capitals.add("Нуакшот");
//        capitals.add("Антананариву");
//        capitals.add("Скопье");
//        capitals.add("Лилонгве");
//        capitals.add("Куала-Лумпур");
//        capitals.add("Бамако");
//        capitals.add("Мале");
//        capitals.add("Валлетта");
//        capitals.add("Рабат");
//        capitals.add("Маджуро");
//        capitals.add("Мехико");
//        capitals.add("Мапуту");
//        capitals.add("Кишинев");
//        capitals.add("Монако");
//        capitals.add("Улан-Батор");
//        capitals.add("Найпьидо");
//        capitals.add("Виндхук");
//        capitals.add("официальной столицы не имеет");
//        capitals.add("Катманду");
//        capitals.add("Ниамей");
//        capitals.add("Абуджа");
//        capitals.add("Амстердам");
//        capitals.add("Манагуа");
//        capitals.add("Веллингтон");
//        capitals.add("Осло");
//        capitals.add("Абу-Даби");
//        capitals.add("Маскат");
//        capitals.add("Исламабад");
//        capitals.add("Мелекеок");
//        capitals.add("Панама");
//        capitals.add("Порт-Морсби");
//        capitals.add("Асунсьон");
//        capitals.add("Лима");
//        capitals.add("Варшава");
//        capitals.add("Лиссабон");
//        capitals.add("Москва");
//        capitals.add("Кигали");
//        capitals.add("Бухарест");
//        capitals.add("Сан-Сальвадор");
//        capitals.add("Апиа");
//        capitals.add("Сан-Марино");
//        capitals.add("Сан-Томе");
//        capitals.add("Эр-Рияд");
//        capitals.add("Мбабане");
//        capitals.add("Пхеньян");
//        capitals.add("Виктория");
//        capitals.add("Дакар");
//        capitals.add("Кингстаун");
//        capitals.add("Бастер");
//        capitals.add("Кастри");
//        capitals.add("Белград");
//        capitals.add("Сингапур");
//        capitals.add("Дамаск");
//        capitals.add("Братислава");
//        capitals.add("Любляна");
//        capitals.add("Вашингтон");
//        capitals.add("Хониара");
//        capitals.add("Могадишо");
//        capitals.add("Хартум");
//        capitals.add("Парамарибо");
//        capitals.add("Фритаун");
//        capitals.add("Душанбе");
//        capitals.add("Бангкок");
//        capitals.add("Додома");
//        capitals.add("Ломе");
//        capitals.add("Нукуалофа");
//        capitals.add("Порт-оф-Спейн");
//        capitals.add("Фунафути");
//        capitals.add("Тунис");
//        capitals.add("Ашхабад");
//        capitals.add("Анкара");
//        capitals.add("Кампала");
//        capitals.add("Ташкент");
//        capitals.add("Киев");
//        capitals.add("Монтевидео");
//        capitals.add("Паликир");
//        capitals.add("Сува");
//        capitals.add("Манила");
//        capitals.add("Хельсинки");
//        capitals.add("Париж");
//        capitals.add("Загреб");
//        capitals.add("Банги");
//        capitals.add("Нджамена");
//        capitals.add("Подгорица");
//        capitals.add("Прага");
//        capitals.add("Сантьяго");
//        capitals.add("Берн");
//        capitals.add("Стокгольм");
//        capitals.add("Коломбо");
//        capitals.add("Кито");
//        capitals.add("Малабо");
//        capitals.add("Асмэра");
//        capitals.add("Таллин");
//        capitals.add("Аддис-Абеба");
//        capitals.add("Сеул");
//        capitals.add("Претория");
//        capitals.add("Кингстон");
//        capitals.add("Токио");
    }

    public void onClickAnswer(View view) {
        Button button = (Button) view;
        if (button.getText().equals(rightAnswerText)) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            rightAnswerCounter++;
            soundPlay(rightSound);
        } else {
            button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            Toast.makeText(this, "НЕТ! Правильный ответ : " + rightAnswerText, Toast.LENGTH_LONG).show();
            soundPlay(wrongSound);
            counterLives--;
            showGoogleInter();
        }
        setButtonsInactive();
        gameCounter++;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playGame();
            }
        }, 1500);
    }

    public void soundPlay(MediaPlayer sound) {
        sound.start();
    }

    public void showLives() {
        for (int i = 0; i < 3; i++) {
            if (i < counterLives) {
                images.get(i).setVisibility(View.VISIBLE);
            } else {
                images.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    private void setButtonsInactive() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(false);
        }
    }

    private void setButtonsActive() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(true);
        }
    }
}

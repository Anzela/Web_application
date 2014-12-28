package lv.ak07178.testapp.domain;

import java.io.Serializable;

public class Post implements Serializable {
    private String text;
    private String title;
    private long id;
    private long authorId;
    private Section section;
    private long dateTime;

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public Section getSection() {
        return section;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public Post(Section section, long authorId, String title, String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.section = section;
        this.authorId = authorId;
        this.title = title;
        this.text = text;
    }

    public enum Section {
        NEWS(Type.ADMIN, "Новости", "Важная информация от администрации, обязательная к прочтению всем пользователям форума."),
        QUESTIONS(Type.ADMIN, "Вопросы и ответы", "Есть вопросы к администрации форума? Задайте вопрос здесь!"),
        SITE(Type.ADMIN, "Работа сайта", "Замечания, предложения, проблемы."),
        DISCUSSIONS(Type.USERS, "Беседы", "Темы, не связанные с другими разделами: о жизни, советы, обсуждения, вопросы."),
        JOKES(Type.USERS, "Шутки", "Посмеёмся?"),
        EVENTS(Type.USERS, "События", "Происшествия в мире - горячие новости и хроника."),
        AUTO(Type.USERS, "Авто/Мото", "Новые машины, аварии, ДТП, случаи на колесах."),
        ANIMALS(Type.USERS, "Зверье", "Раздел про животных."),
        TRAVEL(Type.USERS, "Путешестия", "Репортажи о путешествиях, отдыхе и местах которые стоит посетить."),
        CINEMA(Type.USERS, "Кино", "Все о кино: афиша, рецензии на фильмы, ваше любимое кино, кинозвезды, сплетни."),
        GAMES(Type.USERS, "Игры", "Обсуждения, прохождения, анонсы новых игр"),
        FOOD(Type.USERS, "Кулинария", "Рецепты и ваши любимые блюда."),
        SPORT(Type.USERS, "Здоровье и спорт", "Все о здоровом образе жизни."),
        ART(Type.USERS, "Творчество", "Креатив, творчество пользователей.");

        private final String title;
        private final String description;
        private final Type type;

        Section(Type type, String title, String description){
            this.type = type;
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Type getType() {
            return type;
        }

        public enum Type {
            ADMIN, USERS
        }
    }
}

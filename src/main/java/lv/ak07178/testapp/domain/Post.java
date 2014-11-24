package lv.ak07178.testapp.domain;

public class Post {
    private String text;
    private String title;
    private long id;
    private long authorId;
    private Section section;

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
        NEWS(Type.ADMIN, "Новости", "Новости, объявления и важная информация от администрации, обязательная к прочтению всем пользователям форума."),
        QUESTIONS(Type.ADMIN, "Вопросы и ответы", "Есть вопросы к администрации форума? Задайте вопрос здесь!"),
        SITE(Type.ADMIN, "Работа сайта", "Замечания, предложения, проблемы."),
        DISCUSSIONS(Type.USERS, "Беседы", "Раздел для общения на темы, не связанные с другими разделами: о жизни, советы, обсуждения, вопросы."),
        JOKES(Type.USERS, "Шутки", "Посмеёмся? Шутки, приколы, анекдоты, веселые картинки, смешные видеоролики."),
        EVENTS(Type.USERS, "События", "События и происшествия в мире. Горячие новости, хроника и комментарии."),
        AUTO(Type.USERS, "Авто/Мото", "Темы про авто/мото технику. Новые машины, аварии, ДТП, случаи на колесах,концепты, выставки, смешные случаи."),
        ANIMALS(Type.USERS, "Зверье", "Раздел про животных. Фотографии кошек, собак, насекомых, весь мир фауны. Разная живность!"),
        TRAVEL(Type.USERS, "Путешестия", "Путеводитель по странам и городам. Репортажи о путешествиях, отдыхе и местах которые стоит посетить."),
        CINEMA(Type.USERS, "Кино", "Все о кино: афиша, новости, рецензии на фильмы, ваше любимое кино, кинозвезды, сплетни."),
        GAMES(Type.USERS, "Игры", "Компьютерные игры и развлекательные программы. Обсуждения, прохождения, анонсы новых игр"),
        FOOD(Type.USERS, "Кулинария", "Все о кулинарии: рецепты, ваши любимые блюда, кухни всего мира, диета, куда сходить поесть"),
        SPORT(Type.USERS, "Здоровье и спорт", "Все о здоровом образе жизни, спортивные мероприятия, события, обсуждения и комментарии."),
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

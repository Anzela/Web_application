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
        NEWS("Новости", "Новости, объявления и важная информация от администрации, обязательная к прочтению всем пользователям форума."),
        QUESTIONS("Вопросы и ответы", "Есть вопросы к администрации форума? Задайте вопрос здесь!"),
        SITE("Работа сайта", "Замечания, предложения, проблемы."),
        DISCUSSIONS("Беседы", "Раздел для общения на темы, не связанные с другими разделами: о жизни, советы, обсуждения, вопросы."),
        JOKES("Шутки", "Посмеёмся? Шутки, приколы, анекдоты, веселые картинки, смешные видеоролики."),
        EVENTS("События", "События и происшествия в мире. Горячие новости, хроника и комментарии."),
        AUTO("Авто/Мото", "Темы про авто/мото технику. Новые машины, аварии, ДТП, случаи на колесах,концепты, выставки, смешные случаи."),
        ANIMALS("Зверье", "Раздел про животных. Фотографии кошек, собак, насекомых, весь мир фауны. Разная живность!"),
        TRAVEL("Путешестия", "Путеводитель по странам и городам. Репортажи о путешествиях, отдыхе и местах которые стоит посетить."),
        CINEMA("Кино", "Все о кино: афиша, новости, рецензии на фильмы, ваше любимое кино, кинозвезды, сплетни."),
        GAMES("Игры", "Компьютерные игры и развлекательные программы. Обсуждения, прохождения, анонсы новых игр"),
        FOOD("Кулинария", "Все о кулинарии: рецепты, ваши любимые блюда, кухни всего мира, диета, куда сходить поесть"),
        SPORT("Здоровье и спорт", "Все о здоровом образе жизни, спортивные мероприятия, события, обсуждения и комментарии."),
        ART("Творчество", "Креатив, творчество пользователей.");

        private final String title;

        private final String description;

        Section(String title, String description){
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}

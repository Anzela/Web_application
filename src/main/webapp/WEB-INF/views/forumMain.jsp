<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <div class="left-column">
            <div class="post-head">
                 <h1>Общение с администрацией</h1>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/news_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/NEWS"><h1>Новости</h1></a>
                    <p>Новости, объявления и важная информация от администрации,
                    обязательная к прочтению всем пользователям форума.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/questions_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/QUESTIONS"><h1>Вопросы и ответы</h1></a>
                    <p>Есть вопросы к администрации форума? Задайте вопрос здесь!</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/site_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/SITE"><h1>Работа сайта</h1></a>
                    <p>Замечания, предложения, проблемы.</p>
                </div>
            </div>

            <div class="post-head">
                <h1>Темы форума</h1>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/talks_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/DISCUSSIONS"><h1>Беседы</h1></a>
                    <p>Раздел для общения на темы, не связанные с другими разделами: о жизни, советы, обсуждения, вопросы.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/jokes_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/JOKES"><h1>Шутки</h1></a>
                    <p>Посмеёмся? Шутки, приколы, анекдоты, веселые картинки, смешные видеоролики.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/events_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/EVENTS"><h1>События</h1></a>
                    <p>События и происшествия в мире. Горячие новости, хроника и комментарии.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/auto_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/AUTO"><h1>Авто/Мото</h1></a>
                    <p>Темы про авто/мото технику. Новые машины, аварии, ДТП, случаи на колесах,концепты, выставки, смешные случаи.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/animals_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/ANIMALS"><h1>Зверьё</h1></a>
                    <p>Раздел про животных. Фотографии кошек, собак, насекомых, весь мир фауны. Разная живность!</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/travel_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/TRAVEL"><h1>Путешествия</h1></a>
                    <p>Путеводитель по странам и городам. Репортажи о путешествиях, отдыхе и местах которые стоит посетить.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/movie_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/CINEMA"><h1>Кино</h1></a>
                    <p>Все о кино: афиша, новости, рецензии на фильмы, ваше любимое кино, кинозвезды, сплетни.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/games_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/GAMES"><h1>Игры</h1></a>
                    <p>Компьютерные игры и развлекательные программы. Обсуждения, прохождения, анонсы новых игр</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/food_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/FOOD"><h1>Кулинария</h1></a>
                    <p>Все о кулинарии: рецепты, ваши любимые блюда, кухни всего мира, диета, куда сходить поесть</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/sport_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/SPORT"><h1>Здоровье и спорт</h1></a>
                    <p>Все о здоровом образе жизни, спортивные мероприятия, события, обсуждения и комментарии.</p>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/art_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/ART"><h1>Творчество</h1></a>
                    <p>Креатив, творчество пользователей.</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
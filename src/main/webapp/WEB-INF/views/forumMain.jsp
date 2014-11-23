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
                <c:forEach var="post" items="${posts}">
                    <div class="post-area">
                        <div class="post-content">
                            <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                            <h1>${post.section.title}</h1>
                            <p>${post.section.description}</p>
                        </div>
                    </div>
                </c:forEach>

            <div class="post-head">
                <h1>Темы форума</h1>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/DISCUSSIONS"><h1>Беседы</h1></a>
                    <a href="/test-mvn-app/forum/DISCUSSIONS"><p>Раздел для общения на темы, не связанные с другими разделами: о жизни, советы, обсуждения, вопросы.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/JOKES"><h1>Шутки</h1></a>
                    <a href="/test-mvn-app/forum/JOKES"><p>Посмеёмся? Шутки, приколы, анекдоты, веселые картинки, смешные видеоролики.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/EVENTS"><h1>События</h1></a>
                    <a href="/test-mvn-app/forum/EVENTS"><p>События и происшествия в мире. Горячие новости, хроника и комментарии.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/AUTO"><h1>Авто/Мото</h1></a>
                    <a href="/test-mvn-app/forum/AUTO"><p>Темы про авто/мото технику. Новые машины, аварии, ДТП, случаи на колесах,концепты, выставки, смешные случаи.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/ANIMALS"><h1>Зверьё</h1></a>
                    <a href="/test-mvn-app/forum/ANIMALS"><p>Раздел про животных. Фотографии кошек, собак, насекомых, весь мир фауны. Разная живность!</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/TRAVEL"><h1>Путешествия</h1></a>
                    <a href="/test-mvn-app/forum/TRAVEL"><p>Путеводитель по странам и городам. Репортажи о путешествиях, отдыхе и местах которые стоит посетить.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/CINEMA"><h1>Кино</h1></a>
                    <a href="/test-mvn-app/forum/CINEMA"><p>Все о кино: афиша, новости, рецензии на фильмы, ваше любимое кино, кинозвезды, сплетни.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/GAMES"><h1>Игры</h1></a>
                    <a href="/test-mvn-app/forum/GAMES"><p>Компьютерные игры и развлекательные программы. Обсуждения, прохождения, анонсы новых игр</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/FOOD"><h1>Кулинария</h1></a>
                    <a href="/test-mvn-app/forum/FOOD"><p>Все о кулинарии: рецепты, ваши любимые блюда, кухни всего мира, диета, куда сходить поесть</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/SPORT"><h1>Здоровье и спорт</h1></a>
                    <a href="/test-mvn-app/forum/SPORT"><p>Все о здоровом образе жизни, спортивные мероприятия, события, обсуждения и комментарии.</p></a>
                </div>
            </div>
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <a href="/test-mvn-app/forum/ART"><h1>Творчество</h1></a>
                    <a href="/test-mvn-app/forum/ART"><p>Креатив, творчество пользователей.</p></a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
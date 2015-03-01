<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Calculator</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/specialPages.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/calculator.css"/>
</head>
<body>
<div class="big_background_image">
    <div class="logo">
        <a href="/test-mvn-app/"><img src="/test-mvn-app/resources/images/logo.png"></a>
    </div>
    <div class="content_block">
        <form name="calc" id="calculator">
            <table class="calculator">
                <tr>
                    <td>
                        <input type="text" name="input" class="calculatorDisplay">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="one" value="1" OnClick="calc.input.value += '1'">
                        <input type="button" name="two" value="2" OnClick="calc.input.value += '2'">
                        <input type="button" name="three" value="3" OnClick="calc.input.value += '3'">
                        <input type="button" name="add" value="+" OnClick="calc.input.value += '+'">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="four" value="4" OnClick="calc.input.value += '4'">
                        <input type="button" name="five" value="5" OnClick="calc.input.value += '5'">
                        <input type="button" name="six" value="6" OnClick="calc.input.value += '6'">
                        <input type="button" name="sub" value="-" OnClick="calc.input.value += '-'">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="seven" value="7" OnClick="calc.input.value += '7'">
                        <input type="button" name="eight" value="8" OnClick="calc.input.value += '8'">
                        <input type="button" name="nine" value="9" OnClick="calc.input.value += '9'">
                        <input type="button" name="mul" value="x" OnClick="calc.input.value += '*'">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="clear" value="c" OnClick="calc.input.value = ''">
                        <input type="button" name="zero" value="0" OnClick="calc.input.value += '0'">
                        <input type="button" name="doit" value="=" OnClick="calc.input.value = eval(calc.input.value)">
                        <input type="button" name="div"  value="/" OnClick="calc.input.value += '/'">
                    </td>
                </tr>
            </table>
        </form>

        <ul class="redirect_buttons">
            <li><h1><a href="/test-mvn-app/">На главную</a></h1></li>
        </ul>
    </div>
</div>
</body>
</html>
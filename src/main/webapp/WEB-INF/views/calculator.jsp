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

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<div class="big_background_image">
    <div class="logo">
        <a href="/test-mvn-app/"><img src="/test-mvn-app/resources/images/logo.png"></a>
    </div>
    <div class="content_block">
        <script>

            var lastValue;
            var lastOperation;
            var AddedSymbol;

            function addSymbol(buttonValue) {
                if (AddedSymbol === undefined){
                    clearInput();
                }
                AddedSymbol = document.getElementById('calc_value').value += buttonValue;

            }
            function addOperation(buttonValue) {
                if (lastOperation !== undefined){
                    calculate();
                }
                lastValue = document.getElementById('calc_value').value;
                lastOperation = buttonValue;
                AddedSymbol = undefined;
            }
            function clearInput() {
                document.getElementById('calc_value').value = '';
            }
            function calculate() {
                console.log("lastValue: " + lastValue);
                console.log("lastOperation: " + lastOperation);
                if (lastOperation === '+') {
                    document.getElementById('calc_value').value
                            = parseInt(lastValue)
                            + parseInt(document.getElementById('calc_value').value);
                }
                if (lastOperation === '-') {
                    document.getElementById('calc_value').value
                            = parseInt(lastValue)
                            - parseInt(document.getElementById('calc_value').value);
                }
                if (lastOperation === '*') {
                    document.getElementById('calc_value').value
                            = parseInt(lastValue)
                            * parseInt(document.getElementById('calc_value').value);
                }
                if (lastOperation === '/') {
                    document.getElementById('calc_value').value
                            = parseInt(lastValue)
                            / parseInt(document.getElementById('calc_value').value);
                }
                lastOperation = undefined;
                lastValue = undefined;
                AddedSymbol = undefined;
            }
            function toUSD() {
                var currency = document.getElementById("currency").value;
                $.get( "http://api.fixer.io/latest",
                        function(data) {
                            document.getElementById('calc_value').value
                                    = document.getElementById('calc_value').value
                                    * data.rates[currency];
                        }
                );
            }
        </script>

        <form name="calc" id="calculator">
            <table class="calculator">
                <tr>
                    <td>
                        <input id="calc_value" type="text" name="input" class="calculatorDisplay">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="one" value="1" onclick="addSymbol('1');">
                        <input type="button" name="two" value="2" onclick="addSymbol('2');">
                        <input type="button" name="three" value="3" onclick="addSymbol('3');">
                        <input type="button" name="add" value="+" onclick="addOperation('+');">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="four" value="4" onclick="addSymbol('4');">
                        <input type="button" name="five" value="5" onclick="addSymbol('5');">
                        <input type="button" name="six" value="6" onclick="addSymbol('6');">
                        <input type="button" name="sub" value="-" onclick="addOperation('-');">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="seven" value="7" onclick="addSymbol('7');">
                        <input type="button" name="eight" value="8" onclick="addSymbol('8');">
                        <input type="button" name="nine" value="9" onclick="addSymbol('9');">
                        <input type="button" name="mul" value="x" onclick="addOperation('*');">
                    </td>
                </tr>
                <tr>
                    <td class="buttons">
                        <input type="button" name="clear" value="c" onclick="clearInput();">
                        <input type="button" name="zero" value="0" onclick="addSymbol('0');">
                        <input type="button" name="doit" value="=" onclick="calculate(); addOperation('=');">
                        <input type="button" name="div"  value="/" onclick="addOperation('/');">
                        <form action="" method="post">
                            <input type="button" onclick="toUSD(); addOperation('convert');" value="Convert"/>
                            <select id="currency">
                                <option selected value="USD">USD</option>
                                <option value="AUD">AUD</option>
                                <option value="BGN">BGN</option>
                                <option value="RUB">RUB</option>
                                <option value="MYR">MYR</option>
                                <option value="CAD">CAD</option>
                                <option value="MXN">MXN</option>
                            </select>
                        </form>
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
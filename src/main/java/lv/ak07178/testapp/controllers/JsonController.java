package lv.ak07178.testapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lv.ak07178.testapp.dto.WeatherDTO;

/**
 * @author <a href="mailto:kirill.afanasjev@odnoklassniki.ru">Kirill Afanasjev</a>
 */
@Controller
public class JsonController {

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    public @ResponseBody
    WeatherDTO getLoginPage() {
        WeatherDTO result = new WeatherDTO();
        result.setTemperature(10);
        result.setWindSpeed(3);
        return result;
    }

}

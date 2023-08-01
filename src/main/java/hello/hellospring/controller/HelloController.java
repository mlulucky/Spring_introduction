package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // 스프링부트는 톰캣서버를 내장하고 있음 -> localhost:8000/hello 요청이 오면 스프링에 전달
    // 스프링이 @GetMapping - http url - Get 방식 으로 localhost:8000/hello 매칭을 시켜줌 ->  HelloController 안에 @GetMapping("hello") 의 메서드를 실행시켜줌
    // 스프링이 만들어 넣어주는 Model - 화면에 데이터를 전달한다.
    // return "hello" -> resources - templates 폴더 안에 hello.html 을 찾아서 return, 화면에 렌더한다. // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello"; // resources:templates/ +{ViewName}+ .html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // /hello-mvc?name=spring공부중이다 // ?파라미터
        // @RequestParam("") : "" url 파라미터를 받는다.
        // url 파라미터로 받은 name
        model.addAttribute("name", name);
        // model 로 파라미터로 넘어온 name 을 view 에 넘긴다. // model.addAttribute(key, value)
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http 응답에 바로 데이터를 넘긴다. (view 없음)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring" 문자 응답 // http 응답 - 문자 => 문자로 응답
        // @ResponseBody : http 헤드부/body 부 -> 응답 body 부에 return "hello " + name 데이터를 직접 넣어주겠다. // view(html) 가 없음. 바로 문자가 요청 클라이언트 url 에 넘어간다.
    }

    @GetMapping("hello-api")
    @ResponseBody // http 응답에 바로 데이터를 넘긴다. (view 없음)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 🌈 http 응답 - 객체 => json 로 응답
        hello.setName(name);
        return hello; // {"name":"spring"} // JSON  { key : value }
    }
    static class Hello {
        private String name; // json 의 key

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

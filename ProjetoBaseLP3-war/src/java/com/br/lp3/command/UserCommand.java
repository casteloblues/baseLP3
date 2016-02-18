package com.br.lp3.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1147106
 */
public class UserCommand implements Command {

    public HttpServletRequest request;
    public HttpServletResponse response;
    public String returnPage = "index.jsp";

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
//                System.out.println(request.getParameter("checkSave")); //usado para saber qual o tipo de retorno do checkbox no html
                String check = request.getParameter("checkSave");

                if ("cacique".equals(username) && "123".equals(password)) {
                    returnPage = "index.jsp";

                    if ("on".equals(check)) { //estamos usando isso para salvar no cookie
                        Cookie c = new Cookie("usernameCookie", username);
                        c.setMaxAge(1440);
                        response.addCookie(c);
                        Cookie c2 = new Cookie("pwdCookie", password);
                        c2.setMaxAge(1440);
                        response.addCookie(c2);
                    } else { //zera o cookie
                        Cookie c = new Cookie("usernameCookie", " ");
                        c.setMaxAge(0);
                        response.addCookie(c);
                        Cookie c2 = new Cookie("pwdCookie", " ");
                        c2.setMaxAge(0);
                        response.addCookie(c2);
                    }
                } else {
                    returnPage = "login.jsp";
                }
                break;
//            case "reCookies": //isso pode ser urecuperado pela expression language
//                Cookie[] cookies = request.getCookies();
//                for (Cookie cookie : cookies) {
//                    if(cookie.getName().equals("usernameCookie")) {
//                        request.getSession().setAttribute("usernameC", cookie.getValue()); //o C é referente ao cookie que foi jogado para a sessão
//                    } else if (cookie.getName().equals("pwdCookie")) {
//                        request.getSession().setAttribute("pwdC", cookie.getValue());
//                    }
//                }
//                returnPage = "login.jsp";
//                break;
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

}

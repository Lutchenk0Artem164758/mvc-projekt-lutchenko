package pl.merito.szczyt.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/start") // adres, pod którym działa servlet
public class StartServlet extends HttpServlet {

    // na razie lista szlaków jest na sztywno – w kolejnych etapach trafi do modelu / bazy danych
    private static final String[] SZLAKI = {
            "Giewont (1894 m) – Tatry, szlak niebieski z Kuźnic",
            "Rysy (2499 m) – Tatry, najwyższy szczyt Polski",
            "Orla Perć – Tatry, najtrudniejszy znakowany szlak w Polsce",
            "Babia Góra (1725 m) – Beskid Żywiecki",
            "Skałki Jury Krakowsko-Częstochowskiej – wspinaczka skałkowa"
    };

    // GET – wejście z paska adresu lub kliknięcie linku
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'>");
        out.println("<title>Szczyt – szlaki</title></head><body>");
        out.println("<h1>Witaj w aplikacji Szczyt!</h1>");
        out.println("<p>To jest odpowiedź na żądanie GET.</p>");
        out.println("<h2>Polecane szlaki i rejony</h2>");
        out.println("<ul>");
        for (String szlak : SZLAKI) {
            out.println("<li>" + szlak + "</li>");
        }
        out.println("</ul>");
        out.println("<a href='index.html'>Powrót</a>");
        out.println("</body></html>");
    }

    // POST – wysłanie formularza powitalnego
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.setCharacterEncoding("UTF-8");                 // polskie znaki z formularza
        String imie = req.getParameter("imie");            // pole name="imie"
        String aktywnosc = req.getParameter("aktywnosc");  // turystyka / wspinaczka
        String cel = req.getParameter("cel");              // wymarzony szczyt lub droga

        if (imie == null || imie.isBlank()) {
            imie = "Wędrowcze";
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html><html lang='pl'><head><meta charset='UTF-8'>");
        out.println("<title>Szczyt – powitanie</title></head><body>");
        out.println("<h1>Cześć, " + escape(imie) + "!</h1>");
        out.println("<p>To jest odpowiedź na żądanie POST.</p>");

        if ("wspinaczka".equals(aktywnosc)) {
            out.println("<p>Widzę, że wspinasz się – pamiętaj o kasku i sprawdzeniu partnera przed startem!</p>");
        } else {
            out.println("<p>Widzę, że chodzisz po górach – sprawdź prognozę pogody i komunikaty TOPR przed wyjściem!</p>");
        }

        if (cel != null && !cel.isBlank()) {
            out.println("<p>Twój cel: <b>" + escape(cel) + "</b>. "
                    + "Dziennik wejść będzie dostępny w kolejnych etapach.</p>");
        }
        out.println("<a href='index.html'>Powrót</a>");
        out.println("</body></html>");
    }

    // zamiana znaków specjalnych, żeby tekst z formularza nie był traktowany jak HTML
    private String escape(String tekst) {
        return tekst.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}

package ua.nure.odnokozov.admission.committee.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.admission.committee.dao.AccountDao;
import ua.nure.odnokozov.admission.committee.dao.FacultyDao;
import ua.nure.odnokozov.admission.committee.entity.Account;
import ua.nure.odnokozov.admission.committee.entity.Faculty;
import ua.nure.odnokozov.admission.committee.entity.Language;
import ua.nure.odnokozov.admission.committee.enums.ActivationStatus;

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 3731935382653467640L;
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String command = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(command);
        switch (command) {
        case "/registrate":
            Account user = new Account();
            user.setEmail("sdfqwertyds1@gmail.com");
            user.setPasswordHash("sdfgqwerty");
            user.setLanguage(new Language(1, "ENG"));
            AccountDao userDao = new AccountDao();
            Account newAccount = (userDao.create(user));
            LOG.debug(newAccount);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
        case "/show-accounts-all":
            AccountDao userDao1 = new AccountDao();
            System.out.println(userDao1.getAll());
            request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
        case "/show-accounts-by-id":
            AccountDao userDao12 = new AccountDao();
            System.out.println(userDao12.getById(1));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
        case "/add-faculty":
            Faculty faculty = new Faculty();
            faculty.setTotalSeats(20);
            faculty.setBudgetSeats(3);
            Map<Language, String> titles = new HashMap<>();
            titles.put(new Language(1, "EN"), "Machine Building faculty");
            titles.put(new Language(2, "RU"), "Машиностроительный факультет");
            faculty.setTitles(titles);
            Map<Language, String> descriptions = new HashMap<>();
            descriptions.put(new Language(1, "EN"), "Machine Building faculty description");
            descriptions.put(new Language(2, "RU"), "Машиностроительный факультет описание");
            faculty.setDescriptions(descriptions);
            FacultyDao facultyDao = new FacultyDao();
            System.out.println(facultyDao.create(faculty));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
        case "/show-faculties-all":
            FacultyDao facultyDao1 = new FacultyDao();
            System.out.println(facultyDao1.getAll());
            request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
        case "/add-faculty-by-id":
            FacultyDao facultyDao2 = new FacultyDao();
            System.out.println(facultyDao2.getById(1));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            break;
        }

//        newAccount.setActivationStatus(ActivationStatus.ACTIVETED);
//        Account updateAccount = userDao.update(newAccount);
//        LOG.debug(updateAccount);
//        AccountDao facultyDao = new AccountDao();
        // Account newFaculty = facultyDao.getById(1);
        // System.out.println(newFaculty);
//        System.out.println(facultyDao.getAll());
    }
}

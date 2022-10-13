package org.top.catalogcar;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.top.catalogcar.dao.CarDAO;
import org.top.catalogcar.entity.CarsTEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarDAO carDAO;

    @Override
    public void init() {
        carDAO = new CarDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCar(request, response);
                    break;
                case "/delete":
                    deleteCar(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCar(request, response);
                    break;
                default:
                    listCars(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCars(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<CarsTEntity> listCars = carDAO.getAllCars();
        request.setAttribute("listCars", listCars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        CarsTEntity existingCar = carDAO.getCarById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
        request.setAttribute("car", existingCar);
        dispatcher.forward(request, response);

    }

    private void insertCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nameF = request.getParameter("nameCar");
        String modelF = request.getParameter("model");
        int yearF = Integer.parseInt(request.getParameter("year"));
        double priceF = Double.parseDouble(request.getParameter("price"));

        CarsTEntity newCar = new CarsTEntity();
        newCar.setNameF(nameF);
        newCar.setModelF(modelF);
        newCar.setYearF(yearF);
        newCar.setPriceF(priceF);
        carDAO.insertCar(newCar);
        response.sendRedirect("list");
    }

    private void updateCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nameF = request.getParameter("nameCar");
        String modelF = request.getParameter("model");
        int yearF = Integer.parseInt(request.getParameter("year"));
        double priceF = Double.parseDouble(request.getParameter("price"));

        CarsTEntity newCar = carDAO.getCarById(id);
        newCar.setNameF(nameF);
        newCar.setModelF(modelF);
        newCar.setYearF(yearF);
        newCar.setPriceF(priceF);

        carDAO.updateCar(newCar);
        response.sendRedirect("list");
    }

    private void deleteCar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        carDAO.deleteCarById(id);
        response.sendRedirect("list");

    }
}
package at.ofi.certificate.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.ofi.certificate.backend.DefaultVersionService;
import at.ofi.certificate.backend.api.VersionService;

/**
 * @author HFrebort
 * @version Feb 9, 2019
 */
@WebServlet("/versions")
@MultipartConfig
public class VersionServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   private VersionService service = new DefaultVersionService();

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setAttribute("versions", service.getVersions());
      final RequestDispatcher dispatcher = request.getRequestDispatcher("versions.jsp");
      dispatcher.forward(request, response);
   }
}

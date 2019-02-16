package at.ofi.certificate.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.ofi.certificate.backend.DefaultFindCertificateService;
import at.ofi.certificate.backend.api.FindCertificateService;

/**
 * @author HFrebort
 * @version Feb 9, 2019
 */
@WebServlet("/find")
@MultipartConfig
public class FindCertificateServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   private static final String RESULT_HEADER = "<table><tr><td>Hersteller</td><td>Auditor</td><td>Normen</td></tr>";

   private static final String RESULT_BODY = "<tr><td>%s</td><td>%s</td><td>%s</td></tr>";

   private static final String RESULT_FOOTER = "</table>";

   private FindCertificateService service = new DefaultFindCertificateService();

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter w = new PrintWriter(response.getOutputStream(), true);
      w.println(String.format(RESULT_HEADER));
      service.findEntries().forEach(c -> {
         w.println(String.format(RESULT_BODY, c.getManufacturer(), c.getAuditor(), c.getNorms()));
      });
      w.println(String.format(RESULT_FOOTER));
      w.close();
   }
}

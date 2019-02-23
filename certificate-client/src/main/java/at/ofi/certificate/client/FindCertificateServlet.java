package at.ofi.certificate.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.ofi.certificate.backend.DefaultFindCertificateService;
import at.ofi.certificate.backend.api.CertificateEntry;
import at.ofi.certificate.backend.api.FindCertificateService;

/**
 * This is the servlet to find certificate entries.
 * 
 * @author HFrebort
 * @version Feb 9, 2019
 */
@WebServlet("/find")
@MultipartConfig
public class FindCertificateServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   private FindCertificateService service = new DefaultFindCertificateService();

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      final String versionId = request.getParameter("versionId");
      final List<CertificateEntry> certificateEntries = getCertificateEntries(versionId);
      final RequestDispatcher dispatcher = request.getRequestDispatcher("searchResult.jsp");
      request.setAttribute("certificateEntries", certificateEntries);
      dispatcher.forward(request, response);
   }

   private List<CertificateEntry> getCertificateEntries(final String versionId) {
      if (versionId != null) {
         return service.findEntriesByVersionId(versionId);
      }
      return service.findEntries();
   }
}

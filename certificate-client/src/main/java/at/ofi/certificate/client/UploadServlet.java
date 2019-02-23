package at.ofi.certificate.client;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import at.ofi.certificate.backend.DefaultCertificateService;
import at.ofi.certificate.backend.api.CertificateService;

/**
 * This is the upload servlet to handle the xls-file and call the service to verify and insert into the database.
 * 
 * @author HFrebort
 * @version Feb 9, 2019
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   private static final String UPLOAD_FILE_PARAMETER = "uploadFile";

   private CertificateService service = new DefaultCertificateService();

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      final Part filePart = request.getPart(UPLOAD_FILE_PARAMETER);
      final InputStream inputStreamt = filePart.getInputStream();
      request.setAttribute("result", service.insert(inputStreamt));
      final RequestDispatcher dispatcher = request.getRequestDispatcher("uploadResult.jsp");
      dispatcher.forward(request, response);
   }
}

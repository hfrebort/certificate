package at.ofi.certificate.client;

import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import at.ofi.certificate.backend.DefaultCertificateService;
import at.ofi.certificate.backend.api.CertificateService;
import at.ofi.certificate.backend.api.VerificationResult;

/**
 * @author HFrebort
 * @version Feb 9, 2019
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   private static final String UPLOAD_FILE_PARAMETER = "uploadFile";

   private static final String RESULT = "<h1>Status: %s</h1><p>%s</p>";

   private CertificateService service = new DefaultCertificateService();

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      final Part filePart = request.getPart(UPLOAD_FILE_PARAMETER);
      final InputStream inputStreamt = filePart.getInputStream();
      final VerificationResult result = service.insert(inputStreamt);
      response.getOutputStream()
            .println(format(RESULT, result.getStatus(), result.getMessage()));
   }
}

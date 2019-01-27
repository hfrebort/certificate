/**
 * 
 */
package at.ofi.certificat.backend;

/**
 * This is the interface definition of the certification service. Use verify for
 * upload verification. Use insert for upload provision.
 * 
 * @author HFrebort
 *
 */
public interface CertificateService {

	VerificationResult verify(VerificationContext context);

	void insert(VerificationContext context);
}

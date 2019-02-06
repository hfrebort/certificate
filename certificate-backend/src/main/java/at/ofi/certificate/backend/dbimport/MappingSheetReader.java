package at.ofi.certificate.backend.dbimport;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import at.ofi.exceltocertsdb.CertificateSheetType;

public class MappingSheetReader {

	private static final String PACKAGE_NAME = "at.ofi.exceltocertsdb";

	public static CertificateSheetType read(InputStream inputStream) {
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(PACKAGE_NAME);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			return
					unmarshaller
							.unmarshal(new StreamSource(inputStream), CertificateSheetType.class)
							.getValue();

		} catch (final Exception e) {
			throw new RuntimeException("Can not read definition file!");
		}
	}

}

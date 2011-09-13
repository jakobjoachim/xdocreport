package fr.opensagres.xdocreport.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.service.DataContext;
import fr.opensagres.xdocreport.document.service.Report;
import fr.opensagres.xdocreport.document.service.ReportId;
import fr.opensagres.xdocreport.document.service.WSOptions;
import fr.opensagres.xdocreport.document.service.XDocReportService;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

@Path("/")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RESTXDocReportService {

	private XDocReportService delegate = XDocReportService.INSTANCE;

	public byte[] download(String reportID, String processState) throws XDocReportException {
		return delegate.download(reportID, processState);
	}

	@GET
	@Path("/test")
	public String get() {

		return "test";
	}

	@GET
	@Path("/listReports")
	public List<ReportId> listReports() {
		return delegate.listReports();
	}
	@POST
	@Path("/processReport")
	@Produces({"text/xml", "application/xml", "application/json"}) 
    @Consumes({"application/x-www-form-urlencoded","multipart/form-data"})  
	public byte[] processReport(@FormParam("") Report report
			, @FormParam("") ArrayList<DataContext> dataContext,
			@FormParam("") WSOptions wsOptions) throws XDocReportException {
		
		FieldsMetadata fieldsMetadata = new FieldsMetadata();
		List<String> fields= report.getFieldsMetaData();
		for (String field : fields) {
			fieldsMetadata.addFieldAsList(field);
		}
		Options options= Options.getFrom(wsOptions.getFrom()).to(wsOptions.getTo()).via(wsOptions.getVia());
		return delegate.process(report.getDocument(), fieldsMetadata, report.getTemplateEngine(), dataContext,options);
	}

	public byte[] processReport(String reportId, List<DataContext> dataContext, Options options) throws XDocReportException {
		return delegate.process(reportId, dataContext, options);
	}

	public void unRegister(String reportId) {
		delegate.unregisterReport(reportId);
	}

	@POST
	@Path("/upload")
	public void upload(Report report) throws XDocReportException {
		System.out.println(report);
		fr.opensagres.xdocreport.template.formatter.FieldsMetadata fieldsMetadata2 = new fr.opensagres.xdocreport.template.formatter.FieldsMetadata();
		for (String field : report.getFieldsMetaData()) {
			fieldsMetadata2.addFieldAsList(field);
		}

		delegate.registerReport(report.getReportID(), report.getDocument(), fieldsMetadata2, "Velocity");

	}

}
package com;

import model.Funding;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundingService {
	Funding fundingObj = new Funding();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProduct() {
		return fundingObj.readFund();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("funderName") String funderName, 
			@FormParam("fundDate") String fundDate,
			@FormParam("fundPrice") String fundPrice,
			@FormParam("fundCate") String fundCate,
			@FormParam("fundDesc") String fundDesc) {
		String output = fundingObj.insertFund(funderName, fundDate, fundPrice,fundCate, fundDesc);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateFund(String fundData) {
		// Convert the input string to a JSON object
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();

		// Read the values from the JSON object
		String fundId  = fundObject.get("fundId").getAsString();
		String funderName = fundObject.get("funderName").getAsString();
		String fundDate = fundObject.get("fundDate").getAsString();
		String fundPrice = fundObject.get("fundPrice").getAsString();
		String fundCate = fundObject.get("fundCate").getAsString();
		String fundDesc = fundObject.get("fundDesc").getAsString();

		String output = fundingObj.updateFund(fundId, funderName, fundDate, fundPrice, fundCate,fundDesc);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String fundData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String fundId = doc.select("fundId").text();
		String output = fundingObj.deleteFund(fundId);
		return output;
	}
}

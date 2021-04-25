package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Payments")


public class PaymentService 
{
	
	Payment paymentObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment()
	{
	 
			return paymentObj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("date") String date,
			@FormParam("payment_amount") String payment_amount,
			@FormParam("payment_description") String payment_description)
	{
		
		String output = paymentObj.insertPayment(date, payment_amount, payment_description);
		return output;
		
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
		//Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		
		//Read the values from the JSON object
		
		String payment_id = paymentObject.get("payment_id").getAsString();
		String date = paymentObject.get("date").getAsString();
		String payment_amount = paymentObject.get("payment_amount").getAsString();
		String payment_description = paymentObject.get("payment_description").getAsString();
		
		String output = paymentObj.updatePayment(payment_id, date, payment_amount, payment_description);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		
		//Read the value from the element <payment_id>
		String payment_id = doc.select("payment_id").text();
		String output = paymentObj.deletePayment(payment_id);
		
		return output;
	}

}

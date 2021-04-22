package com;

import model.Beneficiary; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Inventors")

public class BeneficiaryService {

	Beneficiary benObj = new Beneficiary(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readInventors() 
	 { 
		return benObj.readInventors();
	 } 
	
	//insert item
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertInventor(@FormParam("inventorUsername") String inventorUsername, 
		 @FormParam("inventorAge") String inventorAge, 
		 @FormParam("inventorAddress") String inventorAddress, 
		 @FormParam("inventorPassword") String inventorPassword) 
		{ 
			String output = benObj.insertInventor(inventorUsername, inventorAge, inventorAddress, inventorPassword); 
			
			return output; 
		}

	
		//add method

		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateInventor(String inventorData) 
		{ 
			//Convert the input string to a JSON object 
			JsonObject benObject = new JsonParser().parse(inventorData).getAsJsonObject(); 
			
			//Read the values from the JSON object
			 
			String inventorID = benObject.get("inventorID").getAsString(); 
			String inventorUsername = benObject.get("inventorUsername").getAsString(); 
			String inventorAge = benObject.get("inventorAge").getAsString(); 
			String inventorAddress = benObject.get("inventorAddress").getAsString(); 
			String inventorPassword = benObject.get("inventorPassword").getAsString(); 
			
			String output = benObj.updateInventor(inventorID, inventorUsername, inventorAge, inventorAddress, inventorPassword);
			
			return output; 
		}


	// delete
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteInventor(String inventorData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(inventorData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <inventorID>
		String inventorID = doc.select("inventorID").text(); 
		String output = benObj.deleteInventor(inventorID); 
		
		return output; 
	}
}
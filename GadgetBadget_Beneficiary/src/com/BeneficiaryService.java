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
@Path("/Beneficiary")

public class BeneficiaryService {

	Beneficiary benObj = new Beneficiary(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return benObj.readBeneficiary();
	 } 
	
	//insert item
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertItem(@FormParam("beneficairyCode") String beneficiaryCode, 
		 @FormParam("beneficiaryName") String beneficiaryName, 
		 @FormParam("productType") String productType, 
		 @FormParam("beneficiaryDesc") String beneficiaryDesc) 
		{ 
		 String output = benObj.insertBeneficiary(beneficiaryCode, beneficiaryName, productType, beneficiaryDesc); 
		return output; 
		}

	
		//add method

		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateBeneficiary(String beneficiaryData) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject benObject = new JsonParser().parse(beneficiaryData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String beneficiaryID = benObject.get("beneficiaryID").getAsString(); 
		 String beneficiaryCode = benObject.get("beneficairyCode").getAsString(); 
		 String beneficiaryName = benObject.get("beneficiaryName").getAsString(); 
		 String productType = benObject.get("productType").getAsString(); 
		 String beneficiaryDesc = benObject.get("beneficiaryDesc").getAsString(); 
		 String output = benObj.updateBeneficiary(beneficiaryID, beneficiaryCode, beneficiaryName, productType, beneficiaryDesc); 
		return output; 
		}


// delete

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteBeneficiary(String beneficiaryData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(beneficiaryData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String beneficiaryID = doc.select("beneficiaryID").text(); 
 String output = benObj.deleteBeneficiary(beneficiaryID); 
return output; 
}
}
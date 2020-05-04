package com;

import model.DoctorRegister;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctor")
public class adminService {
	DoctorRegister docobj = new DoctorRegister();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return docobj.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN) // `DocID`,`DocLNo`,`Name`,`NIC`,`PhoneNo`,`Email`,`Address`,`DocSpeacialist`,`WorkingXp`,`Password`
	public String insertItem(@FormParam("DocLNo") String DocLNo, 
			@FormParam("Name") String Name,
			@FormParam("NIC") String NIC, @FormParam("PhoneNo") String PhoneNo, @FormParam("Email") String Email,
			@FormParam("Address") String Address, @FormParam("DocSpeacialist") String DocSpeacialist,
			@FormParam("WorkingXp") String WorkingXp, @FormParam("Password") String Password) {
		String output = docobj.insertItem(DocLNo, Name, NIC, PhoneNo, Email, Address, DocSpeacialist, WorkingXp,
				Password);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String DocID = itemObject.get("DocID").getAsString();
		String DocLNo = itemObject.get("DocLNo").getAsString();
		String Name = itemObject.get("Name").getAsString();
		String NIC = itemObject.get("NIC").getAsString();
		String PhoneNo = itemObject.get("PhoneNo").getAsString();
		String Email = itemObject.get("Email").getAsString();
		String Address = itemObject.get("Address").getAsString();
		String DocSpeacialist = itemObject.get("DocSpeacialist").getAsString();
		String WorkingXp = itemObject.get("WorkingXp").getAsString();
		String Password = itemObject.get("Password").getAsString();
		String output = docobj.updateItem(DocID, DocLNo, Name, NIC, PhoneNo, Email, Address, DocSpeacialist, WorkingXp,
				Password);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String DocID = doc.select("DocID").text();
		String output = docobj.deleteItem(DocID);
		return output;
	}

}

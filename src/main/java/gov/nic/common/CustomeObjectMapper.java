package gov.nic.common;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.nic.model.JsonRootBean;
import gov.nic.model.ReturnR9Cnt;
import gov.nic.model.ReturnR9CntDet;

public class CustomeObjectMapper {
	
	static Logger logger = Logger.getLogger(CustomeObjectMapper.class);
	
	  //json parse for Return R1 file count.........
	 public static ReturnR9Cnt parseReturnR9Count(String jsonArray) throws JsonParseException, JsonMappingException, IOException{			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ReturnR9Cnt retR9Cnt=objectMapper.readValue(jsonArray, ReturnR9Cnt.class);			
			return retR9Cnt;			
	}
	 
	 //json parse for Retrun R1 file .........
	 public static ReturnR9CntDet parseReturnR9File(String jsonArray) throws JsonParseException, JsonMappingException, IOException{			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ReturnR9CntDet returnR9CntDet=objectMapper.readValue(jsonArray, ReturnR9CntDet.class);			
			return returnR9CntDet;			
	}
	 
	 public static JsonRootBean parseReturnR9FileData(String path) throws JsonParseException, JsonMappingException, IOException{			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JsonRootBean gstR1RootBean=objectMapper.readValue(new File(path), JsonRootBean.class);			
			return gstR1RootBean;			
	}
	 
//	 public static Document parseReturnR9Gson(String path) throws JsonParseException, JsonMappingException, IOException{			
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			JsonRootBean gstR1RootBean=objectMapper.readValue(new File(path), JsonRootBean.class);	
//			//FileReader reader = new FileReader(new File(path));
//			Document object=null;
//			try {
//				  Gson gson = new Gson();
//				//  object = (Document) JSON.parse(gson.toJson(gstR1RootBean));	
//				  object =Document.parse(gson.toJson(gstR1RootBean));
//				 // object = Document.parse( gstR1RootBean.toString() );
//				//  logger.info("DBOBJECT is:----"+object);
//			}catch(Exception ex) {
//				ex.printStackTrace();
//			}
//			
//			//DBObject obj = (DBObject) JSON.parse(path);
//			//DBObject dbobj=(DBObject)JSON.parse(reader);
//			return object;			
//	}

}

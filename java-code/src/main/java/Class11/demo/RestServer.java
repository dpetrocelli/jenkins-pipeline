package Class11.demo;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})

public class RestServer {
    Gson gson = new Gson();
   
    private final Logger log = LoggerFactory.getLogger(RestServer.class);

    public RestServer(){
        
    }

    
    // -> nombre y tipo del método en escucha (GET /test)
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    // Además le digo que estoy esperando una variable por "header" que es "name"
    //http://localhost:8080/test?name="algo"
    public ResponseEntity<String> listJobs(@RequestParam("name") String nombre) throws InterruptedException {
        // ALGO ->
        return new ResponseEntity<String>(("Hola tu nombre es: "+nombre).trim(), HttpStatus.OK);
    }

   


}

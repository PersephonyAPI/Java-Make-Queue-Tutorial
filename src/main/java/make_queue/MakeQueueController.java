/* 
 * AFTER RUNNING PROJECT WITH COMMAND: 
 * `gradle build && java -Dserver.port=0080 -jar build/libs/gs-spring-boot-0.1.0.jar`
 * RUN CURL COMMAND:
 * `curl {baseUrl}/makeQueue`
 */

package main.java.make_queue;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vailsys.persephony.api.PersyClient;
import com.vailsys.persephony.api.PersyException;
import com.vailsys.persephony.api.queue.QueueCreateOptions;

@RestController
public class MakeQueueController {
  // Get accountID and authToken from environment variables
  private String accountId = System.getenv("ACCOUNT_ID");
  private String authToken = System.getenv("AUTH_TOKEN");

  @RequestMapping("/makeQueue")
  public void makeQueue() {
    // Options payload to set alias and maximum size when creating the queue
    QueueCreateOptions options = new QueueCreateOptions();
    options.setAlias("Tutorial Queue");
    options.setMaxSize(25);

    try {
      // Create PersyClient object
      PersyClient client = new PersyClient(accountId, authToken);

      // Invoke method to create a queue with the options provided
      client.queues.create(options);
    } catch (PersyException pe) {
      System.out.println(pe.getMessage());
    }
  }
}
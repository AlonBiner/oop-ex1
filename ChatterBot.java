import java.util.*;

/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 * @author Dan Nirel
 */
class ChatterBot {
	// Constants
	static final String REQUEST_PREFIX = "say ";
	static final String REQUESTED_PHRASE_PLACEHOLDER = "<phrase>";
	static final String ILLEGAL_REQUEST_PLACEHOLDER = "<request>";

    // Instance variables
	Random rand = new Random();
	String[] repliesToLegalRequests;
	String[] repliesToIllegalRequest;
	String name;

    // Constructor for ChatterBot object
	ChatterBot(String name, String[] repliesToLegalRequests, String[] repliesToIllegalRequest) {
		this.name = name;
		this.repliesToLegalRequests = new String[repliesToLegalRequests.length];
		for(int i = 0 ; i < repliesToLegalRequests.length ; i = i+1) {
			this.repliesToLegalRequests[i] = repliesToLegalRequests[i];
		}

		this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
		for(int i = 0 ; i < repliesToIllegalRequest.length ; i = i+1) {
			this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
		}
	}

    // Respond according to the legality given statement
	String replyTo(String statement) {
		if(statement.startsWith(REQUEST_PREFIX)) {
		    // Respond to legal requests
			return respondToLegalRequest(statement);
		}
		return respondToIllegalRequest(statement);
	}


    // Respond to a legal request
    String respondToLegalRequest(String statement) {
        String phrase = statement.replaceFirst(REQUEST_PREFIX, "");
		return replacePlaceholderInARandomPattern (repliesToLegalRequests,
		    REQUESTED_PHRASE_PLACEHOLDER, phrase);
    }

    // Respond to an illegal request
	String respondToIllegalRequest(String statement) {
		return replacePlaceholderInARandomPattern (repliesToIllegalRequest,
		    ILLEGAL_REQUEST_PLACEHOLDER, statement);
	}

    // Calculate the response to return
	String replacePlaceholderInARandomPattern (String[] repliesArray,
	    String placeHolder, String statement) {
	    int randomIndex = rand.nextInt(repliesArray.length);
		String responsePattern = repliesArray[randomIndex];
        return responsePattern.replaceAll(placeHolder, statement);
	}

    // Get name of this ChatterBot
	String getName() { return this.name; }
}

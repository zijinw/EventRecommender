package external;

/**
 * Created by Zijin Wang on 10/12/2017.
 */
public class ExternalAPIFactory {
    private static final String DEFAULT_PIPELINE = "Ticketmaster";

    // Start different APIs based on the pipeline.
    public static ExternalAPI getExternalAPI(String pipeline) {
        switch (pipeline) {
            case "Ticketmaster":
                return new TicketMasterAPI();
            default:
                throw new IllegalArgumentException("Invalid pipeline: " + pipeline);
        }
    }

    public static ExternalAPI getExternalAPI() {
        return getExternalAPI(DEFAULT_PIPELINE);
    }
}

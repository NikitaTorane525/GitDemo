package stepdefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void beforeScenario() throws IOException {

		StepDefination st = new StepDefination();
		if (StepDefination.place_id == null) {
			st.add_Place_Payload("nikiya_1_1", "englis", "xys");
			st.user_calls_something_with_post_http_request("addPlaceAPI", "post");
			st.verify_place_Id_created_maps_to_using("nikiya_1_1", "getPlaceAPI");
		}
	}
}

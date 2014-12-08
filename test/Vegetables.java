import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
*
*
*/
public class Vegetable {
    public String name;
    public Color color; // Color is an enum
}
@Given("I have these vegetables:")
public void I_have_these_vegetables(List<Vegetable> vegetables) {
    // Do something with the vegetables
}
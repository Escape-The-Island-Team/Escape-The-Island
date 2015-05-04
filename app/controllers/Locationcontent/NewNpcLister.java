package controllers.Locationcontent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maik Wandrei on 04.05.2015.
 */
public class NewNpcLister implements INpcLister
{

    @Override
    public List<String> getNpcs(String location)
    {
        List<String> result = new ArrayList<>();

        switch (location)
        {
            case "temple":      result.add("nativeMaya");
                                break;
            case "laboratory":  result.add("scientist");
                                break;
        }

        return result;
    }
}

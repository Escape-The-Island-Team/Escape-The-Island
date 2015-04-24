/**
 * Created by grandmaster Norman on 18.04.2015.
 */

function selectObject(objectName) {

    var test7 = 89723;



    //document.getElementById('test123').textContent=(document.getElementsByClassName('test').zweipluszwei()).toString();
    //document.getElementById('test123').textContent=document.getElementById('app').zweipluszwei().toString();

    var result = null;
    model_data = JSON.stringify(test7);

    $.ajax({
        url : '/getValue/' + test7,
        type : 'POST',
        contentType : 'application/json',
        data : model_data,
        dataType : 'json html',
        converters : {
            'text json' : true
        },
        success : function(response) {
            response = JSON.parse(response);
            result = response;
            document.getElementById('textTest').textContent=result;
        },
        error : function(data, request) {
            alert("FAIL " + data);
        },
    });
}



function getNewLocation(info) {


    var test7 = "beach-mid#beach-left";

    var result;

    var splitResult="";

    model_data = JSON.stringify(test7);

    $.ajax({
        url: '/getLocation/' + test7,
        type: 'POST',
        contentType: 'application/json',
        data: model_data,
        dataType: 'json html',
        converters: {
            'text json': true
        },
        success: function (response) {
            response = JSON.parse(response);
            result = response;
            document.getElementById('textTest').textContent = result;
            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="#")
                {
                    splitResult+=result.substring(i,result.size-i)+" ";
                    switch(result.substring(i,result.size-i))
                    {
                        case "beach-mid":
                    }
                    result=result.substring(i+1);
                    i=0;
                }
            }
            document.getElementById('textTest').textContent = splitResult;
        },
        error: function (data, request) {
            alert("FAIL " + data);
        },
    });
}


function loadingSurface()
{
    window.location='loadingGame';
    getNewLocation("start");
}
function loadingSurface2()
{
    window.location='loadingGame';
}

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


    var test7 = "beachMid-beachLeft-";
    test7=info;


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
            //document.getElementById('textTest').textContent = result;
            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult+=result.substring(i,result.size-i)+" ";
                    switch(result.substring(i,result.size-i))
                    {
                        case "beachMidAvailable": window.location ="loadBeachMid";
                             break;
                        case "beachLeftAvailable": window.location ="loadBeachLeft";
                            break;
                        case "beachRightAvailable": window.location ="loadBeachRight";
                            break;
                        case "jungleAvailable": window.location ="loadJungle";
                            break;
                        case "templeAvailable": window.location ="loadTemple";
                            break;
                        case "riverAvailable": window.location ="loadRiver";
                            break;
                        case "openingAvailable": window.location ="loadOpening";
                            break;
                        case "lakeAvailable": window.location ="loadLake";
                            break;
                        case "caveAvailable": window.location ="loadCave";
                            break;
                        case "cliffAvailable": window.location ="loadCliff";
                            break;
                        default: break;
                    }
                    result=result.substring(i+1);
                    i=0;
                }
            }
            //document.getElementById('textTest').textContent = splitResult;
        },
        error: function (data, request) {
            alert("FAIL " + data+result);
        }
    });
}


function loadingSurface()
{
    //window.location='loadingGame';
    getNewLocation("start");
}
function loadingSurface2()
{
    window.location='loadingGame';
}




function makeArrowVisible(id)
{
    //document.getElementById('arrowLeft_beach-mid').style.display = 'block';
    $("#"+id).css({"visibility": "visible"});
}
function makeArrowInvisible(id)
{
    $("#"+id).css({"visibility": "hidden"});
}

function selectItem(id)
{
    if(document.getElementById(id).getAttribute('class')=='table_td')
    {
        document.getElementById(id).setAttribute('class','table_td_selected');
    }
    else
    {
        document.getElementById(id).setAttribute('class','table_td');
    }
}
function selectTool(id)
{
    if(document.getElementById(id).getAttribute('class')=='table_td_tool')
    {
        document.getElementById(id).setAttribute('class','table_td_tool_selected');
    }
    else
    {
        document.getElementById(id).setAttribute('class','table_td_tool');
    }
}
function expandInventory()
{

}

function blubb()
{
    alert("Damn! That's a piranha!");
}
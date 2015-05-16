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
                        case "rocksAvailable": window.location ="loadRocks";
                            break;
                        case "vulcanoAvailable": window.location ="loadVulcano";
                            break;
                        case "waterfallAvailable": window.location ="loadWaterfall";
                            break;
                        case "treehouseAvailable": window.location ="loadTreehouse";
                            break;
                        case "laboratoryAvailable": window.location ="loadLaboratory";
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

function test(str)
{
/*
    imagePrefix ='objects/rope';
    var urlString = 'url(images/' + imagePrefix + '.png)';
    document.getElementById(str).style.backgroundImage= urlString;
    $("#"+str).attr.("src", '/images/objects/rope.png');
    //$("#"+str).css({"visibility": "hidden"});
    //alert(str);
    $("#"+str).css({"src": "hidden"});

    'url(/assets/images/objects/rope.png)';
    */
}
function collectItem2(idInfoMsg,classInfoBar,id_div, id, id2) {
    // hide item on location
    $("#" + id_div).css({"visibility": "hidden"});
    // get the source for the new img for the item(s) in the itembar and set the itembar-icon to this img
    vv = document.getElementById(id).src;
    document.getElementById(id2).setAttribute('src', vv);
    // make infoBar visible
    makeVisibleClass(classInfoBar);
    document.getElementById(idInfoMsg).textContent = "Rumbarrel collected.";
    // alert("Rumbarrel collected.");
    rebuiltItembar(vv);
}


function collectItem(nameItemToCollect, idItemToCollect)
{
    // var infoToReturn = "successful-itembar-1-stick-messageInfo-You have found a stick!-";
    var item = nameItemToCollect;

    var result;
    var splitResult="";

    var successful = true;
    var itembarGetSizeNext = false;
    var itembarSetItems = false;
    var messageNext = false;
    var itembarSize = 0;
    var itembarCounter = 1;

    var idBase = "imgSlot";
    var idComplete ="";

    var imgItemID ='';
    var imgItemPath = '';

    var imgItemIDDefault = 'imgItembarDefault';

    model_data = JSON.stringify(item);

    // call of java method
    $.ajax({
        url: '/collectItem/' + item,
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

            for(var i=0; i<result.length && successful; i++)
            {
                if(result[i]=="-")
                {
                    splitResult+=result.substring(i,result.size-i)+" ";

                    // sets the items for the itembar
                    if(itembarSetItems)
                    {
                        // create proper id of the item bar slot
                        idComplete=idBase+itembarCounter;
                        switch(result.substring(i,result.size-i))
                        {
                            case "stick": imgItemID = 'itemStick';
                                break;
                        }
                        // select the item slot and insert the fitting item image
                        document.getElementById(idComplete).setAttribute('src',document.getElementById(imgItemID).src);

                        itembarCounter++;

                        // if all items are set, the rest of the itembar slots are filled with the default img
                        if(itembarCounter > itembarSize)
                        {
                            for(itembarCounter; itembarCounter<=20; itembarCounter++)
                            {
                                idComplete=idBase+itembarCounter;

                                document.getElementById(idComplete).setAttribute('src',document.getElementById(imgItemIDDefault).src);
                            }

                            itembarSetItems = false;
                        }
                    }
                    // gets the size of the itembar
                    else if(itembarGetSizeNext)
                    {
                        itembarSize = parseInt(result.substring(i,result.size-i));
                        itembarGetSizeNext = false;
                        itembarSetItems = true;
                    }
                    else if(messageNext)
                    {
                        showInfoMessage(result.substring(i,result.size-i));
                        messageNext = false;
                    }

                    // to set successful in beginning, initiate itembar or give out an info message
                    else
                    {
                        switch(result.substring(i,result.size-i))
                        {
                            // set successful in beginning
                            case "successful":
                                successful = true;
                                $("#" + idItemToCollect).css({"visibility": "hidden"});
                                break;
                            // if not successful, working with the result will end
                            case "notSuccessful":
                                successful = false;
                                break;
                            case "itembar":
                                itembarGetSizeNext = true;
                                break;
                            case "messageInfo":
                                messageNext = true;
                                break;
                        }
                    }
                    // prepare for the next splitresult to read
                    result=result.substring(i+1);
                    i=0;
                }
            }
        },
        error: function (data, request) {
            alert("FAIL " + data+result);
        }
    });
}












    /*
    document.getElementById(id).setAttribute('src','url(/assets/images/objects/test)');
    test7="test.png";
    $.ajax({
        url : '/assets/' + test7,
        type : 'GET',
        contentType : 'application/json',
        data : model_data,
        dataType : 'json html',
        converters : {
            'text json' : true
        },
        success : function(response) {
            response = JSON.parse(response);
            result = response;
            alert("gh");
        },
        error : function(data, request) {
            alert("FAIL " + data);
        },
    });







    //document.getElementById(id).style.backgroundImage='url(/assets/images/objects/rope.png)';
    document.getElementById(id).setAttribute('src','url(/assets/images/objects/rope.png)');
    //$("#"+id).css({"src": zzz});
    alert("sd");
*/

function interactWithObject(idInfoMsg,classInfoBar,id_div, id, id2) {
    $("#" + id_div).css({"visibility": "hidden"});
    vv = document.getElementById(id).src;
    document.getElementById(id2).setAttribute('src', vv);
    makeVisibleClass(classInfoBar);
    document.getElementById(idInfoMsg).textContent = "Rumbarrel collected.";
    // alert("Rumbarrel collected.");
}


function makeVisibleClass(cls)
{
    $("."+cls).css({"visibility": "visible"});
}
function makeInvisibleClass(cls)
{
    $("."+cls).css({"visibility": "hidden"});
}

function talkToNPC(npc,idInfoMsg, classInfoBar)
{
    if(npc == 'maya')
    {
        makeVisibleClass(classInfoBar);
        document.getElementById(idInfoMsg).textContent = "Hello my friend! Welcome to the huge temple of my people.";
    }
    else if(npc == 'versutus')
    {
        makeVisibleClass(classInfoBar);
        document.getElementById(idInfoMsg).textContent = "I am Versutus, the famous inventor. But you've already heard of me, didn't you?";
    }
}

function rebuiltItembar(vv)
{
    var idBase = "imgSlot";
    var idComplete;
    for(var i=1; i<=20; i++)
    {
        idComplete=idBase+i;

        document.getElementById(idComplete).setAttribute('src',vv);
    }
}

function showInfoMessage(message)
{
    makeVisibleClass('infoArea');
    document.getElementById('infoTextMsg').textContent = message;
}
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

    var messageNext = false;

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
                    splitResult=result.substring(i,result.size-i)+" ";

                    if(messageNext)
                    {
                        showInfoMessage(result.substring(i,result.size-i));
                        messageNext = false;
                    }

                    switch(result.substring(i,result.size-i))
                    {
                        case "messageInfo":
                            messageNext = true;
                            break;

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
                        case "volcanoAvailable": window.location ="loadVolcano";
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



function getNewLocation2(info)
{
    var test = "beachMid-beachLeft-";

    var result;
    var splitResult="";

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

    model_data = JSON.stringify(info);

    $.ajax({
        url: '/getLocation/' + info,
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
                    splitResult=result.substring(i,result.size-i)+" ";

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
                    else
                    {
                        switch(result.substring(i,result.size-i))
                        {
                            case "itembar":
                                itembarGetSizeNext = true;
                                break;
                            case "messageInfo":
                                messageNext = true;
                                break;
                            // set the proper location
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
                            case "volcanoAvailable": window.location ="loadVolcano";
                                break;
                            case "waterfallAvailable": window.location ="loadWaterfall";
                                break;
                            case "treehouseAvailable": window.location ="loadTreehouse";
                                break;
                            case "laboratoryAvailable": window.location ="loadLaboratory";
                                break;
                            default: break;
                        }
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
function collectItem1(idInfoMsg,classInfoBar,id_div, id, id2) {
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


function collectItem2(nameItemToCollect, idItemToCollect)
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
                    splitResult=result.substring(i,result.size-i);

                    // sets the items for the itembar
                    if(itembarSetItems)
                    {
                        // create proper id of the item bar slot
                        idComplete=idBase+itembarCounter;
                        switch(splitResult)
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
                        switch(splitResult)
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




function collectItem(nameItemToCollect, idItemToCollect)
{
    // var infoToReturn = "successful-itembar-1-stick-messageInfo-You have found a stick!-";
    var item = nameItemToCollect;

    var result;
    var splitResult="";

    var successful = true;
    var messageNext = false;

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
                    splitResult=result.substring(i,result.size-i);

                    if(messageNext)
                    {
                        showInfoMessage(splitResult);
                        messageNext = false;
                    }

                    // to set successful in beginning, initiate itembar or give out an info message
                    else
                    {
                        switch(splitResult)
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
            // if successful, rebuild itembar
            if(successful)
            {
                buildItembar();
            }
        },
        error: function (data, request) {
            alert("FAIL " + data+result);
        }
    });
}




function getCharIngame()
{
    var result;
    var splitResult="";


    // call of java method
    $.ajax({
        url: '/getCharIngame',
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


            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);


                    switch(splitResult)
                    {
                        case "Bob":
                            document.getElementById('charIconImg').setAttribute('src',document.getElementById('charBob').src);
                            break;
                        case "Alice":
                            document.getElementById('charIconImg').setAttribute('src',document.getElementById('charAlice').src);
                            break;
                        case "Nova":
                            document.getElementById('charIconImg').setAttribute('src',document.getElementById('charNova').src);
                            break;
                        case "HomTanks":
                            document.getElementById('charIconImg').setAttribute('src',document.getElementById('charHomTanks').src);
                            break;
                        case "BerryStraw":
                            document.getElementById('charIconImg').setAttribute('src',document.getElementById('charBerryStraw').src);
                            break;
                        case "CaptainSpeckJarrow":
                            document.getElementById('charIconImg').setAttribute('src',document.getElementById('charCaptainSpeckJarrow').src);
                            break;
                    }

                    // prepare for the next splitresult to read; in the basic version there should be none
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

function buildItembar()
{
    var result;
    var splitResult="";

    var itembarGetSizeNext = false;
    var itembarSetItems = false;
    var messageNext = false;
    var itembarSize = 0;
    var itembarCounter = 1;

    var idBase = "imgSlot";
    var idComplete ="";

    var imgItemID ='';

    var imgItemIDDefault = 'imgItembarDefault';

    // call of java method without parameter
    $.ajax({
        url: '/getItems',
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    // sets the items for the itembar
                    if(itembarSetItems)
                    {
                        // create proper id of the item bar slot
                        idComplete=idBase+itembarCounter;
                        switch(splitResult)
                        {
                            case "stick": imgItemID = 'itemStick';
                                break;
                        }
                        // select the item slot and insert the fitting item image
                        document.getElementById(idComplete).setAttribute('src',document.getElementById(imgItemID).src);

                        itembarCounter++;
                        // if all item slots should be filled with items, itembarSetItem state is terminated too
                        if(itembarCounter > 20)
                        {
                            itembarSetItems = false;
                        }

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

                    // initiate itembar if successful
                    else
                    {
                        switch(splitResult)
                        {
                            case "itembar":
                                itembarGetSizeNext = true;
                                break;
                            default: alert("Receiving itembar was not successful.");
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



function placeObjects(location)
{
    var result;
    var splitResult="";

    model_data = JSON.stringify(location);

    // call of java method without parameter
    $.ajax({
        url: '/getObjects/'+location,
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    switch(splitResult)
                    {
                        // non-item objects
                        case "beehive": $("#" + "objBeehive").css({"visibility": "visible"});
                            break;
                        case "bear": $("#" + "objBear").css({"visibility": "visible"});
                            break;

                        // item objects
                        case "stick": $("#" + "objStick").css({"visibility": "visible"});
                            break;
                        case "flintstones": $("#" + "objFlintstones").css({"visibility": "visible"});
                            break;
                        case "rope": $("#" + "objRope").css({"visibility": "visible"});
                            break;
                        case "rumbarrel": $("#" + "objRumbarrel").css({"visibility": "visible"});
                            break;
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

function getLocationMessage(location)
{
    var result;
    var splitResult="";

    var messageNext = false;

    location += "-";

    model_data = JSON.stringify(location);

    // call of java method without parameter
    $.ajax({
        url: '/getLocationMessage/'+location,
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    if(messageNext)
                    {
                        showInfoMessage(splitResult);
                        messageNext = false;
                    }
                    else
                    {
                        switch(splitResult)
                        {
                            case "MessageInfo": messageNext = true;
                                break;
                        }
                    }

                    // prepare for the next splitresult to read; in the basic version of this method there should be none
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

function loadContent(location)
{
    placeObjects(location);
    buildItembar();
    getCharIngame();
    getLocationMessage(location);
}




function showInfoMessage(message)
{
    makeVisibleClass('infoArea');
    document.getElementById('infoTextMsg').textContent = message;
}


function getCharInteraction()
{
    var result;
    var splitResult="";

    // call of java method without parameter
    $.ajax({
        url: '/getCharInteraction',
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    showInfoMessage(splitResult);

                    // prepare for the next splitresult to read; in the basic version of this method there should be none
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



function useTool()
{
    var idTd = "";
    var idTdBase = "slot";

    var slotImgID = "";
    var slotImgIDBase = "imgSlot";

    var itemsSelectedToReturn = "";

    // for ajax
    var result;
    var splitResult="";

    var successful = true;
    var messageNext = false;


    // for all item slots find out which of them are selected
    for(var i=1; i<=20; i++)
    {
        idTd = idTdBase + i;
        slotImgID = slotImgIDBase + i;

        // if a slot is selected
        if(document.getElementById(idTd).getAttribute('class')=='table_td_selected')
        {
            // find out which item it contains
            switch(document.getElementById(slotImgID).src)
            {
                case document.getElementById('itemStick').src:
                    itemsSelectedToReturn += "stick-";
                    break;
                case document.getElementById('itemFlintstones').src:
                    itemsSelectedToReturn += "flintstones-";
                    break;
                case document.getElementById('itemRope').src:
                    itemsSelectedToReturn += "rope-";
                    break;
                case document.getElementById('itemRumbarrel').src:
                    itemsSelectedToReturn += "rumbarrel-";
                    break;
            }
        }
    }

    model_data = JSON.stringify(itemsSelectedToReturn);

    $.ajax({
        url: '/getCombination/'+itemsSelectedToReturn,
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    // message with result of the combination process
                    if(messageNext)
                    {
                        showInfoMessage(splitResult);
                        messageNext = false;
                    }
                    else
                        {
                            switch(splitResult)
                            {
                                // set successful in beginning. if successful, rebuild itembar
                                case "successful":
                                    successful = true;
                                    buildItembar();
                                    break;
                                // if not successful, working with the result will end
                                case "notSuccessful":
                                    successful = false;
                                    break;
                                case "messageInfo":
                                    messageNext = true;
                                    break;
                            }
                        }
                    // prepare for the next splitresult to read; in the basic version of this method there should be none
                    result=result.substring(i+1);
                    i=0;
                }
            }
        },
        error: function (data, request) {
            alert("FAIL " + data+result);
        }
    });

    // unselect all items
    for(var i=1; i<=20; i++)
    {
        idTd = idTdBase + i;
        document.getElementById(idTd).setAttribute('class','table_td');
    }
}




// for Use Cases 'Interact With Objects' and 'Using Items On Objects'
function getAction(object)
{
    var idTd = "";
    var idTdBase = "slot";

    var slotImgID = "";
    var slotImgIDBase = "imgSlot";

    var infoToReturn = ""+object+"-";
    var itemsSelectedToReturn = "";

    // for ajax
    var result;
    var splitResult="";

    var successful = true;
    var messageNext = false;


    // for all item slots find out which of them are selected
    for(var i=1; i<=20; i++)
    {
        idTd = idTdBase + i;
        slotImgID = slotImgIDBase + i;

        // if a slot is selected
        if(document.getElementById(idTd).getAttribute('class')=='table_td_selected')
        {
            // find out which item it contains
            switch(document.getElementById(slotImgID).src)
            {
                case document.getElementById('itemStick').src:
                    itemsSelectedToReturn += "stick-";
                    break;
                case document.getElementById('itemFlintstones').src:
                    itemsSelectedToReturn += "flintstones-";
                    break;
                case document.getElementById('itemRope').src:
                    itemsSelectedToReturn += "rope-";
                    break;
                case document.getElementById('itemRumbarrel').src:
                    itemsSelectedToReturn += "rumbarrel-";
                    break;
            }
        }
    }

    infoToReturn += itemsSelectedToReturn;

    model_data = JSON.stringify(infoToReturn);

    $.ajax({
        url: '/getCombination/'+infoToReturn,
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    // message with result of the combination process
                    if(messageNext)
                    {
                        showInfoMessage(splitResult);
                        messageNext = false;
                    }
                    else
                    {
                        switch(splitResult)
                        {
                            // set successful in beginning. if successful, rebuild itembar
                            case "successful":
                                successful = true;
                                buildItembar();
                                break;
                            // if not successful, working with the result will end
                            case "notSuccessful":
                                successful = false;
                                break;
                            case "messageInfo":
                                messageNext = true;
                                break;
                        }
                    }
                    // prepare for the next splitresult to read; in the basic version of this method there should be none
                    result=result.substring(i+1);
                    i=0;
                }
            }
        },
        error: function (data, request) {
            alert("FAIL " + data+result);
        }
    });

    // unselect all items
    for(var i=1; i<=20; i++)
    {
        idTd = idTdBase + i;
        document.getElementById(idTd).setAttribute('class','table_td');
    }
}


function interactWithNPC(npc)
{
    var result;
    var splitResult="";

    model_data = JSON.stringify(npc);

    // call of java method without parameter
    $.ajax({
        url: '/interactWithNPC/'+npc,
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

            for(var i=0; i<result.length; i++)
            {
                if(result[i]=="-")
                {
                    splitResult=result.substring(i,result.size-i);

                    showInfoMessage(splitResult);

                    // prepare for the next splitresult to read; in the basic version of this method there should be none
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

function actionEditProfile()
{
    createNewGame();
    getNewLocation('getStartLocation-beachMid');
}



















